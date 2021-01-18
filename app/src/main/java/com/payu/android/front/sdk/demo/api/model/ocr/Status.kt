package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("statusCode")
    val statusCode: String
) {
    companion object {
        const val SUCCESS = "SUCCESS"
        const val WARNING_CONTINUE_3DS = "WARNING_CONTINUE_3DS"
        const val WARNING_CONTINUE_CVV = "WARNING_CONTINUE_CVV"
    }
}