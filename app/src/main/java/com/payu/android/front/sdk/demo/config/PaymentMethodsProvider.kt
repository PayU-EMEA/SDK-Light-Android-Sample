package com.payu.android.front.sdk.demo.config

import android.content.Context
import android.content.Intent
import com.payu.android.front.sdk.demo.DemoApplication
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.InstallmentRepository
import com.payu.android.front.sdk.demo.repository.PaymentMethodsRepository
import com.payu.android.front.sdk.demo.repository.PersistentRepository
import com.payu.android.front.sdk.demo.ui.login.LoginActivity
import com.payu.android.front.sdk.payment_library_core.external.listener.InstallmentCallback
import com.payu.android.front.sdk.payment_library_core.external.model.Installment
import com.payu.android.front.sdk.payment_library_core.external.model.InstallmentOption
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

    @Inject
    lateinit var persistenceRepository: PersistentRepository

    @Inject
    lateinit var installmentRepository: InstallmentRepository

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

    override fun provideInstallments(callback: InstallmentCallback) {
        val disposable =
            installmentRepository.getInstallmentOption(persistenceRepository.proposalId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        if (it.installmentDecision != null) {
                            //merchant could display an information regarding this flow
                            println("Installment previously taken: $it")
                            return@subscribe
                        }

                        val installmentList: ArrayList<InstallmentOption> = ArrayList()
                        it.installmentOptions.forEach { item ->
                            installmentList.add(
                                InstallmentOption.Builder()
                                    .withId(item.id)
                                    .withFirstInstallments(item.firstInstallmentAmount ?: 0)
                                    .withNumberOfInstallments(item.numberOfInstallments ?: 0)
                                    .withTotalValue(item.totalAmountDue)
                                    .withAnnualPercentageRate(item.annualPercentageRate)
                                    .withInstallmentAmount(item.installmentAmount ?: 0)
                                    .withInstallmentFeeAmount(item.installmentFeeAmount)
                                    .withInterestRate(item.interestRate)
                                    .build()
                            )

                        }

                        val installment: Installment = Installment.Builder()
                            .withCurrency(it.currencyCode)
                            .withProposalId(persistenceRepository.proposalId)
                            .withInstallmentType(it.installmentOptionFormat)
                            .withInstallmentOptionList(installmentList)
                            .withMaxNumberOfInstallments(it.maxNumberOfInstallments ?: 0)
                            .withMinNumberOfInstallments(it.minNumberOfInstallments ?: 0)
                            .build()
                        callback.onFetched(installment)

                    }, {
                        println("Error during fetching installments: $it")
                    })

    }
}
