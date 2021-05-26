package com.payu.android.front.sdk.demo.api.model.installments

import com.google.gson.annotations.SerializedName

data class CardData(@SerializedName("cardNumberMasked") val cardNumberMasked: String,
                    @SerializedName("cardScheme") val cardScheme: String,
                    @SerializedName("cardProfile") val cardProfile: String,
                    @SerializedName("cardClassification") val cardClassification: String,
                    @SerializedName("cardResponseCode") val cardResponseCode: String,
                    @SerializedName("cardResponseCodeDesc") val cardResponseCodeDesc: String,
                    @SerializedName("cardEciCode") val cardEciCode: String,
                    @SerializedName("card3dsStatus") val card3dsStatus: String,
                    @SerializedName("cardBinCountry") val cardBinCountry: String,
                    @SerializedName("firstTransactionId") val firstTransactionId: String)