package pandiandcode.com.simonsays.di

import org.koin.dsl.module.module
import pandiandcode.com.game.*

/**
 * Created by Rocio Ortega on 14/10/2018.
 */
fun simonSaysModules() = listOf(gameModule)

val gameModule = module {
    single { GamePresenter(get()) }
    single { StartNewGameUseCase(get()) }
    single { GameRepository(get(), get()) }
    single { GameDataSource() }
    single { ColorProvider(get()) }
    single { NumberProvider() }
}