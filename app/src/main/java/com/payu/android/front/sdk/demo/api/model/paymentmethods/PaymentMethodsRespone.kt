package com.payu.android.front.sdk.demo.api.model.paymentmethods

import com.google.gson.annotations.SerializedName

data class PaymentMethodsRespone(
    @SerializedName("blikTokens")
    val blikTokens: List<BlikTokens>?,
    @SerializedName("cardTokens")
    val cardTokens: List<CardTokens>,
    @SerializedName("pexTokens")
    val pexTokens: List<PexTokens>,
    @SerializedName("payByLinks")
    val payByLinks: List<PayByLinks>
)