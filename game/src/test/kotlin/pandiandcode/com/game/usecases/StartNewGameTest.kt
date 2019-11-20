package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import pandiandcode.com.game.repositories.GameRepository

class StartNewGameTest {

    private val gameRepository: GameRepository = mock()
    private val useCase: StartNewGame = StartNewGame(gameRepository)

    @Test
    fun `should get new color when it is executed`() {
        useCase()

        verify(gameRepository).generateColor()
    }

    @Test
    fun `should reset any started game when it is executed`(){
        useCase()

        verify(gameRepository).resetGame()
    }
}