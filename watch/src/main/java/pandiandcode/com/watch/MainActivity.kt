package pandiandcode.com.watch

import android.R
import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFragment()
    }

    private fun initializeFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.content, GameFragment.newInstance())
                .commit()
    }
}
