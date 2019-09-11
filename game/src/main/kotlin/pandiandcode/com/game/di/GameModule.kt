package pandiandcode.com.game.di


import org.koin.dsl.module
import pandiandcode.com.game.datasources.GameDataSource
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.repositories.GameRepository
import pandiandcode.com.game.providers.ColorProvider
import pandiandcode.com.game.providers.NumberProvider
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor

val gameModule = module {
    factory { GamePresenter(get(), get()) }
    factory { StartNewGame(get()) }
    factory { VerifyColor(get()) }
    single { GameRepository(get(), get()) }
    single { GameDataSource() }
    single { ColorProvider(get()) }
    single { NumberProvider() }
}