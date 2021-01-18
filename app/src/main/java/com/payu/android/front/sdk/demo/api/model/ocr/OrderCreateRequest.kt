package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

const val CONTINUE_URL = "https://www.payu.pl"

data class CreateOrderRequest(
    @SerializedName("continueUrl")
    val continueUrl: String = CONTINUE_URL,
    @SerializedName("buyer")
    val buyer: Buyer? = null,
    @SerializedName("currencyCode")
    val currencyCode: String,
    @SerializedName("customerIp")
    val customerIp: String = "127.0.0.1",
    @SerializedName("description")
    var description: String,
    @SerializedName("merchantPosId")
    val merchantPosId: String,
    @SerializedName("notifyUrl")
    val notifyUrl: String? = null,
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("totalAmount")
    val totalAmount: String,
    @SerializedName("payMethods")
    val payMethods: PayMethods?,
)