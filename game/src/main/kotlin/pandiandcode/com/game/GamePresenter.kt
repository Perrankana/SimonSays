package pandiandcode.com.game

import arrow.data.Try
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pandiandcode.com.game.model.Color
import kotlin.coroutines.CoroutineContext

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class GamePresenter(
        private val startNewGameUseCase: StartNewGameUseCase, private val verifyColorUseCase: VerifyColorUseCase
) : CoroutineScope {
    lateinit var job: Job

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
        return async(BG_CONTEXT) {
            startNewGameUseCase.execute()
        }.await()
    }

    fun onGreenPressed() {
        launch {
            async(BG_CONTEXT) {
                verifyColorUseCase.execute(Color.Green)
            }.await().fold({
                view?.renderGameOver()
            }, {
                view?.renderColors(it)
            })
        }
    }

    fun onRedPressed() {
        launch {
            async(BG_CONTEXT) {
                verifyColorUseCase.execute(Color.Red)
            }.await().fold({
                view?.renderGameOver()
            }, {
                view?.renderColors(it)
            })
        }
    }

    fun onYellowPressed() {
        launch {
            async(BG_CONTEXT) {
                verifyColorUseCase.execute(Color.Yellow)
            }.await().fold({
                view?.renderGameOver()
            }, {
                view?.renderColors(it)
            })
        }
    }

    fun onBluePressed() {
        launch {
            async(BG_CONTEXT) {
                verifyColorUseCase.execute(Color.Blue)
            }.await().fold({
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