package pandiandcode.com.game

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

/**
 * Created by Rocio Ortega on 19/10/2018.
 */
class GameRepositoryShould {

    private val colorProvider: ColorProvider = mock()
    private val gameRepository: GameRepository = GameRepository(colorProvider)
    @Test
    fun `get color from Color Provider when a color is requested`() {
        gameRepository.getColor()

        verify(colorProvider).generateColor()
    }
}