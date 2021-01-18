package com.payu.android.front.sdk.demo.api.model.paymentmethods

import com.google.gson.annotations.SerializedName

data class CardTokens(
    @SerializedName("cardExpirationYear")
    val cardExpirationYear: String,
    @SerializedName("cardExpirationMonth")
    val cardExpirationMonth: String,
    @SerializedName("cardNumberMasked")
    val cardNumberMasked: String,
    @SerializedName("cardScheme")
    val cardScheme: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("brandImageUrl")
    val brandImageUrl: String,
    @SerializedName("preferred")
    val preferred: Boolean,
    @SerializedName("status")
    val status: String
)