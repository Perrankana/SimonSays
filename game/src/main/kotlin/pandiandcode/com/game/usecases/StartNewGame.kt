package pandiandcode.com.game.usecases

import arrow.core.Option
import pandiandcode.com.game.commands.ResetGameCommand
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.repositories.ColorSequenceRepository

class StartNewGame(
    private val resetGameCommand: ResetGameCommand,
    private val colorSequenceRepository: ColorSequenceRepository
) {
    operator fun invoke(): Option<Color> {
        resetGameCommand()
        return colorSequenceRepository.createColor()
    }
}
