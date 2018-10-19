package pandiandcode.com.game

import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 19/10/2018.
 */
class GameRepositoryShould {
    companion object {
        private val COLOR = Color.Red
    }

    private val colorProvider: ColorProvider = mock()
    private val dataSource: GameDataSource = mock()
    private val gameRepository: GameRepository = GameRepository(colorProvider, dataSource)

    @Test
    fun `get color from Color Provider when a color is requested`() {
        gameRepository.getColor()

        verify(colorProvider).generateColor()
    }

    @Test
    fun `save color in data source when color is requested`() {
        whenever(colorProvider.generateColor()).thenReturn(COLOR)

        gameRepository.getColor()

        verify(dataSource).saveColor(eq(COLOR))
    }
}