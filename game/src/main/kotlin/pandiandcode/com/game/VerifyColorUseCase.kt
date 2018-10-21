package pandiandcode.com.game

import arrow.core.Option
import arrow.core.applicative
import arrow.core.ev
import arrow.data.*
import arrow.syntax.applicative.tupled
import arrow.syntax.functor.map
import pandiandcode.com.game.model.Color

class VerifyColorUseCase(private val gameRepository: GameRepository) {
    fun execute(color: Color): Validated<Unit, List<Color>> =
            gameRepository.getColor().filter { it == color }
                    .fold({
                        gameRepository.resetGame()
                        Invalid(Unit)
                    }, { _ ->
                        isEndOfSequence().filter { it }
                                .flatMap { combineGameColorAndNewColor() }
                                .fold({
                                    gameRepository.incrementGameSequence()
                                    Valid(emptyList())
                                }, {
                                    gameRepository.resetGameSequence()
                                    Valid(it)
                                })
                    })


    private fun combineGameColorAndNewColor() =
            Try.applicative().tupled(
                    gameRepository.getAllColorsGame(),
                    gameRepository.generateColor()
            ).map {
                it.a.toMutableList().apply { add(it.b) }.toList()
            }.ev()

    private fun isEndOfSequence() =
            Try.applicative().tupled(
                    gameRepository.getAllColorsGame(),
                    gameRepository.getCurrentGameSequence()
            ).map {
                (it.a.size - 1) == it.b
            }.ev()
}
