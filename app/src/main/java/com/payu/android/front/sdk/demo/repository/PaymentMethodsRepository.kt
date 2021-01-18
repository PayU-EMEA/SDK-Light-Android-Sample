package com.payu.android.front.sdk.demo.repository

import com.payu.android.front.sdk.demo.api.PayUApi
import com.payu.android.front.sdk.demo.api.model.ocr.OrderCreateResponse
import com.payu.android.front.sdk.demo.api.model.ocr.Status
import com.payu.android.front.sdk.demo.api.model.paymentmethods.toPaymentMethod
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethodCreator
import io.reactivex.Single
import okhttp3.internal.immutableListOf
import java.util.concurrent.TimeUnit


/**
 * Payment methods repository
 * Used for retrieving payment methods depends on FETCH_DATA_FROM_BACKEND flag
 * If it is set to true repository returns mocked payment methods and use FAKE_OCR for transactions
 */
private const val FETCH_DATA_FROM_BACKEND = true

private val FAKE_OCR = OrderCreateResponse(
    extOrderId = "",
    orderId = "",
    redirectUri = "https://secure.payu.com/api/v2/token/token.json?refReqId=bf80540728655d6a3ae946499deabd94",
    status = Status(Status.WARNING_CONTINUE_CVV),
    error = "",
)

class PaymentMethodsRepository(private val authRepository: AuthenticationRepository, private val payUApi: PayUApi) {
    private val paymentMethods = immutableListOf(
        //How to use a builders in creating  paymentMethods
        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("blik")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_blik.png")
            .withStatus("ENABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Google Pay")
            .withValue("ap")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_ap.png")
            .withStatus("ENABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("p")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_p.png")
            .withStatus("ENABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("m")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_m.png")
            .withStatus("ENABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("g")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_g_off.png")
            .withStatus("DISABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("o")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_o.png")
            .withStatus("ENABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("ab")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_ab.png")
            .withStatus("ENABLED")
            .build(),

        PaymentMethodCreator.pblBuilder()
            .withName("Name")
            .withValue("wm")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_wm_off.png")
            .withStatus("DISABLED")
            .build(),

        PaymentMethodCreator.cardBuilder()
            .withBrandImageUrl("https://static.payu.com/images/mobile/mastercard.png")
            .withCardExpirationMonth("6")
            .withCardExpirationYear("2022")
            .withCardNumberMasked("54340*******4014")
            .withCardScheme("MC")
            .withValue("MASTERCARD_VALUE")
            .withPreferred(false)
            .withStatus("ACTIVE")
            .build(),

        PaymentMethodCreator.cardBuilder()
            .withBrandImageUrl("https://static.payu.com/images/mobile/maestro.png")
            .withCardExpirationMonth("9")
            .withCardExpirationYear("2020")
            .withCardNumberMasked("509980******5618")
            .withCardScheme("MC")
            .withValue("MAESTRO_VALUE")
            .withPreferred(false)
            .withStatus("ACTIVE")
            .build(),

        PaymentMethodCreator.blikPaymentBuilder()
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_blik.png")
            .withType("UID")
            .withValue("Blik_Test_Token")
            .build(),

        PaymentMethodCreator.cardBuilder()
            .withBrandImageUrl("https://static.payu.com/images/mobile/visa.png")
            .withCardExpirationMonth("12")
            .withCardExpirationYear("2018")
            .withCardNumberMasked("411111******1111")
            .withCardScheme("VS")
            .withValue("VISA_VALUE")
            .withPreferred(false)
            .withStatus("ACTIVE")
            .build(),

        PaymentMethodCreator.pexPaymentMethodBuilder()
            .withAccountNumber("")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pex_t_mobile.png")
            .withName("PayU Express")
            .withValue("TOKE_VSHC5H9XJNWH0R3E7P0QZUH5IA3")
            .withPayType("exas")
            .withPreferred(false)
            .withStatus("ACTIVE")
            .build(),

        PaymentMethodCreator.pexPaymentMethodBuilder()
            .withAccountNumber("9511...8341")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pex_mbank.png")
            .withName("mbank - Rafał Kaufmann")
            .withValue("TOKE_MXPEN8C2F6GPEDECRYF3EOBX6PA")
            .withPayType("mtex")
            .withPreferred(false)
            .withStatus("ACTIVE")
            .build(),

        PaymentMethodCreator.pexPaymentMethodBuilder()
            .withAccountNumber("2111...6221")
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pex_mbank.png")
            .withName("mbank - Ździsław Brzeziński")
            .withValue("TOKE_H8XGXSN6Q7N3YOMRMU4WREYV76F")
            .withPayType("mtex")
            .withPreferred(false)
            .withStatus("ACTIVE")
            .build()
    )

    private val bliks = immutableListOf(
        PaymentMethodCreator.blikPaymentBuilder()
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_blik.png")
            .withType("UID")
            .withValue("Blik_Test_Token")
            .build(),

        PaymentMethodCreator.blikPaymentBuilder()
            .withBrandImageUrl("https://static.payu.com/images/mobile/logos/pbl_blik.png")
            .withType("UID")
            .withValue("Blik_Test_Token_2")
            .build(),

        PaymentMethodCreator.blikAmbiguityPaymentMethodBuilder()
            .withKey("testKey")
            .withLabel("testLabel")
            .build()
    )


    fun getPaymentMethods(): Single<List<PaymentMethod>> {
        return if (FETCH_DATA_FROM_BACKEND) {
            payUApi.retrievePaymentMethods(authRepository.accessToken)
                .map { response ->
                    val list = arrayListOf<PaymentMethod>().apply {
                        response.payByLinks.forEach { add(it.toPaymentMethod()) }
                        response.cardTokens.forEach { add(it.toPaymentMethod()) }
                        response.pexTokens.forEach { add(it.toPaymentMethod()) }
                        if (response.blikTokens?.size == 1) {
                            add(response.blikTokens[0].toPaymentMethod())
                        } else {

                        }
                    }
                    list
                }
        } else {
            Single.just(paymentMethods).delay(2, TimeUnit.SECONDS)
        }
    }

    fun getBlikPaymentMethods(): Single<List<PaymentMethod>> {
        return if (FETCH_DATA_FROM_BACKEND) {
            payUApi.retrievePaymentMethods(authRepository.accessToken)
                .map { response ->
                    val list = arrayListOf<PaymentMethod>().apply {
                        response.blikTokens?.forEach { add(it.toPaymentMethod()) }
                    }
                    list
                }
        } else {
            Single.just(bliks).delay(2, TimeUnit.SECONDS)
        }
    }

    fun isPaymentMethodListFetchedFromBackend() = FETCH_DATA_FROM_BACKEND

    fun getFakeOCR() = FAKE_OCR
}