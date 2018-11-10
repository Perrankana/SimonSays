package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import pandiandcode.com.game.GameRepository

/**
 * Created by Rocio Ortega on 19/10/2018.
 */
class StartNewGameShould {

    private val gameRepository: GameRepository = mock()
    private val useCase: StartNewGame = StartNewGame(gameRepository)

    @Test
    fun `get new color when it is executed`() {
        useCase()

        verify(gameRepository).generateColor()
    }
}