package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class TransactionData(@SerializedName("transactions") val transactions: List<Transaction>) {
}