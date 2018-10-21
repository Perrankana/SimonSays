package pandiandcode.com.simonsays

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*
import org.koin.android.ext.android.inject
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.MAIN_CONTEXT
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
        greenButton.setOnClickListener { presenter.onGreenPressed() }
        redButton.setOnClickListener { presenter.onRedPressed() }
        yellowButton.setOnClickListener { presenter.onYellowPressed() }
        blueButton.setOnClickListener { presenter.onBluePressed() }
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
        startGame.visibility = GONE
    }

    override fun renderColor(color: Color) {
        renderColor(color, DELAY)
        highlightColor(R.color.dark, DELAY * 2)
    }

    private fun renderColor(color: Color, delay: Long) {
        when (color) {
            Color.Green -> highlightColor(R.color.green, delay)
            Color.Red -> highlightColor(R.color.red, delay)
            Color.Yellow -> highlightColor(R.color.yellow, delay)
            Color.Blue -> highlightColor(R.color.blue, delay)
        }
    }

    override fun renderGameOver() {
        Toast.makeText(context, "GAME OVER", Toast.LENGTH_SHORT).show()
        startGame.visibility = VISIBLE
    }

    override fun renderColors(colors: List<Color>) {
        colors.forEachIndexed { index, color ->
            renderColor(color, (index + 1) * 2 * DELAY)
            highlightColor(R.color.dark, (((index + 1) * 2) + 1) * DELAY)
        }

    }

    private fun initStartButton() {
        startGame.setOnClickListener {
            presenter.onStartGame()
        }
    }

    private fun highlightColor(colorRes: Int, delay: Long) {
        Handler().postDelayed({
            root.setBackgroundResource(colorRes)
        }, delay)
    }
}