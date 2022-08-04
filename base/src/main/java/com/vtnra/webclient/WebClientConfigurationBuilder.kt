package com.vtnra.webclient

import android.content.Context

/**
 * To build Global web client configuration parameters
 * */

class WebClientConfigurationBuilder() {
    private var webClientConfigParam: WebClientConfigurationParam = WebClientConfigurationParam()

    constructor(context:Context) : this() {
        webClientConfigParam.context = context
    }

    fun connectionTimeOut(connectionTimeout:Long)=apply{
        webClientConfigParam.connectTimeout = connectionTimeout
    }

    fun enableLogging(enableLog:Boolean)=apply{
        webClientConfigParam.enableLog = enableLog
    }

    fun config(){
        WebClientBridge.config(webClientConfigParam)
    }
}
