package pandiandcode.com.game.commands

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository

class ResetGameCommandTest {
    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val resetGameCommand = ResetGameCommand(colorSequenceRepository, positionRepository)

    @Test
    fun `should reset color sequence when executed`() {
        resetGameCommand()

        verify(colorSequenceRepository).reset()
    }

    @Test
    fun `should reset sequence position to validate when executed`() {
        resetGameCommand()

        verify(positionRepository).resetSequencePosition()
    }
}