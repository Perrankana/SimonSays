package pandiandcode.com.game.datasources

import pandiandcode.com.game.model.Color

class ColorSequenceDataSource {
    private val colorSequence = mutableListOf<Color>()

    fun saveColor(color: Color) {
        colorSequence.add(color)
    }

    fun getColorSequence() = colorSequence.toList()

    fun getColorAt(position: Int): Color = colorSequence[position]

    fun reset() {
        colorSequence.clear()
    }
}