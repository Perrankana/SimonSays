package pandiandcode.com.game.usecases

import arrow.data.Invalid
import arrow.data.Validated
import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColor(private val gameRepository: GameRepository) {
    operator fun invoke(color: Color): Validated<Unit, List<Color>> =
            Invalid(Unit)
}
