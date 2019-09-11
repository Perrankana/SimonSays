package pandiandcode.com.game.datasources

import pandiandcode.com.game.model.Color

class GameDataSource {
    private val gameColors = mutableListOf<Color>()

    var positionToValidate = 0

    fun saveColor(color: Color) {
        gameColors.add(color)
    }

    fun getGameColors() = gameColors.toList()

    fun getColorAt(position: Int): Color = gameColors[position]

    fun reset() {
        gameColors.clear()
        positionToValidate = 0
    }
}
