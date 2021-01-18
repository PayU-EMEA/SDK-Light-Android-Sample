package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class PayMethods(
    @SerializedName("payMethod")
    val payMethod: PayMethod
)