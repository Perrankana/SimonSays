package pandiandcode.com.game.usecases

import arrow.core.Invalid
import arrow.core.Option
import arrow.core.Valid
import arrow.core.Validated
import arrow.core.toOption
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository

class VerifyColor(
    private val colorSequenceRepository: ColorSequenceRepository,
    private val positionRepository: PositionRepository
) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
        with(positionRepository.getCurrentSequencePosition()) {
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

    private fun addNewColorToSequence(): Option<List<Color>> =
        colorSequenceRepository.createColor().map {
            colorSequenceRepository.getColorsSequence()
        }


    private fun isEndOfSequence(): Option<Boolean> {
        val sequenceSize = colorSequenceRepository.getColorsSequence().size
        val sequencePosition = positionRepository.getCurrentSequencePosition()
        return (sequenceSize - 1 == sequencePosition).toOption()
    }
}
