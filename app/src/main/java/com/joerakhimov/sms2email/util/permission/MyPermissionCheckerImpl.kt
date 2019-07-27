package com.ipakyulibank.mobile.util.permissions

import android.Manifest
import android.app.Activity
import android.support.v4.app.SupportActivity

class MyPermissionCheckerImpl(var permissionUtil: PermissionUtil): MyPermissionChecker {

    override fun checkReceiveSmsPermission(activity: Activity?, listener: MyPermissionListener) {
        permissionUtil.checkPermission(activity,Manifest.permission.RECEIVE_SMS,listener)
    }

}