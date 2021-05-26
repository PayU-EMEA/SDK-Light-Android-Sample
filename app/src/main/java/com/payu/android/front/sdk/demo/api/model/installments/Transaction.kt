package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName
import com.payu.android.front.sdk.demo.api.model.ocr.PayMethod

data class Transaction(
        @SerializedName("card") val card: Card?,
        @SerializedName("payMethod") val payMethod: PayMethod,
        @SerializedName("paymentFlow") val paymentFlow: String,

)
