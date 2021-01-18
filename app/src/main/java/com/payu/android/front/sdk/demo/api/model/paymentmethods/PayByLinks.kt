package com.payu.android.front.sdk.demo.api.model.paymentmethods

import com.google.gson.annotations.SerializedName

data class PayByLinks(
    @SerializedName("value")
    val value: String,
    @SerializedName("brandImageUrl")
    val brandImageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("minAmount")
    val minAmount: String,
    @SerializedName("maxAmount")
    val maxAmount: String
)