package pandiandcode.com.uielements

import android.content.Context
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.layout_game.view.*

/**
 * Created by Rocio Ortega on 21/12/2018.
 */
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

    fun highlightGreen(delay: Long) {
        Handler().postDelayed({
            greenButton.setBackgroundResource(R.drawable.green_button_border_background)
        }, delay)
    }

    fun highlightBlue(delay: Long) {
        Handler().postDelayed({
            blueButton.setBackgroundResource(R.drawable.blue_button_border_background)
        }, delay)
    }

    fun highlightYellow(delay: Long) {
        Handler().postDelayed({
            yellowButton.setBackgroundResource(R.drawable.yellow_button_border_background)
        }, delay)
    }

    fun highlightRed(delay: Long) {
        Handler().postDelayed({
            redButton.setBackgroundResource(R.drawable.red_button_border_background)
        }, delay)
    }

    fun resetColors(delay: Long) {
        Handler().postDelayed({
            greenButton.setBackgroundResource(R.drawable.green_button_background)
            blueButton.setBackgroundResource(R.drawable.blue_button_background)
            yellowButton.setBackgroundResource(R.drawable.yellow_button_background)
            redButton.setBackgroundResource(R.drawable.red_button_background)
        }, delay)
    }
}