package pandiandcode.com.game.providers

import pandiandcode.com.game.model.Color

class ColorProvider(
        private val numberProvider: NumberProvider
) {
    fun generateColor(): Color =
            when (numberProvider.getRandomNumber(from = 1, to = 4)) {
                1 -> Color.Green
                2 -> Color.Red
                3 -> Color.Yellow
                4 -> Color.Blue
                else -> throw Exception()
            }
}
