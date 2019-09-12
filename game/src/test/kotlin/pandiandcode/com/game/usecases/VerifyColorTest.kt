package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import pandiandcode.com.game.repositories.GameRepository

class VerifyColorTest {

    private val repository: GameRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(repository)

    fun `should return invalid when color is not correct`() {

    }

    fun `should return valid when color is correct`() {

    }

    fun `should return list of colors when color is correct and it is end of sequence`() {

    }

    fun `should return empty list when the color is correct and it is not the end of the sequence`() {

    }

    fun `should increment game sequence when is not end of sequence`() {

    }

    fun `should reset game sequence when it is end of sequence`() {

    }
}
