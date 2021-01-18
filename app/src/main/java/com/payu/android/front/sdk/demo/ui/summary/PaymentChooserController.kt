package com.payu.android.front.sdk.demo.ui.summary

import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod

interface PaymentChooserController {
    val isPaymentMethodSelected: Boolean
    val isBlikAuthorizationCodeNeeded: Boolean
    val blikAuthorizationCode: String?
    val isBlikAuthorizationCodeProvided: Boolean

    fun getPaymentMethod(): PaymentMethod?
    fun cleanPaymentMethods()
}