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

    override fun renderGameOver() {
        showStartGameButton()
    }

    override fun renderColor(color: Color) {
        highlightOneColor(color)
    }

    override fun resetColors() {
        game.resetColors()
    }

    private fun hideStartGameButton() {
        startGame.visibility = GONE
    }

    private fun showStartGameButton() {
        Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show()
        startGame.visibility = VISIBLE
    }

    private fun highlightOneColor(color: Color) {
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

    private fun highlightGreen() {
        game.highlightGreenColor()
    }

    private fun highlightBlue() {
        game.highlightBlueColor()
    }

    private fun highlightYellow() {
        game.highlightYellowColor()
    }

    private fun highlightRed() {
        game.highlightRedColor()
    }
}