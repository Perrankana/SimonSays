package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository

class VerifyColorTest {

    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(colorSequenceRepository, positionRepository)

    fun `should return invalid when color is not correct`() {

    }

    fun `should return valid when color is correct`() {

    }

    fun `should return list of colors when color is correct and it is end of sequence`() {

    }

    fun `should return empty list when the color is correct and it is not the end of the sequence`() {

    }

    fun `should increment position when is not end of sequence`() {

    }

    fun `should reset position when it is end of sequence`() {

    }
}
