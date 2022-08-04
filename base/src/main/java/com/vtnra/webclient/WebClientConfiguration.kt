package com.vtnra.webclient

import android.content.Context

/**
 * To build Web Client Configuration Builder and pass data to the related class
 */

object WebClientConfiguration
{
    fun builder(context:Context):WebClientConfigurationBuilder{
        return WebClientConfigurationBuilder(context)
    }
}
