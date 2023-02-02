package com.imageloader

import android.content.Context

class ImageLoaderConfigurationParam {
    lateinit var context: Context
    var connectTimeout = 10 * 1000L // default to 10 seconds
    var readTimeout = 20 * 1000L // default to 20 seconds
    var enableLog = true
}
