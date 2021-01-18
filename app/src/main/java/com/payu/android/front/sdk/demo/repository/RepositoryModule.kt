package com.payu.android.front.sdk.demo.repository

import android.content.Context
import com.payu.android.front.sdk.demo.api.PayUApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providePersistentRepository(context: Context): PersistentRepository = PersistentRepository(context)

    @Provides
    @Singleton
    fun provideTokenRepository(): AuthenticationRepository = AuthenticationRepository()

    @Provides
    @Singleton
    fun provideProductRepository(): ProductRepository = ProductRepository()


    @Provides
    @Singleton
    fun providePaymentMethodsRepository(authRepository: AuthenticationRepository, payUApi: PayUApi): PaymentMethodsRepository
            = PaymentMethodsRepository(authRepository, payUApi)

}