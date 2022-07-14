package com.vtnra.image

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import java.io.File
import java.lang.ref.WeakReference

/**
 * To build image parameter and pass loading logic to related class
 * */
class ImageLoaderBuilder {

    private val imageParam: ImageLoaderParam

    constructor(context: Context, url: String?) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageUrl = url
    }

    constructor(
        context: Context,
        url: String?,
        imageView: ImageView
    ) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageUrl = url
        on(imageView)
    }

    constructor(context: Context, uri: Uri) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageUri = uri
    }

    constructor(
        context: Context,
        uri: Uri,
        imageView: ImageView
    ) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageUri = uri
        on(imageView)
    }

    constructor(context: Context, file: File) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageFile = file
    }

    constructor(
        context: Context,
        file: File,
        imageView: ImageView
    ) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageFile = file
        on(imageView)
    }

    constructor(context: Context, @DrawableRes imageRes: Int) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageRes = imageRes
    }

    constructor(
        context: Context,
        @DrawableRes imageRes: Int,
        imageView: ImageView
    ) {
        imageParam = ImageLoaderParam()
        imageParam.context = context
        imageParam.imageRes = imageRes
        on(imageView)
    }


    fun errorImage(@DrawableRes imageRes: Int) = apply {
        imageParam.errorImage = imageRes
    }

    fun loadingImage(@DrawableRes imageRes: Int) = apply {
        imageParam.loadingImage = imageRes
    }

    fun id(taskId: Int) = apply {
        imageParam.taskId = taskId
    }

    fun size(height: Int, width: Int) = apply {
        imageParam.height = height
        imageParam.width = width
    }

    fun height(height: Int) = apply {
        imageParam.height = height
    }

    fun width(width: Int) = apply {
        imageParam.width = width
    }

    fun on(imageView: ImageView) = apply {
        imageParam.imageView = WeakReference<ImageView>(imageView)
    }

    fun enableCache(enable: Boolean) = apply {
        imageParam.enableCache = enable
    }

    fun transformation(transformation: Any?) = apply {
        imageParam.transformation = transformation
    }

    fun scaleType(scaleType:ImageView.ScaleType?)=apply {
        imageParam.scaleType = scaleType
    }

    fun progressBar(progressBar:ProgressBar?)=apply{
        imageParam.progressBar = WeakReference<ProgressBar>(progressBar)
    }

    fun load(): ImageLoaderParam {
        ImageLoaderBridge.load(imageParam)
        return imageParam
    }
}
