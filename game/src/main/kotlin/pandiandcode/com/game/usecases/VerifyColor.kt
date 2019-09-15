package pandiandcode.com.game.usecases

import arrow.data.Invalid
import arrow.data.Validated
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository

class VerifyColor(
        private val colorSequenceRepository: ColorSequenceRepository,
        private val positionRepository: PositionRepository
) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
            Invalid(Unit)
}
