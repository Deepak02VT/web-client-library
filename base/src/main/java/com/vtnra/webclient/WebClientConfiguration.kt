package com.vtnra.webclient

import android.content.Context

/**
 * To build Web Client Configuration Builder and pass data to the related class
 */

object WebClientConfiguration
{
    fun setUp(context:Context):WebClientConfigurationBuilder{
        return WebClientConfigurationBuilder(context)
    }

    fun setUp(context:Context,enableLog:Boolean):WebClientConfigurationBuilder{
        return WebClientConfigurationBuilder(context,enableLog)
    }

    fun setUp(context: Context,connectionTimeOut:Long,enableLog:Boolean):WebClientConfigurationBuilder{
        return WebClientConfigurationBuilder(context,connectionTimeOut,enableLog)
    }
}
