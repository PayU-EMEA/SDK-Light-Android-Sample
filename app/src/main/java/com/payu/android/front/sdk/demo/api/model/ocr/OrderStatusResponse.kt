package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class OrderStatusResponse(
        @SerializedName("orderId") val orderId: String,
        @SerializedName("extOrderId") val extOrderId: String,
        @SerializedName("orderCreateDate") val data: String,
        @SerializedName("notifyUrl") val url: String,
        @SerializedName("customerIp") val customerIp: String,
        @SerializedName("description") val description: String,
        @SerializedName("currencyCode") val currencyCode: String,
        @SerializedName("totalAmount") val totalAmount: String,
        @SerializedName("status") val status: String,
        @SerializedName("products")val productList: List<Product>
        )
