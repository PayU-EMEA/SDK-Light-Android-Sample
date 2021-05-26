package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class Card(@SerializedName("cardData") val cardData: CardData,
                @SerializedName("cardInstallmentProposal") val cardInstallmentProposal: CardInstallmentProposal?)



