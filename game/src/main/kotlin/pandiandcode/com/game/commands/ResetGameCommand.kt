package pandiandcode.com.game.commands

import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository


class ResetGameCommand(
    private val colorSequenceRepository: ColorSequenceRepository,
    private val positionRepository: PositionRepository
) {
    operator fun invoke() {
        colorSequenceRepository.reset()
        positionRepository.resetSequencePosition()
    }
}