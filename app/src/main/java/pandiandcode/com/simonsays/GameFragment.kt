package pandiandcode.com.simonsays

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_game.*
import org.koin.android.ext.android.inject
import pandiandcode.com.game.GamePresenter


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

    private fun initStartButton() {
        startGame.setOnClickListener {
            presenter.onStartGame()
        }
    }
}