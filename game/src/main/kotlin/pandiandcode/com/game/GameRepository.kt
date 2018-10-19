package pandiandcode.com.game

import arrow.data.Try
import pandiandcode.com.game.model.Color

class GameRepository(private val colorProvider: ColorProvider) {
    fun getColor() : Try<Color> = Try.invoke { colorProvider.generateColor() }

}
