package pandiandcode.com.game

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
        startNewGameUseCase.execute()
    }

    interface View
}