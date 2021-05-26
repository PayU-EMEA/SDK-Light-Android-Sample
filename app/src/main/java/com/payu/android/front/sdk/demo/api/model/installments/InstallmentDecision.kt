package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class InstallmentDecision(
        @SerializedName("optionId") val id: String,
        @SerializedName("numberOfInstallments") val numberOfInstallments: Int,
        @SerializedName("createTime") val createTime: String
)