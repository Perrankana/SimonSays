package pandiandcode.com.simonsays.di

import org.koin.dsl.module.module

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
fun simonSaysModules() = listOf(gameModule)

val gameModule = module {
    single {  }
}