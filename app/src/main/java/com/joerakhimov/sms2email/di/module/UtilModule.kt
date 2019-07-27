package com.ipakyulibank.mobile.di.module

import android.content.Context
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.data.preferences.PrefsImpl
import com.ipakyulibank.mobile.data.preferences.PrefsUtil
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionCheckerImpl
import com.ipakyulibank.mobile.util.permissions.PermissionUtil
import com.joerakhimov.sms2email.util.scheduler.SchedulerProvider
import com.joerakhimov.sms2email.util.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class UtilModule() {

    @Provides
    fun providePermissionUtil() = PermissionUtil()

    @Provides
    fun providePermissionChecker(permissionUtil: PermissionUtil): MyPermissionChecker = MyPermissionCheckerImpl(permissionUtil)

    @Provides
    fun providePrefsUtil(context: Context): PrefsUtil = PrefsUtil(context)

    @Provides
    fun providePrefs(preferencesUtil: PrefsUtil): Prefs = PrefsImpl(preferencesUtil)

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = SchedulerProviderImpl()

}