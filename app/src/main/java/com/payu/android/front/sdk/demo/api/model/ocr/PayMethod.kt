package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class PayMethod(
    @SerializedName("type")
    val type: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("authorizationCode")
    val authorizationCode: String? = null,
    @SerializedName("blikData")
    val blikData: BlikData? = null
)