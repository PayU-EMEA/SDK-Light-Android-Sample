package com.payu.android.front.sdk.demo.api.model.paymentmethods

import com.google.gson.annotations.SerializedName

data class PexTokens(
    @SerializedName("accountNumber")
    val accountNumber: String,
    @SerializedName("payType")
    val payType: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("brandImageUrl")
    val brandImageUrl: String,
    @SerializedName("preferred")
    val preferred: Boolean,
    @SerializedName("status")
    val status: String
)