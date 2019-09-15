package pandiandcode.com.game.commands

import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository

class ResetGameCommand(
        private val colorSequenceRepository: ColorSequenceRepository,
        private val positionRepository: PositionRepository
) {
    operator fun invoke() {
        colorSequenceRepository.reset()
        positionRepository.resetSequencePosition()
    }
}