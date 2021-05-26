package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class CardInstallments(
        @SerializedName("id") val id: String,
        @SerializedName("cardScheme") val cardScheme: String,
        @SerializedName("installmentOptionFormat") val installmentOptionFormat: String,// or VARYING_NUMBER_OF_INSTALLMENTS
        @SerializedName("currencyCode") val currencyCode: String,
        @SerializedName("minNumberOfInstallments") val minNumberOfInstallments: Int?,// only for VARYING_NUMBER_OF_INSTALLMENTS
        @SerializedName("maxNumberOfInstallments") val maxNumberOfInstallments: Int?, //only for VARYING_NUMBER_OF_INSTALLMENTS
        @SerializedName("installmentOptions") val installmentOptions: List<InstallmentOption>,
        @SerializedName("installmentDecision") val installmentDecision: InstallmentDecision?
)