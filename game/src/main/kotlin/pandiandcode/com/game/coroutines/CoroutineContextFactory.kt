package pandiandcode.com.game.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

var MAIN_CONTEXT: CoroutineContext = Dispatchers.Main
var BG_CONTEXT: CoroutineContext = Dispatchers.IO
var DELAY = 600L