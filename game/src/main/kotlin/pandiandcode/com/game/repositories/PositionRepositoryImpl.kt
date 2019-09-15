package pandiandcode.com.game.repositories

import pandiandcode.com.game.PositionRepository
import pandiandcode.com.game.datasources.PositionDataSource

class PositionRepositoryImpl(
        private val positionDataSource: PositionDataSource
): PositionRepository {

    override fun getCurrentSequencePosition(): Int = positionDataSource.positionToValidate

    override fun incrementSequencePosition() {
        positionDataSource.positionToValidate++
    }

    override fun resetSequencePosition() {
        positionDataSource.positionToValidate = 0
    }
}
