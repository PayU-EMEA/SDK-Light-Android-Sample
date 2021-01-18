package com.payu.android.front.sdk.demo.model

import com.payu.android.front.sdk.demo.api.model.ocr.Product

fun RollModel.toProduct() = Product(namePrice, "1", rollPrice.toString())