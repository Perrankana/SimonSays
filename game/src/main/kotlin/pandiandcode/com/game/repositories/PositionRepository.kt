package pandiandcode.com.game.repositories

import pandiandcode.com.game.datasources.PositionDataSource

class PositionRepository(
        private val positionDataSource: PositionDataSource
) {

    fun getCurrentSequencePosition(): Int = positionDataSource.positionToValidate

    fun incrementSequencePosition() {
        positionDataSource.positionToValidate++
    }

    fun resetSequencePosition() {
        positionDataSource.positionToValidate = 0
    }
}