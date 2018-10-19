package pandiandcode.com.game

import arrow.data.Try
import pandiandcode.com.game.model.Color

open class StartNewGameUseCase(private val gameRepository: GameRepository) {
    fun execute(): Try<Color> = gameRepository.getColor()

}
