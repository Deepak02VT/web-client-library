package com.imageloader

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import java.io.File

/**
 * To use [ImageLoader] as an extension function of [ImageView]
 * Use case:
 * imageView.load(context, imageFile)
 */
fun ImageView.load(
    file: File,
    transformation: Any? = null,
    scaleType: ImageView.ScaleType? = null
) {
    ImageLoader.with(this.context, file)
        .transformation(transformation)
        .scaleType(scaleType)
        .on(this)
        .load()
}

fun ImageView.load(
    uri: Uri, transformation: Any? = null,
    scaleType: ImageView.ScaleType? = null
) {
    ImageLoader.with(this.context, uri)
        .transformation(transformation)
        .scaleType(scaleType)
        .on(this)
        .load()
}

fun ImageView.load(
    imageUrl: String?,
    @DrawableRes loadingImage: Int = -1,
    @DrawableRes errorImage: Int = -1,
    scaleType: ImageView.ScaleType? = null,
    enableCache: Boolean = false,
    transformation: Any? = null
) {
    imageLoader(this.context, imageUrl) {
        on(this@load)
        loadingImage(loadingImage)
        errorImage(errorImage)
        scaleType(scaleType)
        enableCache(enableCache)
        transformation(transformation)
    }
}

fun ImageView.load(
    @DrawableRes imageRes: Int, transformation: Any?=null,
    scaleType: ImageView.ScaleType? = null,
) {
    ImageLoader.with(this.context, imageRes)
        .on(this)
        .transformation(transformation)
        .scaleType(scaleType)
        .load()
}

fun ImageView.load(
    imageUrl: String?,
    @DrawableRes loadingImage: Int = -1,
    @DrawableRes errorImage: Int = -1,
    scaleType: ImageView.ScaleType? = null,
    enableCache: Boolean = false,
    transformation: Any? = null,
    taskId:Int
) {
    imageLoader(this.context, imageUrl) {
        on(this@load)
        loadingImage(loadingImage)
        errorImage(errorImage)
        scaleType(scaleType)
        enableCache(enableCache)
        transformation(transformation)
        id(taskId)
    }
}
