package pandiandcode.com.game.usecases

import arrow.core.Option
import arrow.core.getOrElse
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import pandiandcode.com.game.model.Color
import pandiandcode.com.game.repositories.ColorSequenceRepository
import pandiandcode.com.game.repositories.PositionRepository

class VerifyColorTest {
    private val colorSequenceRepository: ColorSequenceRepository = mock()
    private val positionRepository: PositionRepository = mock()
    private val verifyColor: VerifyColor = VerifyColor(colorSequenceRepository, positionRepository)

}
