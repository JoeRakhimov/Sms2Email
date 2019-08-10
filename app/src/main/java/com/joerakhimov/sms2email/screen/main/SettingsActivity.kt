package com.joerakhimov.sms2email.screen.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.di.Injector
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionListener
import com.joerakhimov.sms2email.R
import com.joerakhimov.sms2email.extensions.showToast
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject
import android.content.Intent
import android.net.Uri
import com.joerakhimov.sms2email.usecase.email.EmailUseCase
import com.joerakhimov.sms2email.util.connection.ConnectionChecker
import com.joerakhimov.sms2email.util.worker.MyWorkManager

class SettingsActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Prefs

    @Inject
    lateinit var permissionChecker: MyPermissionChecker

    @Inject
    lateinit var emailUseCase: EmailUseCase

    @Inject
    lateinit var connectionChecker: ConnectionChecker

    @Inject
    lateinit var workManager: MyWorkManager

    init {
        Injector.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setTitle(R.string.settings)

        initInputs()
        initGmailSettingsButton()
        initTestButton()
        initStartStopButton()

    }

    private fun initInputs() {

        val senderGmailEmail = prefs.getSenderGmailEmail()
        val senderGmailPassword = prefs.getSenderGmailPassword()
        val receiverEmail = prefs.getReceiverEmail()

        inputSenderGmailEmail.setText(senderGmailEmail)
        inputSenderGmailPassword.setText(senderGmailPassword)
        inputReceiverEmailAddress.setText(receiverEmail)

    }

    private fun initGmailSettingsButton() {
        buttonGmailSettings.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://myaccount.google.com/lesssecureapps"))
            startActivity(browserIntent)
        }
    }

    private fun initTestButton() {
        buttonTest.setOnClickListener {

            val inputsAreValid = validateInputs()
            if (!inputsAreValid) return@setOnClickListener

            val senderGmailEmail = inputSenderGmailEmail.text.toString()
            val senderGmailPassword = inputSenderGmailPassword.text.toString()
            val receiverEmail = inputReceiverEmailAddress.text.toString()

            val senderNumber = "Test number"
            val messageBody = "Test message"

            connectionChecker.getConnectionChecker()
                .subscribe({
                    if(it){
                        emailUseCase.sendEmail(
                            senderGmailEmail,
                            senderGmailPassword,
                            receiverEmail,
                            "Test number",
                            "Test message")
                            .subscribe({
                                showToast(R.string.test_message_sent_successfully)
                            }, { e ->
                                showToast(e.message)
                            })
                    } else {
                        workManager.scheduleSendingEmail(senderNumber, messageBody)
                    }
                },{})

        }
    }

    private fun initStartStopButton() {

        if (prefs.isRunning()) buttonStartStop.setText(R.string.stop)
        else buttonStartStop.setText(R.string.start)

        buttonStartStop.setOnClickListener {
            if (prefs.isRunning()) stop()
            else checkReceiveSmsPermissionThenStart()
        }

    }

    private fun checkReceiveSmsPermissionThenStart() {

        permissionChecker.checkReceiveSmsPermission(this, object : MyPermissionListener {

            override fun onAllow() {
                start()
            }

            override fun onDeny() {
                showToast(R.string.sms_permission_not_allowed)
            }

        })

    }

    private fun start() {

        val inputsAreValid = validateInputs()
        if (!inputsAreValid) return

        prefs.setSenderGmailEmail(inputSenderGmailEmail.text.toString())
        prefs.setSenderGmailPassword(inputSenderGmailPassword.text.toString())
        prefs.setReceiverEmail(inputReceiverEmailAddress.text.toString())
        prefs.setRunning(true)

        buttonStartStop.setText(R.string.stop)

    }

    private fun validateInputs(): Boolean {

        val senderGmailEmail = inputSenderGmailEmail.text.toString()
        val senderGmailPassword = inputSenderGmailPassword.text.toString()
        val receiverEmail = inputReceiverEmailAddress.text.toString()

        if (senderGmailEmail.isEmpty()) {
            showToast(R.string.sender_gmail_email_empty)
            return false
        }

        if (senderGmailPassword.isEmpty()) {
            showToast(R.string.sender_gmail_password_empty)
            return false
        }

        if (receiverEmail.isEmpty()) {
            showToast(R.string.receiver_email_address_empty)
            return false
        }

        return true

    }

    private fun stop() {
        prefs.setRunning(false)
        buttonStartStop.setText(R.string.start)
    }

}


