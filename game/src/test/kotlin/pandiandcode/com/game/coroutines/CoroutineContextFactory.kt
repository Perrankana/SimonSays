package pandiandcode.com.game.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
var MAIN_CONTEXT: CoroutineContext = TestCoroutineDispatcher()
@ExperimentalCoroutinesApi
var BG_CONTEXT: CoroutineContext = TestCoroutineDispatcher()
var DELAY = 0L