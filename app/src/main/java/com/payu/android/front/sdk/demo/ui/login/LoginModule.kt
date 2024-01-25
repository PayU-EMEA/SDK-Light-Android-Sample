package com.payu.android.front.sdk.demo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.payu.android.front.sdk.demo.api.PayUApi
import com.payu.android.front.sdk.demo.repository.AuthenticationRepository
import com.payu.android.front.sdk.demo.repository.PersistentRepository
import com.payu.android.front.sdk.demo.ui.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [LoginModule.Providers::class])
abstract class LoginModule {
    @ContributesAndroidInjector(modules = [Injectors::class])
    abstract fun bind(): LoginActivity

    @Module
    class Providers {
        @Provides
        @IntoMap
        @ViewModelKey(LoginViewModel::class)
        fun provideLoginViewModel(
            api: PayUApi,
            authenticationRepository: AuthenticationRepository,
            persistentRepository: PersistentRepository
        ): ViewModel = LoginViewModel(api, authenticationRepository, persistentRepository)
    }

    @Module
    class Injectors {
        @Provides
        fun provideLoginViewModel(
            factory: ViewModelProvider.Factory,
            target: LoginActivity
        ): LoginViewModel = ViewModelProvider(target, factory).get(LoginViewModel::class.java)
    }
}