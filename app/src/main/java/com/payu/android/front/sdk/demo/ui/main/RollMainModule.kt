package com.payu.android.front.sdk.demo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.payu.android.front.sdk.demo.repository.ProductRepository
import com.payu.android.front.sdk.demo.ui.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [RollMainModule.Providers::class])
abstract class RollMainModule {
    @ContributesAndroidInjector(modules = [Injectors::class])
    abstract fun bind(): RollMainActivity

    @Module
    class Providers {
        @Provides
        @IntoMap
        @ViewModelKey(RollMainViewModel::class)
        fun provideRollMainViewModel(productRepository: ProductRepository): ViewModel
                = RollMainViewModel(productRepository)
    }

    @Module
    class Injectors {
        @Provides
        fun provideRollMainViewModel(factory: ViewModelProvider.Factory, target: RollMainActivity): RollMainViewModel
                = ViewModelProvider(target, factory).get(RollMainViewModel::class.java)
    }
}