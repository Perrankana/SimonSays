package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import pandiandcode.com.game.commands.ResetGameCommand
import pandiandcode.com.game.repositories.ColorSequenceRepository

class StartNewGameTest {
    private val resetGameCommand: ResetGameCommand = mock()
    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val useCase: StartNewGame = StartNewGame(resetGameCommand, colorSequenceRepository)

    @Test
    fun `should get new color when it is executed`() {
        useCase()

        verify(colorSequenceRepository).createColor()
    }

    @Test
    fun `should reset any started game when it is executed`(){
        useCase()

        verify(resetGameCommand).invoke()
    }
}