package com.payu.android.front.sdk.demo.repository

import com.payu.android.front.sdk.demo.api.PayUApi
import com.payu.android.front.sdk.demo.api.model.installments.CardInstallments
import com.payu.android.front.sdk.demo.api.model.installments.InstallmentSelected
import com.payu.android.front.sdk.demo.api.model.installments.TransactionData
import com.payu.android.front.sdk.demo.api.model.ocr.OrdersStatusResponse
import io.reactivex.Completable
import io.reactivex.Single

class InstallmentRepository(private val payUApi: PayUApi, private val authenticationRepository: AuthenticationRepository) {

    fun getOrderDetails(orderId: String): Single<OrdersStatusResponse> {
        return payUApi.retrieveOrdersData(authenticationRepository.accessToken, orderId);
    }

    fun getTransactionDetails(orderId: String): Single<TransactionData> {
        return payUApi.retrieveTransactionData(authenticationRepository.accessToken, orderId);
    }

    fun getInstallmentOption(proposalId: String): Single<CardInstallments> {
        return payUApi.retrieveInstallmentOptions(authenticationRepository.accessToken, proposalId)
    }

    fun selectInstallmentOption(installmentSelected: InstallmentSelected, proposalId: String): Completable {
        return payUApi.selectInstallmentOption(authenticationRepository.accessToken, proposalId, installmentSelected)
    }
}
