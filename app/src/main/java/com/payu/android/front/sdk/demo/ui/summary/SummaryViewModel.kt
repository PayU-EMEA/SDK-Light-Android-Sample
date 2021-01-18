package com.payu.android.front.sdk.demo.ui.summary

import androidx.databinding.ObservableBoolean
import com.google.gson.Gson
import com.payu.android.front.sdk.demo.api.PayUApi
import com.payu.android.front.sdk.demo.api.model.ocr.*
import com.payu.android.front.sdk.demo.model.RollModel
import com.payu.android.front.sdk.demo.model.toProduct
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.PaymentMethodsRepository
import com.payu.android.front.sdk.demo.ui.base.BaseViewModel
import com.payu.android.front.sdk.demo.ui.base.SingleLiveEvent
import com.payu.android.front.sdk.demo.ui.base.binding.ObservableString
import com.payu.android.front.sdk.frontsdk.R
import com.payu.android.front.sdk.payment_library_core_android.events.AuthorizationDetails
import com.payu.android.front.sdk.payment_library_core_android.events.PaymentAuthorization
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentType
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.io.PrintWriter
import java.io.StringWriter

private const val DESCRIPTION_PREFIX_FOR_3DS = "dementia_praecox=true"

class SummaryViewModel(
    private val authRepository: AuthenticationRepository,
    private val paymentMethodsRepository: PaymentMethodsRepository,
    private val payUApi: PayUApi,
    private val gson: Gson
) : BaseViewModel() {
    private var authorizationDetails: AuthorizationDetails? = null

    var rollModel: RollModel? = null
    var paymentChooserController: PaymentChooserController? = null

    val posId: String
        get() = authRepository.posId

    val paymentUrl: String
        get() = authorizationDetails?.link?.get() ?: ""

    var paymentValue = ""
    val paymentValueObservable = ObservableString()
    val showClearPaymentButton = ObservableBoolean(false)
    val showProgress = ObservableBoolean(false)

    val unauthoriseEvent = SingleLiveEvent<Unit>()
    val paymentSuccessEvent = SingleLiveEvent<Int>()
    val paymentErrorEvent = SingleLiveEvent<Int>()
    val cvvValidationEvent = SingleLiveEvent<String>()
    val googlePayEvent = SingleLiveEvent<String>()
    val blikAmbiguityEvent = SingleLiveEvent<Unit>()
    val paymentEvent = SingleLiveEvent<AuthorizationDetails>()

    private fun getOrderCreateRequest(
        paymentMethod: PaymentMethod,
        authorizationCode: String?
    ): Single<CreateOrderRequest> {
        val createOrderRequest = CreateOrderRequest(
            buyer = Buyer("fuu_ggondek@objectivity.co.uk"),
            currencyCode = "PLN",
            description = "Demo App",
            merchantPosId = authRepository.posId,
            products = rollModel?.let { listOf(it.toProduct()) } ?: emptyList(),
            totalAmount = rollModel?.rollPrice.toString(),
            payMethods = getPayMethods(paymentMethod, authorizationCode))

        return Single.just(createOrderRequest)
    }


    private fun getPayMethods(
        paymentMethod: PaymentMethod,
        authorizationCode: String?
    ): PayMethods {
        val type = when (paymentMethod.paymentType) {
            PaymentType.BLIK_GENERIC,
            PaymentType.PBL,
            PaymentType.GOOGLE_PAY -> "PBL"
            PaymentType.CARD,
            PaymentType.PEX -> "CARD_TOKEN"
            PaymentType.BLIK_TOKENS,
            PaymentType.BLIK_AMBIGUITY -> "BLIK_TOKEN"
        }
        val value =
            if (paymentMethod.paymentType == PaymentType.BLIK_GENERIC) "blik" else paymentMethod.value
        return PayMethods(PayMethod(type, value, authorizationCode))
    }

    private fun prepareAuthDetails(
        orderCreateResponse: OrderCreateResponse,
        authType: PaymentAuthorization = PaymentAuthorization._3DS
    ) = orderCreateResponse.redirectUri?.let {
        AuthorizationDetails.Builder()
            .withLink(it)
            .withContinueUrl(CONTINUE_URL)
            .withAuthorizationType(authType)
            .build()
    }

    private fun handleOrderCreateResponse(
        response: Response<OrderCreateResponse>,
        paymentMethod: PaymentMethod
    ) {
        val orderCreateResponse = parseOCR(response)

        if (isOCRValid(orderCreateResponse)) {
            orderCreateResponse?.let {
                when (paymentMethod.paymentType) {
                    PaymentType.PEX -> handlePexPayment(it)
                    PaymentType.PBL -> handlePblPayment(it)
                    PaymentType.CARD -> handleCardPayment(it)
                    else -> if (!isPaymentCompleted(it)) {
                        authorizationDetails = prepareAuthDetails(it)
                        paymentEvent.value = authorizationDetails
                    }
                }
            }
        }
    }

    private fun parseOCR(response: Response<OrderCreateResponse>): OrderCreateResponse? {
        val json = response.errorBody()?.string()
        return gson.fromJson(json, OrderCreateResponse::class.java) ?: response.body()
    }

    private fun isOCRValid(orderCreateResponse: OrderCreateResponse?): Boolean {
        val successStatusList = listOf(
            Status.SUCCESS,
            Status.WARNING_CONTINUE_CVV,
            Status.WARNING_CONTINUE_3DS
        )
        if (orderCreateResponse == null || orderCreateResponse.status?.statusCode !in successStatusList) {
            paymentErrorEvent.value = R.string.payment_status_error
            return false
        }

        return if (orderCreateResponse.error == "invalid_token") {
            paymentErrorEvent.value = R.string.invalid_access_token
            unauthoriseEvent.value = null
            false
        } else {
            true
        }
    }

    private fun handlePexPayment(order: OrderCreateResponse) {
        if (!isPaymentCompleted(order)) {
            authorizationDetails = prepareAuthDetails(order, PaymentAuthorization.PEX)
            paymentEvent.value = authorizationDetails
        }
    }

    private fun handlePblPayment(order: OrderCreateResponse) {
        if (!isPaymentCompleted(order)) {
            authorizationDetails = prepareAuthDetails(order, PaymentAuthorization.PAY_BY_LINK)
            paymentEvent.value = authorizationDetails
        }
    }

    private fun handleCardPayment(order: OrderCreateResponse) {
        // todo remove after fixing payU backend
        // from here
        if (order.redirectUri.isNullOrEmpty()) {
            return
        }
        // to here
        when (order.status?.statusCode) {
            Status.SUCCESS -> {
                //transaction ended
            }
            Status.WARNING_CONTINUE_3DS -> {

                authorizationDetails = prepareAuthDetails(order, PaymentAuthorization._3DS)
                paymentEvent.value = authorizationDetails
            }
            Status.WARNING_CONTINUE_CVV -> {
                cvvValidationEvent.value = order.redirectUri
            }
            else -> {
                paymentErrorEvent.value = R.string.payment_status_error
            }
        }
    }

    private fun isPaymentCompleted(order: OrderCreateResponse): Boolean {
        if (order.status?.statusCode == Status.SUCCESS && order.redirectUri.isNullOrEmpty()) {
            paymentSuccessEvent.value = R.string.payment_status_success
            return true
        }
        return false
    }

    fun createOrder(paymentMethod: PaymentMethod, authorizationCode: String? = null) {
        getOrderCreateRequest(paymentMethod, authorizationCode)
            .toObservable()
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(Schedulers.io())
            .concatMap {
                if (paymentMethodsRepository.isPaymentMethodListFetchedFromBackend()) {
                    payUApi.createOrder(authRepository.accessToken, it).toObservable()
                } else {
                    Observable.just(Response.success(paymentMethodsRepository.getFakeOCR()))
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> handleOrderCreateResponse(response, paymentMethod) },
                { throwable ->
                    val sw = StringWriter()
                    throwable.printStackTrace(PrintWriter(sw))
                    println("ERROR: $throwable")
                    println("STACKTRACE: $sw")
                    paymentErrorEvent.value = R.string.payment_status_error
                }
            ).register()
    }

    fun pay() {
        val paymentMethod = paymentChooserController?.getPaymentMethod()

        showProgress.set(true)

        when (paymentMethod?.paymentType) {
            PaymentType.BLIK_GENERIC -> {
                if (paymentChooserController?.isBlikAuthorizationCodeProvided == true) {
                    createOrder(paymentMethod, paymentChooserController?.blikAuthorizationCode)
                } else {
                    paymentErrorEvent.value = R.string.payment_generic_blik_code_not_provided
                }
            }
            PaymentType.BLIK_TOKENS -> {
                if (paymentChooserController?.isBlikAuthorizationCodeNeeded == true) {
                    if (paymentChooserController?.isBlikAuthorizationCodeProvided == true) {
                        createOrder(
                            paymentMethod,
                            paymentChooserController?.blikAuthorizationCode
                        )
                    } else {
                        paymentErrorEvent.value =
                            R.string.payment_generic_blik_code_not_provided
                    }
                } else {
                    createOrder(paymentMethod)
                }
            }
            PaymentType.BLIK_AMBIGUITY -> {
                blikAmbiguityEvent.postValue(null)
                showProgress.set(false)
            }
            PaymentType.GOOGLE_PAY -> {
                googlePayEvent.value = posId
            }
            else -> paymentMethod?.let { createOrder(it) }
        }
    }

    fun clearPaymentMethods() {
        paymentChooserController?.cleanPaymentMethods()
    }

    fun testBlikAmbiguity() {
        blikAmbiguityEvent.value = null
    }
}

