package pandiandcode.com.game.usecases

import arrow.core.Try
import arrow.data.Invalid
import arrow.data.Valid
import arrow.data.Validated

import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColor(private val gameRepository: GameRepository) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
        gameRepository.getColorToValidate()
            .filter { color == it }
            .fold({
                gameRepository.resetGame()
                Invalid(Unit)
            }, { colors ->
                isEndOfSequence()
                    .filter { it }
                    .flatMap {
                        addNewColorToSequence()
                    }
                    .fold({
                        gameRepository.incrementGameSequencePosition()
                        Valid(emptyList())
                    }, {
                        gameRepository.resetGameSequencePosition()
                        Valid(it)
                    })
            })

    private fun addNewColorToSequence(): Try<List<Color>> =
        gameRepository.generateColor().map {
            gameRepository.getAllColorsGame()
        }

    private fun isEndOfSequence(): Try<Boolean> =
        Try.invoke {
            val sequenceSize = gameRepository.getAllColorsGame().size
            val sequencePosition = gameRepository.getCurrentGameSequencePosition()
            sequenceSize - 1 == sequencePosition
        }
}
