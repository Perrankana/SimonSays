package pandiandcode.com.game.usecases

import arrow.core.Try
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.repositories.GameRepository

class StartNewGame(private val gameRepository: GameRepository) {
    operator fun invoke(): Try<Color> {
        gameRepository.resetGame()
        return gameRepository.generateColor()
    }
}
