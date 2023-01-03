package com.vtnra.webclient

import android.content.Context

/**
 * To build Web Client Configuration Builder and pass data to the related class
 */

object WebConnectConfiguration {
    fun builder(context: Context): WebConnectConfigurationBuilder {
        return WebConnectConfigurationBuilder(context)
    }
}
