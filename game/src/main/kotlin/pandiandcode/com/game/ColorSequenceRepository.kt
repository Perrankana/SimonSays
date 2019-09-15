package pandiandcode.com.game

import arrow.core.Try
import pandiandcode.com.game.datasources.ColorSequenceDataSource
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

interface ColorSequenceRepository {
    fun createColor(): Try<Color>

    fun getColorAt(position: Int):  Try<Color>

    fun getColorsSequence(): List<Color>

    fun reset()
}
