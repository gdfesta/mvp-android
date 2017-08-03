package com.gdfesta.mvp

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel

/**
 * @author gdfesta
 */
open class BaseViewModel<S : ViewState> : ViewModel() {
    private val state: MutableLiveData<S> = MutableLiveData()

    fun hasValue(): Boolean = state.value != null

    fun lastState(): S? = state.value

    fun onNext(state: S) {
        this.state.value = state
    }

    fun observe(source: LifecycleOwner, observer: Observer<S>) {
        state.observe(source, observer)
    }

    fun removeObserver(observer: Observer<S>) {
        state.removeObserver(observer)
    }
}
