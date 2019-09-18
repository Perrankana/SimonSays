package pandiandcode.com.game.usecases

import com.nhaarman.mockitokotlin2.mock
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository

class VerifyColorTest {

    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(colorSequenceRepository, positionRepository)

}
