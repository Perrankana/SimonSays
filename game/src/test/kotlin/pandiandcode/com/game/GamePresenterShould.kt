package pandiandcode.com.game

import arrow.data.Try
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class GamePresenterShould {
    private companion object {
        val COLOR = Color.Red
    }

    private val view: GamePresenter.View = mock()
    private val startNewGameUseCase: StartNewGameUseCase = mock()
    private val presenter: GamePresenter = GamePresenter(startNewGameUseCase)

    @Test
    fun `execute start new game when on start game`() {
        whenever(startNewGameUseCase.execute()).thenReturn(Try.pure(COLOR))

        presenter.onStartGame()

        verify(startNewGameUseCase).execute()
    }

    @Test
    fun `render first color when on start game`() {
        whenever(startNewGameUseCase.execute()).thenReturn(Try.pure(COLOR))
        presenter.onAttach(view)

        presenter.onStartGame()

        verify(view).renderColor(eq(COLOR))
    }
}