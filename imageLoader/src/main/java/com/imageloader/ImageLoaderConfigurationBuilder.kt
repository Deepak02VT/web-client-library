package com.imageloader

import android.content.Context

class ImageLoaderConfigurationBuilder {

    private val imageConfigParam: ImageLoaderConfigurationParam

    constructor(context: Context, enableLog: Boolean, connectTimeout: Long, readTimeout: Long) {
        imageConfigParam = ImageLoaderConfigurationParam()
        imageConfigParam.context = context
        enableLogging(enableLog)
        timeout(connectTimeout, readTimeout)
    }

    constructor(context: Context, enableLog: Boolean) {
        imageConfigParam = ImageLoaderConfigurationParam()
        imageConfigParam.context = context
        imageConfigParam.enableLog = enableLog
    }

    constructor(context: Context) {
        imageConfigParam = ImageLoaderConfigurationParam()
        imageConfigParam.context = context
    }

    fun enableLogging(enableLog: Boolean) = apply {
        imageConfigParam.enableLog = enableLog
    }

    fun timeout(connectTimeout: Long, readTimeout: Long) = apply {
        imageConfigParam.connectTimeout = connectTimeout
        imageConfigParam.readTimeout = readTimeout
    }

    fun config(): ImageLoaderConfigurationParam {
        // TODO: needed to be enabled global configuration with future use-cases that can be configured from Application class
        return imageConfigParam
    }
}
