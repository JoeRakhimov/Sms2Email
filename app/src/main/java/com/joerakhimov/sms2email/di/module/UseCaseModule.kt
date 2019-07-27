package com.ipakyulibank.mobile.di.module

import android.content.Context
import com.ipakyulibank.mobile.data.preferences.Prefs
import com.ipakyulibank.mobile.data.preferences.PrefsImpl
import com.ipakyulibank.mobile.data.preferences.PrefsUtil
import com.ipakyulibank.mobile.util.permissions.MyPermissionChecker
import com.ipakyulibank.mobile.util.permissions.MyPermissionCheckerImpl
import com.ipakyulibank.mobile.util.permissions.PermissionUtil
import com.joerakhimov.sms2email.usecase.email.EmailUseCase
import com.joerakhimov.sms2email.usecase.email.EmailUseCaseImpl
import com.joerakhimov.sms2email.util.scheduler.SchedulerProvider
import com.joerakhimov.sms2email.util.scheduler.SchedulerProviderImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule() {

    @Provides
    fun provideEmailUseCase(prefs: Prefs, schedulerProvider: SchedulerProvider): EmailUseCase = EmailUseCaseImpl(prefs, schedulerProvider)

}