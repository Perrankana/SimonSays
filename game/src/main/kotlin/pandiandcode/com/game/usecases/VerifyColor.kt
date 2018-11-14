package pandiandcode.com.game.usecases

import arrow.data.Invalid
import arrow.data.Try
import arrow.data.Valid
import arrow.data.Validated
import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColor(private val gameRepository: GameRepository) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
        gameRepository.getColorToValidate().filter { it == color }
            .fold({
                gameRepository.resetGame()
                Invalid(Unit)
            }, {
                if (isEndOfSequence()) {
                    addNewColorToGameColorSequence().fold({
                        gameRepository.resetGame()
                        Invalid(Unit)
                    }, { colors ->
                        gameRepository.resetGameSequencePosition()
                        Valid(colors)
                    })
                } else {
                    gameRepository.incrementGameSequencePosition()
                    Valid(emptyList())
                }
            })

    private fun addNewColorToGameColorSequence(): Try<List<Color>> =
        gameRepository.generateColor().map {
            gameRepository.getAllColorsGame().toMutableList().apply { add(it) }
        }

    private fun isEndOfSequence(): Boolean {
        val sequenceSize = gameRepository.getAllColorsGame().size
        val currentGameSequencePosition = gameRepository.getCurrentGameSequencePosition()
        return sequenceSize - 1 == currentGameSequencePosition
    }
}
