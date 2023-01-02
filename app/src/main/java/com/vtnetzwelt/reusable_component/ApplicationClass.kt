package com.vtnetzwelt.reusable_component

import android.app.Application

class ApplicationClass:Application() {

    override fun onCreate() {
        super.onCreate()
      /*  WebConnectConfiguration.builder(this)
            .baseUrl("")
            .connectionTimeOut(60)
            .enableLogging(true)
            .config()*/
    }
}