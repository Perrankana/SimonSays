package pandiandcode.com.game

import arrow.data.Invalid
import arrow.data.Try
import arrow.data.Valid
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor

class GamePresenterShould {
    private companion object {
        val COLOR = Color.Red
    }

    private val view: GamePresenter.View = mock()
    private val startNewGame: StartNewGame = mock()
    private val verifyColor: VerifyColor = mock()
    private val presenter: GamePresenter = GamePresenter(startNewGame, verifyColor)

    @Test
    fun `execute start new game when on start game`() {
        whenever(startNewGame()).thenReturn(Try.pure(COLOR))
        presenter.onAttach(view)

        presenter.onStartGame()

        verify(startNewGame)()
    }

    @Test
    fun `render first color when on start game`() {
        whenever(startNewGame()).thenReturn(Try.pure(COLOR))
        presenter.onAttach(view)

        presenter.onStartGame()

        inOrder(view) {
            verify(view).renderColor(eq(COLOR))
        }
    }

    @Test
    fun `hide start button when on start game`() {
        whenever(startNewGame()).thenReturn(Try.pure(Color.Red))
        presenter.onAttach(view)

        presenter.onStartGame()

        verify(view).hideStartButton()
    }

    @Test
    fun `render game over if green color is not correct`() {
        whenever(verifyColor(eq(Color.Green))).thenReturn(Invalid(Unit))
        presenter.onAttach(view)

        presenter.onGreenPressed()

        verify(view).renderGameOver()
    }

    @Test
    fun `render list of colors if green color is correct`() {
        whenever(verifyColor(eq(Color.Green))).thenReturn(Valid(listOf(Color.Green, Color.Red)))
        presenter.onAttach(view)

        presenter.onGreenPressed()

        inOrder(view) {
            verify(view).renderColor(eq(Color.Green))
            verify(view).renderColor(eq(Color.Red))
        }
    }
}