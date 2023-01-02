package com.vtnetzwelt.reusable_component

import android.app.Application
import com.vtnra.webclient.WebConnect
import com.vtnra.webclient.WebConnectConfiguration

class ApplicationClass:Application() {

    override fun onCreate() {
        super.onCreate()
        WebConnectConfiguration.builder(this)
            .baseUrl("")
            .connectionTimeOut(60)
            .enableLogging(true)
            .config()
    }
}