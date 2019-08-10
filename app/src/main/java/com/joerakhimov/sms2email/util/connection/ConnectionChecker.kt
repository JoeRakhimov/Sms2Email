package com.joerakhimov.sms2email.util.connection

import androidx.work.ListenableWorker
import com.joerakhimov.sms2email.util.scheduler.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import java.net.InetAddress

class ConnectionChecker(val schedulerProvider: SchedulerProvider) {

    fun getConnectionChecker() = Single.fromCallable {
        val address = InetAddress.getByName("google.com")
        !address.equals("")
    }.subscribeOn(schedulerProvider.io)
        .observeOn(schedulerProvider.ui)
        .onErrorReturn { false }

}