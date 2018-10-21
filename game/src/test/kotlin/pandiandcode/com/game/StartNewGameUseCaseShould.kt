package pandiandcode.com.game

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

/**
 * Created by Rocio Ortega on 19/10/2018.
 */
class StartNewGameUseCaseShould {

    private val gameRepository: GameRepository = mock()
    private val useCase: StartNewGameUseCase = StartNewGameUseCase(gameRepository)

    @Test
    fun `get new color when it is executed`() {
        useCase.execute()

        verify(gameRepository).generateColor()
    }
}