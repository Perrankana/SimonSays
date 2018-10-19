package pandiandcode.com.game

import arrow.data.Try
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class GamePresenter(private val startNewGameUseCase: StartNewGameUseCase) {

    var view: View? = null

    fun onAttach(view: View) {
        this.view = view
    }

    fun onDetach() {
        view = null
    }

    fun onStartGame() {
        launch(MAIN_CONTEXT) {
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
        
    }

    fun onRedPressed() {


    }

    fun onYellowPressed() {

    }

    fun onBluePressed() {

    }

    interface View {
        fun renderColor(color: Color)
        fun hideStartButton()
    }
}