package pandiandcode.com.game.usecases

import arrow.data.Try
import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

class StartNewGame(private val gameRepository: GameRepository) {
    operator fun invoke(): Try<Color> = gameRepository.generateColor()
}
