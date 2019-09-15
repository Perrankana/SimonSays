package pandiandcode.com.game

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import pandiandcode.com.game.coroutines.BG_CONTEXT
import pandiandcode.com.game.coroutines.DELAY
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor


@FlowPreview
class GamePresenter(
        private val startNewGame: StartNewGame,
        private val verifyColor: VerifyColor
) : CoroutineScope by MainScope() {
    private var view: View? = null

    fun onAttach(view: View) {
        this.view = view
    }

    fun onDetach() {
        view = null
        cancel()
    }

    fun onStartGame() {
        launch {
            withContext(BG_CONTEXT) {
                startNewGame()
            }.fold({
                Unit
            }, { firstColor ->
                view?.hideStartButton()
                listOf(firstColor).asFlowWithDelay(DELAY)
                        .collect { color ->
                            renderColor(color)
                        }
            })
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

    private fun colorPressed(colorPressed: Color) {

    }

    private fun renderColor(color: Color) {
        view?.renderColor(color)
    }

    private fun <T> Iterable<T>.asFlowWithDelay(delayTime: Long): Flow<T> =
            flow {
                forEach { value ->
                    delay(delayTime)
                    emit(value)
                }
            }

    interface View {
        fun hideStartButton()
        fun renderGameOver()
        fun renderColor(color: Color)
    }
}