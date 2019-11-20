package pandiandcode.com.game.usecases

import arrow.core.Invalid
import arrow.core.Try
import arrow.core.Valid
import arrow.core.Validated
import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository

import pandiandcode.com.game.model.Color

class VerifyColor(
        private val colorSequenceRepository: ColorSequenceRepository,
        private val positionRepository: PositionRepository
) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
            with(positionRepository.getCurrentSequencePosition()){
                colorSequenceRepository.getColorAt(this)
                        .filter { color == it }
                        .fold({
                            Invalid(Unit)
                        }, {
                            isEndOfSequence()
                                    .filter { it }
                                    .flatMap {
                                        addNewColorToSequence()
                                    }
                                    .fold({
                                        positionRepository.incrementSequencePosition()
                                        Valid(emptyList())
                                    }, {
                                        positionRepository.resetSequencePosition()
                                        Valid(it)
                                    })
                        })
            }

    private fun addNewColorToSequence(): Try<List<Color>> =
        colorSequenceRepository.createColor().map {
            colorSequenceRepository.getColorsSequence()
        }

    private fun isEndOfSequence(): Try<Boolean> =
        Try.invoke {
            val sequenceSize = colorSequenceRepository.getColorsSequence().size
            val sequencePosition = positionRepository.getCurrentSequencePosition()
            sequenceSize - 1 == sequencePosition
        }
}
