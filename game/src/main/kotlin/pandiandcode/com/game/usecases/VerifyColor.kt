package pandiandcode.com.game.usecases

import arrow.data.Invalid
import arrow.data.Try
import arrow.data.Valid
import arrow.data.Validated
import arrow.data.applicative
import arrow.data.ev
import arrow.syntax.applicative.tupled
import arrow.syntax.functor.map
import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColor(private val gameRepository: GameRepository) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
            gameRepository.getColor().filter { it == color }
                    .fold({
                        gameRepository.resetGame()
                        Invalid(Unit)
                    }, { 
                        isEndOfSequence().filter { it }
                                .flatMap { combineGameColorAndNewColor() }
                                .fold({
                                    gameRepository.incrementGameSequence()
                                    Valid(emptyList())
                                }, {colors ->
                                    gameRepository.resetGameSequence()
                                    Valid(colors)
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
