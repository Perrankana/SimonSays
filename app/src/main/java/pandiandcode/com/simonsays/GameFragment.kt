package pandiandcode.com.simonsays

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.android.ext.android.inject
import pandiandcode.com.game.GamePresenter

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class GameFragment : Fragment() {

    val presenter: GamePresenter by inject()

    companion object {
        fun newInstance(): GameFragment = GameFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
}