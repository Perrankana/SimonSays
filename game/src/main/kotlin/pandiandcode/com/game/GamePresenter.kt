package pandiandcode.com.game

import arrow.data.Try
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pandiandcode.com.game.coroutines.BG_CONTEXT
import pandiandcode.com.game.coroutines.MAIN_CONTEXT
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor
import kotlin.coroutines.CoroutineContext

class GamePresenter(
    private val startNewGame: StartNewGame, private val verifyColor: VerifyColor
) : CoroutineScope {
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = MAIN_CONTEXT + job

    var view: View? = null

    fun onAttach(view: View) {
        this.view = view
        job = Job()
    }

    fun onDetach() {
        view = null
        job.cancel()
    }

    fun onStartGame() {
        launch {
            startGame().map {
                view?.hideStartButton()
                view?.renderColor(it)
            }
        }
    }

    private suspend fun startGame(): Try<Color> {
        return withContext(BG_CONTEXT) {
            startNewGame()
        }
    }

    fun onGreenPressed() {
        colorPressed(Color.Green)
    }

    fun onRedPressed() {
        colorPressed(Color.Red)
    }

    fun onYellowPressed() {
        colorPressed(Color.Yellow)
    }

    fun onBluePressed() {
        colorPressed(Color.Blue)
    }

    private fun colorPressed(color: Color) {
        launch {
            withContext(BG_CONTEXT) {
                verifyColor(color)
            }.fold({
                view?.renderGameOver()
            }, {
                view?.renderColors(it)
            })
        }
    }

    interface View {
        fun renderColor(color: Color)
        fun hideStartButton()
        fun renderGameOver()
        fun renderColors(colors: List<Color>)
    }
}