package pandiandcode.com.game.usecases

import arrow.data.Try
import arrow.data.getOrElse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pandiandcode.com.game.GameRepository
import pandiandcode.com.game.model.Color

/**
 * Created by Rocio Ortega on 21/10/2018.
 */
class VerifyColorShould {

    private val gameRepository: GameRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(gameRepository)

    @Test
    fun `return invalid when color is not correct`() {
        whenever(gameRepository.getColorToValidate()).thenReturn(Try.pure(Color.Green))

        val result = verifyColor(Color.Red)

        assertTrue(result.isInvalid)
    }

    @Test
    fun `return valid when color is correct`() {
        whenever(gameRepository.getColorToValidate()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(listOf(Color.Red))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))
        whenever(gameRepository.getCurrentGameSequencePosition()).thenReturn(0)

        val result = verifyColor(Color.Red)

        assertTrue(result.isValid)
    }

    @Test
    fun `return list of colors when color is correct`() {
        whenever(gameRepository.getColorToValidate()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(listOf(Color.Red))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))
        whenever(gameRepository.getCurrentGameSequencePosition()).thenReturn(0)

        val result = verifyColor(Color.Red)

        assertTrue(result.isValid)
        assertEquals(listOf(Color.Red, Color.Green), result.getOrElse { emptyList() })
    }

    @Test
    fun `return empty list when the color is correct and it is not the end of the sequence`() {
        whenever(gameRepository.getColorToValidate()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(listOf(Color.Red, Color.Green))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))
        whenever(gameRepository.getCurrentGameSequencePosition()).thenReturn(0)

        val result = verifyColor(Color.Red)

        assertTrue(result.isValid)
        assertEquals(emptyList<Color>(), result.getOrElse { emptyList() })
    }

    @Test
    fun `increment game sequence when is not end of sequence`() {
        whenever(gameRepository.getColorToValidate()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(listOf(Color.Red, Color.Green))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))
        whenever(gameRepository.getCurrentGameSequencePosition()).thenReturn(0)

        verifyColor(Color.Red)

        verify(gameRepository).incrementGameSequencePosition()
    }

    @Test
    fun `reset game sequence when it is end of sequence`() {
        whenever(gameRepository.getColorToValidate()).thenReturn(Try.pure(Color.Red))
        whenever(gameRepository.getAllColorsGame()).thenReturn(listOf(Color.Red))
        whenever(gameRepository.generateColor()).thenReturn(Try.pure(Color.Green))
        whenever(gameRepository.getCurrentGameSequencePosition()).thenReturn(0)

        verifyColor(Color.Red)

        verify(gameRepository).resetGameSequencePosition()
    }
}
