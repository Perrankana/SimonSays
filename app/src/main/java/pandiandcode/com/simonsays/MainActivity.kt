package pandiandcode.com.simonsays

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pandiandcode.com.common.GameFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFragment()
    }

    private fun initializeFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.content, GameFragment.newInstance())
            .commit()
    }
}
