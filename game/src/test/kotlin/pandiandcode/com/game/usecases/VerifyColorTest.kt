package pandiandcode.com.game.usecases

import arrow.core.Try
import arrow.data.getOrElse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pandiandcode.com.game.repositories.GameRepository
import pandiandcode.com.game.model.Color

class VerifyColorTest {

    private val repository: GameRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(repository)

    @Test
    fun `should return invalid when color is not correct`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isInvalid)
    }

    @Test
    fun `should return valid when color is correct`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Green))
        whenever(repository.generateColor()).thenReturn(Try.just(Color.Red))
        whenever(repository.getAllColorsGame()).thenReturn(listOf(Color.Green, Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isValid)
    }

    @Test
    fun `should return list of colors when color is correct and it is end of sequence`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Green))
        whenever(repository.getAllColorsGame()).thenReturn(
            listOf(Color.Green),
            listOf(Color.Green, Color.Red)
        )
        whenever(repository.getCurrentGameSequencePosition()).thenReturn(0)
        whenever(repository.generateColor()).thenReturn(Try.just(Color.Red))


        val result = verifyColor(Color.Green)

        assertTrue(result.isValid)
        assertEquals(listOf(Color.Green, Color.Red), result.getOrElse { emptyList() })
    }

    @Test
    fun `should reset game when color is not correct`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Red))

        verifyColor(Color.Green)

        verify(repository).resetGame()
    }

    @Test
    fun `should return empty list when the color is correct and it is not the end of the sequence`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Green))
        whenever(repository.generateColor()).thenReturn(Try.just(Color.Red))
        whenever(repository.getAllColorsGame()).thenReturn(listOf(Color.Green, Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isValid)
        assertEquals(emptyList<Color>(), result.getOrElse { listOf(Color.Red) })
    }

    @Test
    fun `should increment game sequence when is not end of sequence`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Green))
        whenever(repository.generateColor()).thenReturn(Try.just(Color.Red))
        whenever(repository.getAllColorsGame()).thenReturn(listOf(Color.Green, Color.Red))

        verifyColor(Color.Green)

        verify(repository).incrementGameSequencePosition()
    }

    @Test
    fun `should reset game sequence when it is end of sequence`() {
        whenever(repository.getColorToValidate()).thenReturn(Try.just(Color.Green))
        whenever(repository.getAllColorsGame()).thenReturn(
            listOf(Color.Green),
            listOf(Color.Green, Color.Red)
        )
        whenever(repository.getCurrentGameSequencePosition()).thenReturn(0)
        whenever(repository.generateColor()).thenReturn(Try.just(Color.Red))

        verifyColor(Color.Green)

        verify(repository).resetGameSequencePosition()
    }
}
