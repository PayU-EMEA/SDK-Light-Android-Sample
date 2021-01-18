package com.payu.android.front.sdk.demo.api.model.paymentmethods

import com.payu.android.front.sdk.payment_library_payment_methods.model.*

fun PayByLinks.toPaymentMethod(): PayByLinkPaymentMethod = PaymentMethodCreator.pblBuilder()
    .withName(name)
    .withValue(value)
    .withBrandImageUrl(brandImageUrl)
    .withStatus(status)
    .build()

fun CardTokens.toPaymentMethod(): CardPaymentMethod = PaymentMethodCreator.cardBuilder()
    .withBrandImageUrl(brandImageUrl)
    .withCardExpirationMonth(cardExpirationMonth)
    .withCardExpirationYear(cardExpirationYear)
    .withCardNumberMasked(cardNumberMasked)
    .withCardScheme(cardScheme)
    .withValue(value)
    .withPreferred(preferred)
    .withStatus(status)
    .build()

fun PexTokens.toPaymentMethod(): PexPaymentMethod = PaymentMethodCreator.pexPaymentMethodBuilder()
    .withAccountNumber(accountNumber)
    .withBrandImageUrl(brandImageUrl)
    .withName(name)
    .withValue(value)
    .withPayType(payType)
    .withPreferred(preferred)
    .withStatus(status)
    .build()

fun BlikTokens.toPaymentMethod(): BlikPaymentMethod = PaymentMethodCreator.blikPaymentBuilder()
    .withBrandImageUrl(brandImageUrl)
    .withType(type)
    .withValue(value)
    .build()