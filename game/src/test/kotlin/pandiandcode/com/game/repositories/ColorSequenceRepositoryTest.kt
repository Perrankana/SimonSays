package pandiandcode.com.game.repositories

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import pandiandcode.com.game.datasources.ColorSequenceDataSource
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

class ColorSequenceRepositoryTest{
    private val colorProvider: ColorProvider = mock()
    private val dataSource: ColorSequenceDataSource = mock()
    private val repository: ColorSequenceRepository = ColorSequenceRepository(colorProvider, dataSource)

    @Test
    fun `should get color from Color Provider when a color is requested`() {
        repository.createColor()

        verify(colorProvider).generateColor()
    }

    @Test
    fun `should save color in data source when color is requested`() {
        whenever(colorProvider.generateColor()).thenReturn(COLOR)

        repository.createColor()

        verify(dataSource).saveColor(eq(COLOR))
    }

    @Test
    fun `should get color at position from data source`() {
        whenever(dataSource.getColorAt(eq(POSITION))).thenReturn(COLOR)

        repository.getColorAt(POSITION)

        verify(dataSource).getColorAt(eq(POSITION))
    }

    private companion object{
        val COLOR = Color.Green
        const val POSITION = 0
    }
}