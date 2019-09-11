package pandiandcode.com.game.datasources

import pandiandcode.com.game.model.Color

class GameDataSource {
    val gameColors = mutableListOf<Color>()
    var positionToValidate = 0

    fun saveColor(color: Color) {
        gameColors.add(color)
    }

    fun getColorAt(position: Int): Color = gameColors[position]

    fun reset() {
        gameColors.clear()
        positionToValidate = 0
    }
}
