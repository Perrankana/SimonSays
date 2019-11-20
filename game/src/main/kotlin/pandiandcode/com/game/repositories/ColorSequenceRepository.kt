package pandiandcode.com.game.repositories

import arrow.core.Try
import pandiandcode.com.game.model.Color

interface ColorSequenceRepository {
    fun createColor(): Try<Color>

    fun getColorAt(position: Int):  Try<Color>

    fun getColorsSequence(): List<Color>

    fun reset()
}