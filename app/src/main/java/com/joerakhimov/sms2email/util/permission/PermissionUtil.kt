package com.ipakyulibank.mobile.util.permissions

import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.single.PermissionListener

class PermissionUtil {

    fun checkPermission(activity: Activity?, permission: String, listener: MyPermissionListener) {
        Dexter.withActivity(activity)
                .withPermission(permission)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        listener.onAllow()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        listener.onDeny()
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: com.karumi.dexter.listener.PermissionRequest, token: PermissionToken) {
                        token.continuePermissionRequest()
                    }
                }).check()
    }

}