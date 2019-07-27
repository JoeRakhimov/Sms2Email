package com.ipakyulibank.mobile.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(var context: Context) {

    @Provides
    fun provideContext() = context

}