package pandiandcode.com.simonsays.di

import org.koin.dsl.module.module
import pandiandcode.com.game.*
import pandiandcode.com.game.providers.ColorProvider
import pandiandcode.com.game.providers.NumberProvider
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
fun simonSaysModules() = listOf(gameModule)

val gameModule = module {
    single { GamePresenter(get(), get()) }
    single { StartNewGame(get()) }
    single { VerifyColor(get()) }
    single { GameRepository(get(), get()) }
    single { GameDataSource() }
    single { ColorProvider(get()) }
    single { NumberProvider() }
}