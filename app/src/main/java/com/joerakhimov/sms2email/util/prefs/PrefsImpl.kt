package com.ipakyulibank.mobile.data.preferences

const val PREF_IS_RUNNING = "is_running"
const val PREF_SENDER_GMAIL_EMAIL = "sender_gmail_email"
const val PREF_SENDER_GMAIL_PASSWORD = "sender_gmail_password"
const val RECEIVER_EMAIL = "receiver_email"

class PrefsImpl constructor(var prefsUtil: PrefsUtil) : Prefs {

    override fun setRunning(isRunning: Boolean) =prefsUtil.setBoolean(PREF_IS_RUNNING, isRunning)
    override fun isRunning() = prefsUtil.getBoolean(PREF_IS_RUNNING, false)

    override fun setSenderGmailEmail(email: String?) = prefsUtil.setString(PREF_SENDER_GMAIL_EMAIL, email)
    override fun getSenderGmailEmail() = prefsUtil.getString(PREF_SENDER_GMAIL_EMAIL, "")

    override fun setSenderGmailPassword(password: String?) = prefsUtil.setString(PREF_SENDER_GMAIL_PASSWORD, password)
    override fun getSenderGmailPassword() = prefsUtil.getString(PREF_SENDER_GMAIL_PASSWORD, "")

    override fun setReceiverEmail(email: String?) = prefsUtil.setString(RECEIVER_EMAIL, email)
    override fun getReceiverEmail() = prefsUtil.getString(RECEIVER_EMAIL, "")

}