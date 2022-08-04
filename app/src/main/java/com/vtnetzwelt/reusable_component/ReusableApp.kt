package com.vtnetzwelt.reusable_component

import android.app.Application
import com.vtnra.webclient.WebClientConfiguration

class ReusableApp : Application() {
    override fun onCreate() {
        super.onCreate()
        WebClientConfiguration.setUp(this, 60, false).config()
    }
}
