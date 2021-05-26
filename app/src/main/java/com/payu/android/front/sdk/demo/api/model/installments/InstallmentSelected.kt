package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class InstallmentSelected(@SerializedName("optionId") val optionId: String? = null, // opcjonalne, wymagane dla formatu VARYING_NUMBER_OF_OPTIONS
                               @SerializedName("numberOfInstallments") val numberOfInstallments: Int? = null // opcjonalne, wymagane dla formatu VARYING_NUMBER_OF_INSTALLMENTS
)

