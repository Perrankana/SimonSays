package pandiandcode.com.game.repositories

import arrow.core.Try
import pandiandcode.com.game.datasources.ColorSequenceDataSource
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

class ColorSequenceRepositoryImpl(
        private val colorProvider: ColorProvider,
        private val colorSequenceDataSource: ColorSequenceDataSource
): ColorSequenceRepository {
    override fun createColor(): Try<Color> = Try.invoke {
        colorProvider.generateColor().also {
            colorSequenceDataSource.saveColor(it)
        }
    }

    override fun getColorAt(position: Int): Try<Color> = Try.invoke {
        colorSequenceDataSource.getColorAt(position)
    }

    override fun getColorsSequence(): List<Color> = colorSequenceDataSource.getColorSequence()

    override fun reset() {
        colorSequenceDataSource.reset()
    }
}