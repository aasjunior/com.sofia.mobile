package com.sofia.mobile

import android.app.Application
import com.sofia.mobile.config.Injector

class SofiaApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.initialize(this)
    }
}