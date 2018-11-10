package pandiandcode.com.game.providers

import java.util.*

class NumberProvider {
    fun getRandomNumber(from: Int, to: Int): Int = Random().nextInt((to + 1) - from) + from
}
