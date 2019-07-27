package com.joerakhimov.sms2email.util.scheduler

import io.reactivex.Scheduler

interface SchedulerProvider {

    val io: Scheduler
    val ui: Scheduler
    val computation: Scheduler

}