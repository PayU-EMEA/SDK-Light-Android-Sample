package com.payu.android.front.sdk.demo.config

import android.content.Context
import android.content.Intent
import com.payu.android.front.sdk.demo.DemoApplication
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.PaymentMethodsRepository
import com.payu.android.front.sdk.demo.ui.login.LoginActivity
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PaymentMethodsCallback
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.external.listener.PosIdListener
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.providers.PaymentMethodActions
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Payment list to populate test application
 */
class PaymentMethodsProvider(context: Context) : PaymentMethodActions(context) {
    @Inject
    lateinit var paymentMethodsRepository: PaymentMethodsRepository

    @Inject
    lateinit var authenticationRepository: AuthenticationRepository

    init {
        val demoApplication = context.applicationContext as DemoApplication
        demoApplication.appComponent.inject(this)
    }

    private fun startLoginActivity() {
        context.startActivity(
                Intent(context, LoginActivity::class.java)
                        .apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK })
    }

    override fun providePaymentMethods(callback: PaymentMethodsCallback) {
        val disposable = paymentMethodsRepository.getPaymentMethods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { callback.onFetched(it) },
                        {
                            println("Error during fetching payment methods: $it")
                            startLoginActivity()
                        }
                )
    }

    override fun onPaymentMethodRemoved(paymentMethod: PaymentMethod) {
        //call to backend to remove  payment method
    }

    override fun providePosId(posIdListener: PosIdListener) {
        posIdListener.onPosId(authenticationRepository.posId)
    }

    override fun provideBlikPaymentMethods(callback: PaymentMethodsCallback) {
        val disposable = paymentMethodsRepository.getBlikPaymentMethods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { callback.onFetched(it) },
                        {
                            println("Error during fetching blik payment methods: $it")
                            startLoginActivity()
                        }
                )
    }
}
