package pandiandcode.com.game

import arrow.data.Try
import pandiandcode.com.game.model.Color

class GameRepository(private val colorProvider: ColorProvider, private val gameDataSource: GameDataSource) {
    fun getColor(): Try<Color> = Try.invoke {
        val color = colorProvider.generateColor()
        gameDataSource.saveColor(color)
        color
    }

}
