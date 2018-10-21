package pandiandcode.com.game

import com.wallapop.kernel.CoroutineContextForTest
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlin.coroutines.experimental.AbstractCoroutineContextElement
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Rocio Ortega on 28/01/2018.
 */

var MAIN_CONTEXT: CoroutineContext = CoroutineContextForTest
var BG_CONTEXT: CoroutineContext =CoroutineContextForTest