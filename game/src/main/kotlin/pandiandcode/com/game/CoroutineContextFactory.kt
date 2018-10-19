package pandiandcode.com.game

import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Rocio Ortega on 28/01/2018.
 */

var MAIN_CONTEXT: CoroutineContext = UI + UncaughtCoRoutineExceptionHandler()
var BG_CONTEXT: CoroutineContext = newFixedThreadPoolContext(2 * Runtime.getRuntime().availableProcessors(), "bg") +
        UncaughtCoRoutineExceptionHandler()

class UncaughtCoRoutineExceptionHandler :
        CoroutineExceptionHandler, AbstractCoroutineContextElement(CoroutineExceptionHandler.Key) {
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.printStackTrace()
    }
}