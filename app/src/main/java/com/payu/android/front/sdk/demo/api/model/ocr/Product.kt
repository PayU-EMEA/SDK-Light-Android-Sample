package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("unitPrice")
    val unitPrice: String
)