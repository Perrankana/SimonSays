package pandiandcode.com.game.di


import kotlinx.coroutines.FlowPreview
import org.koin.dsl.module
import pandiandcode.com.game.datasources.PositionDataSource
import pandiandcode.com.game.GamePresenter
import pandiandcode.com.game.commands.ResetGameCommand
import pandiandcode.com.game.datasources.ColorSequenceDataSource
import pandiandcode.com.game.PositionRepository
import pandiandcode.com.game.providers.ColorProvider
import pandiandcode.com.game.providers.NumberProvider
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.repositories.ColorSequenceRepositoryImpl
import pandiandcode.com.game.repositories.PositionRepositoryImpl
import pandiandcode.com.game.usecases.StartNewGame
import pandiandcode.com.game.usecases.VerifyColor

@FlowPreview
val gameModule = module {
    factory { GamePresenter(get(), get()) }
    factory { StartNewGame(get(), get()) }
    factory { VerifyColor(get(), get()) }
    factory { ResetGameCommand(get(), get()) }
    single<PositionRepository> { PositionRepositoryImpl(get()) }
    single<ColorSequenceRepository> { ColorSequenceRepositoryImpl(get(), get()) }
    single { PositionDataSource() }
    single { ColorSequenceDataSource() }
    single { ColorProvider(get()) }
    single { NumberProvider() }
}