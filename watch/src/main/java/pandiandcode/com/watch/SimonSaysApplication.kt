package pandiandcode.com.watch

import android.app.Application
import org.koin.android.ext.android.startKoin
import pandiandcode.com.watch.di.simonSaysModules

/**
 * Created by Rocio Ortega on 21/12/2018.
 */
class SimonSaysApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, simonSaysModules())
    }
}