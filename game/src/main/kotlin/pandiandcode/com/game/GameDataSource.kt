package pandiandcode.com.game

import pandiandcode.com.game.model.Color

class GameDataSource {
    val gameColors = mutableListOf<Color>()

    fun saveColor(color: Color) {
        gameColors.add(color)
    }

}
