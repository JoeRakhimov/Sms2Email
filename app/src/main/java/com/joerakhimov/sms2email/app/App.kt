package com.joerakhimov.sms2email.app

import android.app.Application
import com.ipakyulibank.mobile.di.Injector

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.initAppComponent(this)
    }

}