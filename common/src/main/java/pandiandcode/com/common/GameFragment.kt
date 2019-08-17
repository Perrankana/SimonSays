package pandiandcode.com.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.coroutines.MAIN_CONTEXT
import pandiandcode.com.game.model.Color

class GameFragment : Fragment(), GamePresenter.View {

    private val presenter: GamePresenter by inject()

    companion object {
        private const val DELAY = 300L
        fun newInstance(): GameFragment = GameFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStartButton()
        initButtons()
    }

    private fun initButtons() {
        game.setOnGreenButtonListener { presenter.onGreenPressed() }
        game.setOnRedButtonListener { presenter.onRedPressed() }
        game.setOnYellowButtonListener { presenter.onYellowPressed() }
        game.setOnBlueButtonListener { presenter.onBluePressed() }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.onAttach(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }

    override fun hideStartButton() {
        hideStartGameButton()
    }

    override fun renderColor(color: Color) {
        CoroutineScope(MAIN_CONTEXT).launch {
            highlightColor(color)
        }
    }

    override fun renderGameOver() {
        showStartGameButton()
    }

    override fun renderColors(colors: List<Color>) {
        CoroutineScope(MAIN_CONTEXT).launch {
            highlightColors(colors)
        }
    }

    private fun hideStartGameButton() {
        startGame.visibility = GONE
    }

    private suspend fun highlightColor(color: Color) {
        highlightOneColor(color)
        resetColors()
    }

    private fun showStartGameButton() {
        Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show()
        startGame.visibility = VISIBLE
    }

    private suspend fun highlightColors(colors: List<Color>) {
        colors.forEach { color ->
            highlightOneColor(color)
            resetColors()
        }
    }

    private suspend fun highlightOneColor(color: Color) {
        when (color) {
            Color.Green -> highlightGreen()
            Color.Red -> highlightRed()
            Color.Yellow -> highlightYellow()
            Color.Blue -> highlightBlue()
        }
    }

    private fun initStartButton() {
        startGame.setOnClickListener {
            presenter.onStartGame()
        }
    }

    private suspend fun highlightGreen() {
        delay(DELAY)
        game.highlightGreenColor()
    }

    private suspend fun highlightBlue() {
        delay(DELAY)
        game.highlightBlueColor()
    }

    private suspend fun highlightYellow() {
        delay(DELAY)
        game.highlightYellowColor()
    }

    private suspend fun highlightRed() {
        delay(DELAY)
        game.highlightRedColor()
    }

    private suspend fun resetColors() {
        delay(DELAY)
        game.resetColors()
    }
}