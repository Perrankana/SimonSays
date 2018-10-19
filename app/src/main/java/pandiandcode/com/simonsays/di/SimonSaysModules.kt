package pandiandcode.com.simonsays.di

import org.koin.dsl.module.module
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.StartNewGameUseCase

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
fun simonSaysModules() = listOf(gameModule)

val gameModule = module {
    single { GamePresenter(get()) }
    single { StartNewGameUseCase()}
}