package com.ipakyulibank.mobile.di

import com.ipakyulibank.mobile.di.module.AppModule
import com.ipakyulibank.mobile.di.module.UseCaseModule
import com.ipakyulibank.mobile.di.module.UtilModule
import com.joerakhimov.sms2email.receiver.SmsBroadcastReceiver
import com.joerakhimov.sms2email.screen.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UtilModule::class, UseCaseModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(receiver: SmsBroadcastReceiver)
}