package pandiandcode.com.game.repositories

import arrow.core.Try
import pandiandcode.com.game.datasources.ColorSequenceDataSource
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

class ColorSequenceRepository(
        private val colorProvider: ColorProvider,
        private val colorSequenceDataSource: ColorSequenceDataSource
) {
    fun createColor(): Try<Color> = Try.invoke {
        colorProvider.generateColor().also {
            colorSequenceDataSource.saveColor(it)
        }
    }

    fun getColorAt(position: Int): Try<Color> = Try.invoke {
        colorSequenceDataSource.getColorAt(position)
    }

    fun getColorsSequence(): List<Color> = colorSequenceDataSource.getColorSequence()

    fun reset() {
        colorSequenceDataSource.reset()
    }
}