package com.vtnra.webclient

import android.content.Context

/**
 * Parameters for [WebClientConfigurationBuilder]
 */
class WebClientConfigurationParam {
    lateinit var context: Context
    var connectTimeout = 10 * 1L // default to 10 seconds
    var enableLog = true
    lateinit var baseUrl: String
}
