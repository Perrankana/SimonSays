package pandiandcode.com.simonsays

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pandiandcode.com.simonsays.di.simonSaysModules

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
class SimonSaysApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SimonSaysApplication)
            modules(simonSaysModules())
        }
    }
}