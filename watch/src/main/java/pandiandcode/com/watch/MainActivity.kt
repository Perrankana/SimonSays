package pandiandcode.com.watch

import android.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


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
