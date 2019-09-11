package pandiandcode.com.game.usecases

import arrow.core.Try
import pandiandcode.com.game.repositories.GameRepository
import pandiandcode.com.game.model.Color

class StartNewGame(private val gameRepository: GameRepository) {
    operator fun invoke(): Try<Color> = gameRepository.generateColor()
}
