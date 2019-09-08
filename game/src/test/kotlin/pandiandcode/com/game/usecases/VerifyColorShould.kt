package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import pandiandcode.com.game.GameRepository

class VerifyColorShould {

    private val repository: GameRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(repository)

    fun `return invalid when color is not correct`() {

    }

    fun `return valid when color is correct`() {

    }

    fun `return list of colors when color is correct and it is end of sequence`() {

    }

    fun `reset game when color is not correct`() {

    }

    fun `return empty list when the color is correct and it is not the end of the sequence`() {

    }

    fun `increment game sequence when is not end of sequence`() {

    }

    fun `reset game sequence when it is end of sequence`() {

    }
}
