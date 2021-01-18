package com.payu.android.front.sdk.demo.ui.main

import com.payu.android.front.sdk.demo.model.RollModel
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.ProductRepository
import com.payu.android.front.sdk.demo.ui.base.BaseViewModel
import com.payu.android.front.sdk.demo.ui.base.SingleLiveEvent

class RollMainViewModel(private val productRepository: ProductRepository) : BaseViewModel() {
    val productFetchedEvent = SingleLiveEvent<List<RollModel>>()

    fun fetchProducts() {
        productFetchedEvent.value = productRepository.provideData()
    }
}