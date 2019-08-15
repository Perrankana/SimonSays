package pandiandcode.com.game.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlin.coroutines.CoroutineContext

/**
 * Created by Rocio Ortega on 28/01/2018.
 */

@ExperimentalCoroutinesApi
var MAIN_CONTEXT: CoroutineContext = TestCoroutineDispatcher()
@ExperimentalCoroutinesApi
var BG_CONTEXT: CoroutineContext = TestCoroutineDispatcher()