package com.ipakyulibank.mobile.data.preferences

import android.content.Context

const val PREFS_FILE = "prefs_file"

class PrefsUtil(private val mContext: Context) {

    fun clearAll(){
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
    }

    fun setString(key: String, value: String?) {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun setInt(key: String, value: Int) {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun setLong(key: String, value: Long) {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        val editor = settings.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun setBoolean(key: String, value: Boolean) {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String): String? {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        return settings.getString(key, defValue)
    }

    fun getInt(key: String, defValue: Int): Int {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        return settings.getInt(key, defValue)
    }

    fun getLong(key: String, defValue: Long): Long {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        return settings.getLong(key, defValue)
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        val settings = mContext.getSharedPreferences(PREFS_FILE, 0)
        return settings.getBoolean(key, defValue)
    }

}