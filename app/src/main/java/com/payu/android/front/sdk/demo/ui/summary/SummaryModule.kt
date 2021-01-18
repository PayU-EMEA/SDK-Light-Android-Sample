package com.payu.android.front.sdk.demo.ui.summary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.payu.android.front.sdk.demo.api.PayUApi
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.PaymentMethodsRepository
import com.payu.android.front.sdk.demo.ui.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [SummaryModule.Providers::class])
abstract class SummaryModule {
    @ContributesAndroidInjector(modules = [Injectors::class])
    abstract fun bind(): RollSummaryActivity

    @Module
    class Providers {
        @Provides
        @IntoMap
        @ViewModelKey(SummaryViewModel::class)
        fun provideSummaryViewModel(
            authRepository: AuthenticationRepository,
            paymentMethodsRepository: PaymentMethodsRepository,
            api: PayUApi,
            gson: Gson
        ): ViewModel = SummaryViewModel(authRepository, paymentMethodsRepository, api, gson)
    }

    @Module
    class Injectors {
        @Provides
        fun provideSummaryViewModel(factory: ViewModelProvider.Factory, target: RollSummaryActivity): SummaryViewModel
                = ViewModelProvider(target, factory).get(SummaryViewModel::class.java)
    }
}