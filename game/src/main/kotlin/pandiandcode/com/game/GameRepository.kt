package pandiandcode.com.game

import arrow.data.Try
import pandiandcode.com.game.model.Color

class GameRepository(private val colorProvider: ColorProvider, private val gameDataSource: GameDataSource) {
    fun generateColor(): Try<Color> = Try.invoke {
        val color = colorProvider.generateColor()
        gameDataSource.saveColor(color)
        color
    }

    fun getColor(): Try<Color> = Try.invoke {
        val position = gameDataSource.positionToValidate
        gameDataSource.getColorAt(position)
    }

    fun getAllColorsGame(): Try<List<Color>> = Try.invoke { gameDataSource.gameColors.toList() }

    fun resetGame() {

        gameDataSource.reset()
    }

    fun getCurrentGameSequence(): Try<Int> = Try.invoke { gameDataSource.positionToValidate }

    fun incrementGameSequence() {
        gameDataSource.positionToValidate++
    }

    fun resetGameSequence() {
        gameDataSource.positionToValidate = 0
    }

}
