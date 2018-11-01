package pandiandcode.com.game

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * Created by Rocio Ortega on 28/01/2018.
 */

var MAIN_CONTEXT: CoroutineContext = Dispatchers.Main
var BG_CONTEXT: CoroutineContext = Dispatchers.IO