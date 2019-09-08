package pandiandcode.com.game

import arrow.core.Try
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor

@FlowPreview
@ExperimentalCoroutinesApi
class GamePresenterTest {

    private lateinit var view: GamePresenter.View
    private lateinit var startNewGame: StartNewGame
    private lateinit var verifyColor: VerifyColor
    private lateinit var presenter: GamePresenter

    @Before
    fun setup() {
        val mainTestDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(mainTestDispatcher)

        view = mock()
        startNewGame = mock()
        verifyColor = mock()
        presenter = GamePresenter(startNewGame, verifyColor)
        presenter.onAttach(view)
    }

    @Test
    fun `should execute start new game when on start game`() {
        whenever(startNewGame()).thenReturn(Try.just(COLOR))

        presenter.onStartGame()

        verify(startNewGame)()
    }

    @Test
    fun `should render first color when on start game`() {
        whenever(startNewGame()).thenReturn(Try.just(COLOR))

        presenter.onStartGame()

        inOrder(view) {
            verify(view).renderColor(eq(COLOR))
        }
    }

    @Test
    fun `should hide start button when on start game`() {
        whenever(startNewGame()).thenReturn(Try.just(Color.Red))

        presenter.onStartGame()

        verify(view).hideStartButton()
    }

    fun `should render game over if green color is not correct`() {

    }

    fun `should render list of colors if green color is correct`() {

    }

    private companion object {
        val COLOR = Color.Red
    }
}