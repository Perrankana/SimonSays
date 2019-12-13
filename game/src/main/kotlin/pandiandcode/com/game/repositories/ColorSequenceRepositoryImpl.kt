package pandiandcode.com.game.repositories

import arrow.core.Option
import arrow.core.toOption
import pandiandcode.com.game.datasources.ColorSequenceDataSource
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

class ColorSequenceRepositoryImpl(
    private val colorProvider: ColorProvider,
    private val colorSequenceDataSource: ColorSequenceDataSource
) : ColorSequenceRepository {
    override fun createColor(): Option<Color> =
        colorProvider.generateColor().also {
            colorSequenceDataSource.saveColor(it)
        }.toOption()

    override fun getColorAt(position: Int): Option<Color> =
        colorSequenceDataSource.getColorAt(position).toOption()

    override fun getColorsSequence(): List<Color> = colorSequenceDataSource.getColorSequence()

    override fun reset() {
        colorSequenceDataSource.reset()
    }
}