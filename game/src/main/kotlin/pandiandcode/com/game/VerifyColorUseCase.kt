package pandiandcode.com.game

import arrow.data.*
import arrow.syntax.applicative.tupled
import arrow.syntax.functor.map
import pandiandcode.com.game.model.Color

class VerifyColorUseCase(private val gameRepository: GameRepository) {
    fun execute(color: Color): Validated<Unit, List<Color>> =
            gameRepository.getColor().filter { it == color }
                    .flatMap { combineGameColorAndNewColor() }
                    .fold({
                        gameRepository.resetGame()
                        Invalid(Unit)
                    }, {
                        Valid(it)
                    })

    private fun combineGameColorAndNewColor() =
            Try.applicative().tupled(
                    gameRepository.getAllColorsGame(),
                    gameRepository.generateColor()
            ).map {
                it.a.toMutableList().apply { add(it.b) }.toList()
            }.ev()

}
