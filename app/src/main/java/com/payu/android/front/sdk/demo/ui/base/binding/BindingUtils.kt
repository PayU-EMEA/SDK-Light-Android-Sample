package com.payu.android.front.sdk.demo.ui.base.binding

import androidx.databinding.Observable
import io.reactivex.disposables.Disposables

inline fun <reified T : Observable> T.addOnPropertyChanged(crossinline callback: (T) -> Unit) =
        object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) = callback(observable as T)
        }.also { addOnPropertyChangedCallback(it) }.let {
            Disposables.fromAction { removeOnPropertyChangedCallback(it) }
        }