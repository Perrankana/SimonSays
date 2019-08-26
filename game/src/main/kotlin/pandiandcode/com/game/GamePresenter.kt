package pandiandcode.com.game

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pandiandcode.com.game.coroutines.BG_CONTEXT
import pandiandcode.com.game.coroutines.DELAY
import pandiandcode.com.game.coroutines.MAIN_CONTEXT
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor
import kotlin.coroutines.CoroutineContext

@UseExperimental(FlowPreview::class)
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
        launch {
            withContext(BG_CONTEXT) {
                verifyColor(colorPressed)
            }.fold({
                view?.renderGameOver()
            }, { colors ->
                colors.asFlowWithDelay(DELAY)
                    .collect { color ->
                        renderColor(color)
                    }
            })
        }
    }

    private fun renderColor(color: Color) {
        view?.renderColor(color)
    }

    private fun <T> Iterable<T>.asFlowWithDelay(delayTime: Long): Flow<T> = flow {
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