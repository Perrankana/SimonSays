package pandiandcode.com.simonsays

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        fun newInstance(): GameFragment = GameFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initStartButton()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.onAttach(this)
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }

    override fun renderColor(color: Color) {
        when (color) {
            Color.Green -> highlightColor(R.color.green)
            Color.Red -> highlightColor(R.color.red)
            Color.Yellow -> highlightColor(R.color.yellow)
            Color.Blue -> highlightColor(R.color.blue)
        }
    }

    private fun initStartButton() {
        startGame.setOnClickListener {
            presenter.onStartGame()
        }
    }

    private fun highlightColor(colorRes: Int) {
        root.setBackgroundResource(colorRes)
        Handler().postDelayed({
            root.setBackgroundResource(R.color.dark)
        }, 300)
    }
}