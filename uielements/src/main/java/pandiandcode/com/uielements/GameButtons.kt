package pandiandcode.com.uielements

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.layout_game.view.*

class GameButtons @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        View.inflate(context, R.layout.layout_game, this)
    }

    fun setOnGreenButtonListener(block: (Unit) -> Unit) {
        greenButton.setOnClickListener { block.invoke(Unit) }
    }

    fun setOnRedButtonListener(block: (Unit) -> Unit) {
        redButton.setOnClickListener { block.invoke(Unit) }
    }

    fun setOnYellowButtonListener(block: (Unit) -> Unit) {
        yellowButton.setOnClickListener { block.invoke(Unit) }
    }

    fun setOnBlueButtonListener(block: (Unit) -> Unit) {
        blueButton.setOnClickListener { block.invoke(Unit) }
    }

    fun resetColors() {
        deactivateGreenColor()
        deactivateBlueColor()
        deactivateYellowColor()
        reactivateRedColor()
    }

    fun highlightGreenColor() {
        greenButton.setBackgroundResource(R.drawable.green_button_border_background)
    }

    fun highlightBlueColor() {
        blueButton.setBackgroundResource(R.drawable.blue_button_border_background)
    }

    fun highlightYellowColor() {
        yellowButton.setBackgroundResource(R.drawable.yellow_button_border_background)
    }

    fun highlightRedColor() {
        redButton.setBackgroundResource(R.drawable.red_button_border_background)
    }

    private fun reactivateRedColor() {
        redButton.setBackgroundResource(R.drawable.red_button_background)
    }

    private fun deactivateYellowColor() {
        yellowButton.setBackgroundResource(R.drawable.yellow_button_background)
    }

    private fun deactivateBlueColor() {
        blueButton.setBackgroundResource(R.drawable.blue_button_background)
    }

    private fun deactivateGreenColor() {
        greenButton.setBackgroundResource(R.drawable.green_button_background)
    }
}