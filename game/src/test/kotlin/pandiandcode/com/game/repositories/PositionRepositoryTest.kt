package pandiandcode.com.game.repositories

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import pandiandcode.com.game.datasources.PositionDataSource

class PositionRepositoryTest {

    private val dataSource: PositionDataSource = mock()
    private val positionRepository: PositionRepository = PositionRepository(dataSource)


    @Test
    fun `should get position to validate from data source`() {
        whenever(dataSource.positionToValidate).thenReturn(POSITION)

        val resultPosition = positionRepository.getCurrentSequencePosition()

        verify(dataSource).positionToValidate
        assertEquals(POSITION, resultPosition)
    }

    companion object {
        private const val POSITION = 0
    }
}