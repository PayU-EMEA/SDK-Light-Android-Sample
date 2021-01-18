package com.payu.android.front.sdk.demo.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {
    protected val disposables = CompositeDisposable()

    protected fun Disposable.register() {
        disposables.add(this)
    }

    fun registerDisposable(d: Disposable) {
        disposables.add(d)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}