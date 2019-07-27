package com.joerakhimov.sms2email.usecase.email

import io.reactivex.Single

interface EmailUseCase {

    fun sendEmail(
        senderGmailEmail: String,
        senderGmailPassword: String,
        receiverEmail: String,
        senderNumber: String,
        messageBody: String
    ): Single<Unit>

}