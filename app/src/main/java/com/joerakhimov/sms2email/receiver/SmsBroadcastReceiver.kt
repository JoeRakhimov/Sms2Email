package com.joerakhimov.sms2email.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.di.Injector
import com.joerakhimov.sms2email.extensions.showToast
import com.joerakhimov.sms2email.util.email.GMailSender
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.os.Build
import android.support.v4.app.NotificationCompat.getExtras
import android.os.Bundle
import android.telephony.SmsMessage
import com.joerakhimov.sms2email.usecase.email.EmailUseCase
import com.joerakhimov.sms2email.util.scheduler.SchedulerProvider
import io.reactivex.Single


class SmsBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var prefs: Prefs

    @Inject
    lateinit var emailUseCase: EmailUseCase

    init {
        Injector.appComponent.inject(this)
    }

    override fun onReceive(context: Context, intent: Intent) {

        if (!prefs.isRunning()) return

        val data = intent.extras
        val pdus = data.get("pdus") as Array<*>

        for (index in pdus.indices) {

            val smsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                SmsMessage.createFromPdu(pdus[index] as ByteArray?, data.getString("format"));
            } else {
                SmsMessage.createFromPdu(pdus[index] as ByteArray?);
            }

            val senderNumber = smsMessage.displayOriginatingAddress
            val messageBody = smsMessage.messageBody

            sendEmail(context, senderNumber, messageBody)

        }

    }

    private fun sendEmail(context: Context, senderNumber: String, messageBody: String) {

        val senderGmailEmail = prefs.getSenderGmailEmail()
        val senderGmailPassword = prefs.getSenderGmailPassword()
        val receiverEmail = prefs.getReceiverEmail()

        if(senderGmailEmail.isNullOrEmpty()) return
        if(senderGmailPassword.isNullOrEmpty()) return
        if(receiverEmail.isNullOrEmpty()) return

        emailUseCase.sendEmail(senderGmailEmail, senderGmailPassword, receiverEmail, senderNumber, messageBody)
            .subscribe({
                context.showToast("Message from $senderNumber was sent to $receiverEmail")
            }, { e ->
                context.showToast(e.message)
            })


    }

}
