package pandiandcode.com.game.usecases

import arrow.core.Try
import arrow.data.Invalid
import arrow.data.Valid
import arrow.data.Validated
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository
import pandiandcode.com.game.model.Color

class VerifyColor(
    private val colorSequenceRepository: ColorSequenceRepository,
    private val positionRepository: PositionRepository
) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> {
        val position = positionRepository.getCurrentSequencePosition()
        return colorSequenceRepository.getColorAt(position)
            .filter { colorToValidate ->
                colorToValidate == color
            }.fold({
                Invalid(Unit)
            }, {
                isEndOfSequence()
                    .filter { it }
                    .flatMap { addColorToSequence() }
                    .fold({
                        positionRepository.incrementSequencePosition()
                        Valid(emptyList())
                    }, {
                        positionRepository.resetSequencePosition()
                        Valid(it)
                    })

            })
    }

    private fun isEndOfSequence(): Try<Boolean> = Try {
        val position = positionRepository.getCurrentSequencePosition()
        val size = colorSequenceRepository.getColorsSequence().size
        size - 1 == position
    }

    private fun addColorToSequence(): Try<List<Color>> =
        colorSequenceRepository.createColor()
            .map { colorSequenceRepository.getColorsSequence() }
}
