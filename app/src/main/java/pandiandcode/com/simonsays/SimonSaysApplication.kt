package pandiandcode.com.simonsays

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import pandiandcode.com.common.di.simonSaysModules


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