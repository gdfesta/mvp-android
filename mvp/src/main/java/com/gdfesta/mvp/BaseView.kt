package com.gdfesta.mvp

import android.arch.lifecycle.LifecycleOwner

/**
 * Base view for MVP Pattern
 *
 * @author gdfesta
 */
interface BaseView<in S : ViewState> : LifecycleOwner {
    fun render(state: S)
}