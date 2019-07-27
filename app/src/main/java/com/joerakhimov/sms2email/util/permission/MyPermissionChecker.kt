package com.ipakyulibank.mobile.util.permissions

import android.app.Activity
import android.support.v4.app.SupportActivity

interface MyPermissionChecker {

    fun checkReceiveSmsPermission(activity: Activity?, listener: MyPermissionListener)

}