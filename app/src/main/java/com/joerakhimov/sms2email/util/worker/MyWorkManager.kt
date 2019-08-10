package com.joerakhimov.sms2email.util.worker

import androidx.work.*
import com.joerakhimov.sms2email.work.EmailSendingWork
import com.joerakhimov.sms2email.work.MESSAGE_BODY
import com.joerakhimov.sms2email.work.SENDER_NUMBER


class MyWorkManager {

    fun scheduleSendingEmail(senderNumber: String, messageBody: String) {

        val work = OneTimeWorkRequest.Builder(EmailSendingWork::class.java)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        work.setConstraints(constraints)

        val data = Data.Builder()
        data.putString(SENDER_NUMBER, senderNumber)
        data.putString(MESSAGE_BODY, messageBody)
        work.setInputData(data.build())

        WorkManager.getInstance().enqueue(work.build())

    }

}