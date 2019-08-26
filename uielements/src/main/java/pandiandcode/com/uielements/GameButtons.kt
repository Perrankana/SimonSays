package pandiandcode.com.uielements

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.layout_game.view.blueButton
import kotlinx.android.synthetic.main.layout_game.view.greenButton
import kotlinx.android.synthetic.main.layout_game.view.redButton
import kotlinx.android.synthetic.main.layout_game.view.yellowButton

class GameButtons @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        View.inflate(context, R.layout.layout_game, this)
    }

    fun setOnGreenButtonListener(block: (Unit) -> Unit) {
        onClickButton(greenButton, block)
    }

    fun setOnRedButtonListener(block: (Unit) -> Unit) {
        onClickButton(redButton, block)
    }

    fun setOnYellowButtonListener(block: (Unit) -> Unit) {
        onClickButton(yellowButton, block)
    }

    fun setOnBlueButtonListener(block: (Unit) -> Unit) {
        onClickButton(blueButton, block)
    }

    fun highlightGreenColor() {
        highLightButton(greenButton)
    }

    fun highlightBlueColor() {
        highLightButton(blueButton)
    }

    fun highlightYellowColor() {
        highLightButton(yellowButton)
    }

    fun highlightRedColor() {
        highLightButton(redButton)
    }

    private fun onClickButton(button: AppCompatImageView, block: (Unit) -> Unit) {
        button.setOnClickListener {
            highLightButton(button) {
                block.invoke(Unit)
            }
        }
    }

    private fun highLightButton(button: AppCompatImageView, animationEnd: (() -> Unit)? = null) {
        (button.background as? AnimatedVectorDrawable)?.apply {
            AnimatedVectorDrawableCompat.registerAnimationCallback(this, object : Animatable2Compat.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                    super.onAnimationEnd(drawable)
                    animationEnd?.invoke()
                    AnimatedVectorDrawableCompat.clearAnimationCallbacks(drawable)
                }
            })
            start()
        }
    }
}