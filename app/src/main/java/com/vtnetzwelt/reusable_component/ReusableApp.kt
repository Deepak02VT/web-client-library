package com.vtnetzwelt.reusable_component

import android.app.Application
import com.vtnra.webclient.WebClientConfiguration

class ReusableApp : Application() {
    override fun onCreate() {
        super.onCreate()
        WebClientConfiguration.builder(this)
            .baseUrl("https://fakestoreapi.com/")
            .connectionTimeOut(60)
            .enableLogging(true)
            .config()
    }
}
