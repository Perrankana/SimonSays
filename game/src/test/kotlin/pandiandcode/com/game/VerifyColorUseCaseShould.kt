package pandiandcode.com.game

import arrow.data.Try
import arrow.data.getOrElse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 21/10/2018.
 */
class VerifyColorUseCaseShould {

    private val gameRepository: GameRepository = mock()
    private val verifyColorUseCase: VerifyColorUseCase = VerifyColorUseCase(gameRepository)

    @Test
    fun `return invalid when color is not correct`() {
        whenever(gameRepository.getColor()).thenReturn(Try.pure(Color.Green))

        val result = verifyColorUseCase.execute(Color.Red)

        assertTrue(result.isInvalid)
    }

    @Test
    fun `return valid when color is correct`() {
        whenever(gameRepository.getColor()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(Try.pure(listOf(Color.Red)))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))

        val result = verifyColorUseCase.execute(Color.Red)

        assertTrue(result.isValid)
    }

    @Test
    fun `return list of colors when color is correct`() {
        whenever(gameRepository.getColor()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(Try.pure(listOf(Color.Red)))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))

        val result = verifyColorUseCase.execute(Color.Red)

        assertTrue(result.isValid)
        assertEquals(listOf(Color.Red, Color.Green), result.getOrElse { emptyList() })
    }
}
