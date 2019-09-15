package pandiandcode.com.game.commands

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository

class ResetGameCommandTest() {
    private lateinit var colorSequenceRepository: ColorSequenceRepository
    private lateinit var positionRepository: PositionRepository
    private lateinit var resetGameCommand: ResetGameCommand

    @Before
    fun setup() {
        colorSequenceRepository = mock()
        positionRepository = mock()
        resetGameCommand = ResetGameCommand(colorSequenceRepository, positionRepository)
    }

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