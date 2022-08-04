package com.vtnra.webclient

import android.content.Context

/**
 * To build Global web client configuration parameters
 * */

class WebClientConfigurationBuilder() {
    private lateinit var webClientConfigParam: WebClientConfigurationParam

    constructor(context:Context) : this() {
        webClientConfigParam = WebClientConfigurationParam()
        webClientConfigParam.context = context
    }

    constructor(context:Context,enableLog: Boolean) : this() {
        webClientConfigParam = WebClientConfigurationParam()
        webClientConfigParam.context = context
        enableLogging(enableLog)
    }

    constructor(context: Context,connectionTimeout:Long,enableLog: Boolean) : this() {
        webClientConfigParam = WebClientConfigurationParam()
        webClientConfigParam.context = context
        enableLogging(enableLog)
        connectionTimeOut(connectionTimeout)
    }

    private fun connectionTimeOut(connectionTimeout:Long)=apply{
        webClientConfigParam.connectTimeout = connectionTimeout
    }

    private fun enableLogging(enableLog:Boolean)=apply{
        webClientConfigParam.enableLog = enableLog
    }

    fun config(){
        WebClientBridge.config(webClientConfigParam)
    }
}
