package pandiandcode.com.game.usecases

import arrow.core.Try
import arrow.data.Invalid
import arrow.data.Valid
import arrow.data.Validated

import pandiandcode.com.game.repositories.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColor(private val gameRepository: GameRepository) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
        gameRepository.getColorToValidate()
            .filter { color == it }
            .fold({
                Invalid(Unit)
            }, { colors ->
                isEndOfSequence()
                    .filter { it }
                    .flatMap {
                        addNewColorToSequence()
                    }
                    .fold({
                        gameRepository.incrementSequencePosition()
                        Valid(emptyList())
                    }, {
                        gameRepository.resetSequencePosition()
                        Valid(it)
                    })
            })

    private fun addNewColorToSequence(): Try<List<Color>> =
        gameRepository.generateColor().map {
            gameRepository.getColorsSequence()
        }

    private fun isEndOfSequence(): Try<Boolean> =
        Try.invoke {
            val sequenceSize = gameRepository.getColorsSequence().size
            val sequencePosition = gameRepository.getCurrentSequencePosition()
            sequenceSize - 1 == sequencePosition
        }
}
