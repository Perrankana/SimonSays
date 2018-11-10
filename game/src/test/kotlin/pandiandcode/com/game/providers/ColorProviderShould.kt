package pandiandcode.com.game.providers

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.providers.ColorProvider
import pandiandcode.com.game.providers.NumberProvider

/**
 * Created by Rocio Ortega on 19/10/2018.
 */
class ColorProviderShould {

    private val numberProvider: NumberProvider = mock()
    private val colorProvider: ColorProvider = ColorProvider(numberProvider)

    @Test
    fun `return color green if random number is 1`() {
        whenever(numberProvider.getRandomNumber(from = 1, to = 4)).thenReturn(1)

        val result = colorProvider.generateColor()

        assertEquals(Color.Green, result)
    }

    @Test
    fun `return color red if random number is 2`() {
        whenever(numberProvider.getRandomNumber(from = 1, to = 4)).thenReturn(2)

        val result = colorProvider.generateColor()

        assertEquals(Color.Red, result)
    }

    @Test
    fun `return color yellow if random number is 3`() {
        whenever(numberProvider.getRandomNumber(from = 1, to = 4)).thenReturn(3)

        val result = colorProvider.generateColor()

        assertEquals(Color.Yellow, result)
    }

    @Test
    fun `return color blue if random number is 4`() {
        whenever(numberProvider.getRandomNumber(from = 1, to = 4)).thenReturn(4)

        val result = colorProvider.generateColor()

        assertEquals(Color.Blue, result)
    }
}