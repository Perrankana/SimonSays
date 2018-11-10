package pandiandcode.com.game.di

import org.koin.dsl.module.module
import pandiandcode.com.game.GameDataSource
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.providers.ColorProvider
import pandiandcode.com.game.providers.NumberProvider
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor

val gameModule = module {
    single { GamePresenter(get(), get()) }
    single { StartNewGame(get()) }
    single { VerifyColor(get()) }
    single { GameRepository(get(), get()) }
    single { GameDataSource() }
    single { ColorProvider(get()) }
    single { NumberProvider() }
}