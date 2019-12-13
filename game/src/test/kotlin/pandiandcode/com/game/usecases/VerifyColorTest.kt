package pandiandcode.com.game.usecases

import arrow.core.Option
import arrow.core.getOrElse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository
import pandiandcode.com.game.model.Color

class VerifyColorTest {
    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(colorSequenceRepository, positionRepository)

    @Test
    fun `should return invalid when color is not correct`() {
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0)).thenReturn(Option.just(Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isInvalid)
    }

    @Test
    fun `should return valid when color is correct`() {
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0)).thenReturn(Option.just(Color.Green))
        whenever(colorSequenceRepository.createColor()).thenReturn(Option.just(Color.Red))
        whenever(colorSequenceRepository.getColorsSequence()).thenReturn(listOf(Color.Green, Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isValid)
    }

    @Test
    fun `should return list of colors when color is correct and it is end of sequence`() {
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0)).thenReturn(Option.just(Color.Green))
        whenever(colorSequenceRepository.getColorsSequence()).thenReturn(
            listOf(Color.Green),
            listOf(Color.Green, Color.Red)
        )
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.createColor()).thenReturn(Option.just(Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isValid)
        assertEquals(listOf(Color.Green, Color.Red), result.getOrElse { emptyList() })
    }

    @Test
    fun `should return empty list when the color is correct and it is not the end of the sequence`() {
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0)).thenReturn(Option.just(Color.Green))
        whenever(colorSequenceRepository.getColorsSequence()).thenReturn(listOf(Color.Green, Color.Red))

        val result = verifyColor(Color.Green)

        assertTrue(result.isValid)
        assertEquals(emptyList<Color>(), result.getOrElse { listOf(Color.Red) })
    }

    @Test
    fun `should increment position when is not end of sequence`() {
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0)).thenReturn(Option.just(Color.Green))
        whenever(colorSequenceRepository.getColorsSequence()).thenReturn(listOf(Color.Green, Color.Red))

        verifyColor(Color.Green)

        verify(positionRepository).incrementSequencePosition()
    }

    @Test
    fun `should reset position when it is end of sequence`() {
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0)).thenReturn(Option.just(Color.Green))
        whenever(colorSequenceRepository.getColorsSequence()).thenReturn(
                listOf(Color.Green),
                listOf(Color.Green, Color.Red)
        )
        whenever(positionRepository.getCurrentSequencePosition()).thenReturn(0)
        whenever(colorSequenceRepository.createColor()).thenReturn(Option.just(Color.Red))

        verifyColor(Color.Green)

        verify(positionRepository).resetSequencePosition()
    }
}
