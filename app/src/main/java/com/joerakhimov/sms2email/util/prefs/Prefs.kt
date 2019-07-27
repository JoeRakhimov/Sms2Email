package com.ipakyulibank.mobile.data.preferences

interface Prefs {

    fun setRunning(isRunning: Boolean)
    fun isRunning(): Boolean

    fun setSenderGmailEmail(email: String?)
    fun getSenderGmailEmail(): String?

    fun setSenderGmailPassword(password: String?)
    fun getSenderGmailPassword(): String?

    fun setReceiverEmail(email: String?)
    fun getReceiverEmail(): String?

}