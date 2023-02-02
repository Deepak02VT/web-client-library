package com.vtn.android

import android.app.Application
import com.webconnect.WebConnectConfiguration

class ReusableSampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        WebConnectConfiguration.builder(this)
            .baseUrl("https://reqres.in/api/")
            .connectionTimeOut(60)
            .enableLogging(true)
            .config()
    }
}
