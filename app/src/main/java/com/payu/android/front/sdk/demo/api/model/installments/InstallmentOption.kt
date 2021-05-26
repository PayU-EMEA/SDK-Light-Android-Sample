package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class InstallmentOption(@SerializedName("id") val id: String,
                             @SerializedName("interestRate") val interestRate: Float,
                             @SerializedName("installmentFeeAmount") val installmentFeeAmount: Int,
                             @SerializedName("annualPercentageRate") val annualPercentageRate: Float,
                             @SerializedName("totalAmountDue") val totalAmountDue: Int,
                             @SerializedName("firstInstallmentAmount") val firstInstallmentAmount: Int? = 0,
                             @SerializedName("installmentAmount") val installmentAmount: Int? = 0,
                             @SerializedName("numberOfInstallments") val numberOfInstallments: Int? = 0
)