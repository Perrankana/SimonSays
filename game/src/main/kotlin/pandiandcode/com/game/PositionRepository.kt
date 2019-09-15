package pandiandcode.com.game

interface PositionRepository {

    fun getCurrentSequencePosition(): Int

    fun incrementSequencePosition()

    fun resetSequencePosition()
}
