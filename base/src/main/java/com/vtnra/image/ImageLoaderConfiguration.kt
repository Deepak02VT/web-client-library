package com.vtnra.image

import android.content.Context

/**
 * To configure image loading library with global constraints
 * */
@SuppressWarnings("Not in use and not tested")
object ImageLoaderConfiguration {

    fun with(context: Context): ImageLoaderConfigurationBuilder {
        return ImageLoaderConfigurationBuilder(context)
    }

    fun with(context: Context, enableLogging: Boolean): ImageLoaderConfigurationBuilder {
        return ImageLoaderConfigurationBuilder(context, enableLogging)
    }

    fun with(
        context: Context,
        enableLogging: Boolean,
        connectTimeout: Long,
        readTimeout: Long
    ): ImageLoaderConfigurationBuilder {
        return ImageLoaderConfigurationBuilder(context, enableLogging, connectTimeout, readTimeout)
    }
}