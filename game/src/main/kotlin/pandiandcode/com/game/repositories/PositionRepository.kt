package pandiandcode.com.game.repositories

interface PositionRepository {

    fun getCurrentSequencePosition(): Int

    fun incrementSequencePosition()

    fun resetSequencePosition()
}