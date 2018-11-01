package pandiandcode.com.game

import arrow.data.Try
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class GamePresenter(
        private val startNewGameUseCase: StartNewGameUseCase, private val verifyColorUseCase: VerifyColorUseCase
) {

    var view: View? = null

    fun onAttach(view: View) {
        this.view = view
    }

    fun onDetach() {
        view = null
    }

    fun onStartGame() {
        GlobalScope.launch(MAIN_CONTEXT) {
            startGame().map {
                view?.hideStartButton()
                view?.renderColor(it)
            }
        }
    }

    private suspend fun startGame(): Try<Color> {
        return GlobalScope.async(BG_CONTEXT) {
            startNewGameUseCase.execute()
        }.await()
    }

    fun onGreenPressed() {
        GlobalScope.launch(MAIN_CONTEXT) {
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
        GlobalScope.launch(MAIN_CONTEXT) {
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
        GlobalScope.launch(MAIN_CONTEXT) {
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
        GlobalScope.launch(MAIN_CONTEXT) {
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