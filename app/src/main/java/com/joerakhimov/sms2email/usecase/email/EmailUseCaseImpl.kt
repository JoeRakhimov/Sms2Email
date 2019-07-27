package com.joerakhimov.sms2email.usecase.email

import com.ipakyulibank.mobile.data.preferences.Prefs
import com.joerakhimov.sms2email.util.email.GMailSender
import com.joerakhimov.sms2email.util.scheduler.SchedulerProvider
import io.reactivex.Single

class EmailUseCaseImpl(val prefs: Prefs, private val schedulerProvider: SchedulerProvider) : EmailUseCase {

    override fun sendEmail(
        senderGmailEmail: String,
        senderGmailPassword: String,
        receiverEmail: String,
        senderNumber: String,
        messageBody: String
    ): Single<Unit> {

        return Single.fromCallable {

            val sender = GMailSender(
                senderGmailEmail,
                senderGmailPassword
            )

            sender.sendMail(
                senderNumber,
                messageBody,
                senderGmailEmail,
                receiverEmail
            )

        }.subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)

    }

}