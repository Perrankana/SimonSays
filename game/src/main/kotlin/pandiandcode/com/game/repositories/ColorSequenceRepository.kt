package pandiandcode.com.game.repositories

import arrow.core.Option
import pandiandcode.com.game.model.Color

interface ColorSequenceRepository {
    fun createColor(): Option<Color>

    fun getColorAt(position: Int): Option<Color>

    fun getColorsSequence(): List<Color>

    fun reset()
}