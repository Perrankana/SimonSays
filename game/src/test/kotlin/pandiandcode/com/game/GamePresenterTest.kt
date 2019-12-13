package pandiandcode.com.game

import arrow.core.Invalid
import arrow.core.Option
import arrow.core.Valid
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
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

    private val view: GamePresenter.View = mock()
    private val startNewGame: StartNewGame = mock()
    private val verifyColor: VerifyColor = mock()
    private val presenter: GamePresenter = GamePresenter(startNewGame, verifyColor)

    @Before
    fun setup() {
        val mainTestDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(mainTestDispatcher)

        presenter.onAttach(view)
    }

    @Test
    fun `should render first color when on start game`() {
        whenever(startNewGame()).thenReturn(Option.just(COLOR))

        presenter.onStartGame()

        verify(view).renderColor(eq(COLOR))
    }

    @Test
    fun `should hide start button when on start game`() {
        whenever(startNewGame()).thenReturn(Option.just(Color.Red))

        presenter.onStartGame()

        verify(view).hideStartButton()
    }

    private companion object {
        val COLOR = Color.Red
    }
}