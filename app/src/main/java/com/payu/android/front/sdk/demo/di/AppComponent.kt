package com.payu.android.front.sdk.demo.di

import com.payu.android.front.sdk.demo.DemoApplication
import com.payu.android.front.sdk.demo.api.NetworkModule
import com.payu.android.front.sdk.demo.config.PaymentMethodsProvider
import com.payu.android.front.sdk.demo.repository.RepositoryModule
import com.payu.android.front.sdk.demo.ui.di.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    UiModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DemoApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: DemoApplication)

    fun inject(paymentMethodProvider: PaymentMethodsProvider)
}