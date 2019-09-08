package pandiandcode.com.game

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider

class GameRepositoryTest {
    companion object {
        private val COLOR = Color.Red
        private const val POSITION = 0
    }

    private val colorProvider: ColorProvider = mock()
    private val dataSource: GameDataSource = mock()
    private val gameRepository: GameRepository = GameRepository(colorProvider, dataSource)

    @Test
    fun `should get color from Color Provider when a color is requested`() {
        gameRepository.generateColor()

        verify(colorProvider).generateColor()
    }

    @Test
    fun `should save color in data source when color is requested`() {
        whenever(colorProvider.generateColor()).thenReturn(COLOR)

        gameRepository.generateColor()

        verify(dataSource).saveColor(eq(COLOR))
    }

    @Test
    fun `should get position to validate from data source`() {
        whenever(dataSource.positionToValidate).thenReturn(POSITION)

        gameRepository.getColorToValidate()

        verify(dataSource).positionToValidate
    }

    @Test
    fun `should get color at position from data source`() {
        whenever(dataSource.positionToValidate).thenReturn(POSITION)
        whenever(dataSource.getColorAt(eq(POSITION))).thenReturn(COLOR)

        gameRepository.getColorToValidate()

        verify(dataSource).getColorAt(eq(POSITION))
    }
}