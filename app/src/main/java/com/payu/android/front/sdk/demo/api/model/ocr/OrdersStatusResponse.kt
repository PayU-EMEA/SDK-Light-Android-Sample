package com.payu.android.front.sdk.demo.api.model.ocr

import com.google.gson.annotations.SerializedName

data class OrdersStatusResponse(@SerializedName("orders") val orderStatusResponses: List<OrderStatusResponse>) {
}