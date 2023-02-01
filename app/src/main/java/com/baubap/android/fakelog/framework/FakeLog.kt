package com.baubap.android.fakelog.framework

import android.app.Application
import com.baubap.android.fakelog.framework.common.NAVIGATION_BAR_HEIGHT
import com.baubap.android.fakelog.framework.common.STATUS_BAR_HEIGHT
import com.baubap.android.fakelog.framework.common.getScreenComponentHeight
import com.baubap.android.fakelog.framework.common.navigationBarSize
import com.baubap.android.fakelog.framework.common.statusBarSize

// Entry point of the application
class FakeLog : Application() {

    // Initialize the global application data
    override fun onCreate() {
        super.onCreate()
        statusBarSize = getScreenComponentHeight(context = this, name = STATUS_BAR_HEIGHT)
        navigationBarSize = getScreenComponentHeight(context = this, name = NAVIGATION_BAR_HEIGHT)
    }

}