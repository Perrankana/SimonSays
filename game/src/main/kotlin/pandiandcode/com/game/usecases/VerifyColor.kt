package pandiandcode.com.game.usecases

import arrow.data.*
import arrow.syntax.applicative.tupled
import arrow.syntax.functor.map

import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColor(private val gameRepository: GameRepository) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
            gameRepository.getColorToValidate().filter { it == color }
                    .fold({
                        gameRepository.resetGame()
                        Invalid(Unit)
                    }, {
                        isEndOfSequence().filter { it }
                                .flatMap { addNewColorToGameColorSequence() }
                                .fold({
                                    gameRepository.incrementGameSequencePosition()
                                    Valid(emptyList())
                                }, {colors ->
                                    gameRepository.resetGameSequencePosition()
                                    Valid(colors)
                                })
                    })


    private fun addNewColorToGameColorSequence() =
            Try.applicative().tupled(
                    gameRepository.getAllColorsGame(),
                    gameRepository.generateColor()
            ).map {
                it.a.toMutableList().apply { add(it.b) }.toList()
            }.ev()

    private fun isEndOfSequence() =
            Try.applicative().tupled(
                    gameRepository.getAllColorsGame(),
                    gameRepository.getCurrentGameSequencePosition()
            ).map {
                (it.a.size - 1) == it.b
            }.ev()
}
