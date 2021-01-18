package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class OrderCreateResponse(
    @SerializedName("extOrderId")
    val extOrderId: String?,
    @SerializedName("orderId")
    val orderId: String?,
    @SerializedName("redirectUri")
    val redirectUri: String?,
    @SerializedName("status")
    val status: Status?,
    @SerializedName("error")
    val error: String?
)