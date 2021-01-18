package com.payu.android.front.sdk.demo.di

import android.content.Context
import com.payu.android.front.sdk.demo.DemoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: DemoApplication): Context {
        return application
    }
}