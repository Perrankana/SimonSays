package pandiandcode.com.game.repositories

import arrow.core.Try
import pandiandcode.com.game.datasources.GameDataSource
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

class GameRepository(private val colorProvider: ColorProvider, private val gameDataSource: GameDataSource) {
    fun generateColor(): Try<Color> = Try.invoke {
        colorProvider.generateColor().also {
            gameDataSource.saveColor(it)
        }
    }

    fun getColorToValidate(): Try<Color> = Try.invoke {
        val position = gameDataSource.positionToValidate
        gameDataSource.getColorAt(position)
    }

    fun getColorsSequence(): List<Color> = gameDataSource.getGameColors()

    fun resetGame() {
        gameDataSource.reset()
    }

    fun getCurrentSequencePosition(): Int = gameDataSource.positionToValidate

    fun incrementSequencePosition() {
        gameDataSource.positionToValidate++
    }

    fun resetSequencePosition() {
        gameDataSource.positionToValidate = 0
    }
}
