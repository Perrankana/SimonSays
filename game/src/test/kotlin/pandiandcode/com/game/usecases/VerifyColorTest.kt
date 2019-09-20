package pandiandcode.com.game.usecases

import arrow.core.Try
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import pandiandcode.com.game.ColorSequenceRepository
import pandiandcode.com.game.PositionRepository
import pandiandcode.com.game.model.Color.Green
import pandiandcode.com.game.model.Color.Blue
import pandiandcode.com.game.model.Color.Red
import pandiandcode.com.game.model.Color.Yellow

class VerifyColorTest {

    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(colorSequenceRepository, positionRepository)


}
