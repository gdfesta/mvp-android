package com.gdfesta.mvp

import android.arch.lifecycle.*
import io.reactivex.disposables.CompositeDisposable

/**
 * Base Present for MVP Pattern
 *
 * @author gdfesta
 */
open class BasePresenter<V : BaseView<S>, S : ViewState, out SVM : BaseViewModel<S>>(protected val stateViewModel: SVM) : LifecycleObserver, Observer<S> {
    protected val viewStateDisposables = CompositeDisposable()
    protected var view: V? = null

    /**
     * Due to [android.arch.lifecycle.ReflectiveGenericLifecycleObserver] error we can not receive [V] as parameter
     * we must receive [LifecycleOwner] and cast it to [V]
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun attachView(source: LifecycleOwner) {
        this.view = source as V
        stateViewModel.observe(source, this)
        doOnAttached()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachView() {
        this.viewStateDisposables.clear()
        doOnDetached()
        this.view = null
    }

    override final fun onChanged(t: S?) {
        view?.render(t!!)
    }

    open fun doOnAttached() = Unit
    open fun doOnDetached() = Unit
}