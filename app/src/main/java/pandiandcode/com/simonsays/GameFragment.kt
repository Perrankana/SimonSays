package pandiandcode.com.simonsays

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_game.*
import org.koin.android.ext.android.inject
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
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
        highlightColor(color)
    }

    override fun renderGameOver() {
        showStartGameButton()
    }

    override fun renderColors(colors: List<Color>) {
        highlightColors(colors)
    }

    private fun hideStartGameButton() {
        startGame.visibility = GONE
    }

    private fun highlightColor(color: Color) {
        renderColor(color, DELAY)
        resetColors(2 * DELAY)
    }

    private fun showStartGameButton() {
        Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show()
        startGame.visibility = VISIBLE
    }

    private fun highlightColors(colors: List<Color>) {
        colors.forEachIndexed { index, color ->
            renderColor(color, (index + 1) * 2 * DELAY)
            resetColors((((index + 1) * 2) + 1) * DELAY)
        }
    }

    private fun renderColor(color: Color, delay: Long) {
        when (color) {
            Color.Green -> highlightGreen(delay)
            Color.Red -> highlightRed(delay)
            Color.Yellow -> highlightYellow(delay)
            Color.Blue -> highlightBlue(delay)
        }
    }

    private fun initStartButton() {
        startGame.setOnClickListener {
            presenter.onStartGame()
        }
    }

    private fun highlightGreen(delay: Long) {
        game.highlightGreen(delay)
    }

    private fun highlightBlue(delay: Long) {
        game.highlightBlue(delay)
    }

    private fun highlightYellow(delay: Long) {
        game.highlightYellow(delay)
    }

    private fun highlightRed(delay: Long) {
        game.highlightRed(delay)
    }

    private fun resetColors(delay: Long) {
        game.resetColors(delay)
    }
}