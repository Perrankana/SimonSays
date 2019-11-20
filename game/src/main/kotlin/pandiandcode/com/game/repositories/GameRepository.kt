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

    fun getAllColorsGame(): List<Color> = gameDataSource.gameColors.toList()

    fun resetGame() {
        gameDataSource.reset()
    }

    fun getCurrentGameSequencePosition(): Int = gameDataSource.positionToValidate

    fun incrementGameSequencePosition() {
        gameDataSource.positionToValidate++
    }

    fun resetGameSequencePosition() {
        gameDataSource.positionToValidate = 0
    }
}
