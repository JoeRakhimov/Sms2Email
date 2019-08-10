package com.joerakhimov.sms2email.work

import android.content.Context
import android.util.Log
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.di.Injector
import com.joerakhimov.sms2email.R
import com.joerakhimov.sms2email.extensions.showToast
import com.joerakhimov.sms2email.usecase.email.EmailUseCase
import io.reactivex.Single
import javax.inject.Inject

const val SENDER_NUMBER = "sender_number"
const val MESSAGE_BODY = "message_body"

class EmailSendingWork(
    val context: Context,
    val params: WorkerParameters)
    : RxWorker(context, params) {

    @Inject
    lateinit var prefs: Prefs

    @Inject
    lateinit var emailUseCase: EmailUseCase

    init {
        Injector.appComponent.inject(this)
    }

    override fun createWork(): Single<Result> {

        Log.d("Sms2EmailTag", "Sending e-mail using WorkManager")

        val senderGmailEmail = prefs.getSenderGmailEmail()
        val senderGmailPassword = prefs.getSenderGmailPassword()
        val receiverEmail = prefs.getReceiverEmail()

        val senderNumber = inputData.getString(SENDER_NUMBER)
        val messageBody = inputData.getString(MESSAGE_BODY)

        return emailUseCase.sendEmail(senderGmailEmail!!, senderGmailPassword!!, receiverEmail!!, senderNumber!!, messageBody!!)
            .map { Result.success() }
            .onErrorReturn { Result.failure() }

    }

}