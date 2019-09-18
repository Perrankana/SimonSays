package pandiandcode.com.game.usecases

import arrow.core.Try
import arrow.data.getOrElse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository
import pandiandcode.com.game.model.Color

class VerifyColorTest {

    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(colorSequenceRepository, positionRepository)

    @Test
    fun `should return invalid when color is not correct`() {
        val position = 0
        whenever(positionRepository.getCurrentSequencePosition())
            .thenReturn(position)
        whenever(colorSequenceRepository.getColorAt(position))
            .thenReturn(Try.just(Color.Red))

        val result = verifyColor(Color.Green)

        assert(result.isInvalid)
    }

    @Test
    fun `should return valid when color is correct`() {
        val position = 0
        whenever(positionRepository.getCurrentSequencePosition())
            .thenReturn(position)
        whenever(colorSequenceRepository.getColorAt(position))
            .thenReturn(
                Try.just(Color.Green)
            )

        val result = verifyColor(Color.Green)

        assert(result.isValid)
    }

    @Test
    fun `should return list of colors when color is correct and it is end of sequence`() {
        val position = 1
        whenever(positionRepository.getCurrentSequencePosition())
            .thenReturn(position)
        whenever(colorSequenceRepository.getColorAt(position)).thenReturn(
            Try.just(Color.Green)
        )
        whenever(colorSequenceRepository.getColorsSequence())
            .thenReturn(
                listOf(Color.Red, Color.Green),
                listOf(Color.Red, Color.Green, Color.Blue)
            )
        whenever(colorSequenceRepository.createColor())
            .thenReturn(Try.just(Color.Blue))

        val result = verifyColor(Color.Green)

        assertEquals(listOf(Color.Red, Color.Green, Color.Blue), result.getOrElse { emptyList() })
    }

    @Test
    fun `should return empty list when the color is correct and it is not the end of the sequence`() {
        val position = 0
        whenever(positionRepository.getCurrentSequencePosition())
            .thenReturn(position)
        whenever(colorSequenceRepository.getColorAt(position))
            .thenReturn(
                Try.just(Color.Green)
            )
        whenever(colorSequenceRepository.getColorsSequence())
            .thenReturn(
                listOf(Color.Green, Color.Yellow)
            )

        val result = verifyColor(Color.Green)

        assertEquals(emptyList<Color>(), result.getOrElse { listOf(Color.Green) })
    }

    @Test
    fun `should increment position when is not end of sequence`() {
         whenever(positionRepository.getCurrentSequencePosition())
                         .thenReturn(0)
         whenever(colorSequenceRepository.getColorAt(0))
                         .thenReturn(Try.just(Color.Green))

         whenever(colorSequenceRepository.getColorsSequence())
                         .thenReturn(
                                 listOf(Color.Green, Color.Red)
                         )


        verifyColor(Color.Green)

        verify(positionRepository).incrementSequencePosition()
    }

    @Test
    fun `should reset position when it is end of sequence`() {
        whenever(positionRepository.getCurrentSequencePosition())
                        .thenReturn(0)
        whenever(colorSequenceRepository.getColorAt(0))
                        .thenReturn(Try.just(Color.Green))

        whenever(colorSequenceRepository.getColorsSequence())
                        .thenReturn(
                                listOf(Color.Green),
                                listOf(Color.Green, Color.Red)
                        )
        whenever(colorSequenceRepository.createColor())
                        .thenReturn(Try.just(Color.Red))

        verifyColor(Color.Green)

        verify(positionRepository).resetSequencePosition()
    }
}
