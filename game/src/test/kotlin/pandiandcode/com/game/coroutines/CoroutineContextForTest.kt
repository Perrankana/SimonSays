package com.wallapop.kernel

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

object CoroutineContextForTest : CoroutineDispatcher() {
    override fun isDispatchNeeded(context: CoroutineContext): Boolean = false

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }

    override fun toString(): String = "Current"
}