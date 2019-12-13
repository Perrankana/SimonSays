package pandiandcode.com.game.usecases

import arrow.core.Invalid
import arrow.core.Option
import arrow.core.Valid
import arrow.core.Validated
import arrow.core.toOption
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
