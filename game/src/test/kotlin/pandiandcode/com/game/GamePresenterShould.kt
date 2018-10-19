package pandiandcode.com.game

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class GamePresenterShould {

    private val startNewGameUseCase: StartNewGameUseCase = mock()
    private val presenter: GamePresenter = GamePresenter(startNewGameUseCase)

    @Test
    fun `execute start new game when on start game`() {
        presenter.onStartGame()

        verify(startNewGameUseCase).execute()
    }
}