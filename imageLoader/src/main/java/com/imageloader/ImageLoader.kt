package com.imageloader

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import java.io.File

/**
 * Please setup global constraint using [ImageLoaderConfiguration]
 * Root class to load an image using reusable image loader wrapper
 * Use case: ImageLoader.with(this, "").load()
 * */

/**
 * When there is no required to use builder pattern instead use Kotlin DSL way
 * Use case:
 * ImageLoader(context, IMAGE_FILE){
 *      on(imageView)
 *      enableCache(false)
 * }
 * */
inline fun imageLoader(
    context: Context,
    imageFile: File,
    buildImageLoader: ImageLoaderBuilder.() -> Unit
): ImageLoaderParam {
    val builder = ImageLoaderBuilder(context, imageFile)
    builder.buildImageLoader()

    return builder.load()
}

/**
 * When there is no required to use builder pattern instead use Kotlin DSL way
 * Use case:
 * ImageLoader(context, IMAGE_URI){
 *      on(imageView)
 *      enableCache(false)
 * }
 * */
inline fun imageLoader(
    context: Context,
    imageUri: Uri,
    buildImageLoader: ImageLoaderBuilder.() -> Unit
): ImageLoaderParam {
    val builder = ImageLoaderBuilder(context, imageUri)
    builder.buildImageLoader()

    return builder.load()
}

/**
 * When there is no required to use builder pattern instead use Kotlin DSL way
 * Use case:
 * ImageLoader(context, IMAGE_WEB_URL){
 *      on(imageView)
 *      enableCache(false)
 * }
 * */
inline fun imageLoader(
    context: Context,
    imageUrl: String?,
    buildImageLoader: ImageLoaderBuilder.() -> Unit
): ImageLoaderParam {
    val builder = ImageLoaderBuilder(context, imageUrl)
    builder.buildImageLoader()
    return builder.load()
}

/**
 * When there is no required to use builder pattern instead use Kotlin DSL way
 * Use case:
 * ImageLoader(context, R.drawable.image){
 *      on(imageView)
 *      enableCache(false)
 * }
 * */
inline fun imageLoader(
    context: Context,
    @DrawableRes imageRes: Int,
    buildImageLoader: ImageLoaderBuilder.() -> Unit
): ImageLoaderParam {
    val builder = ImageLoaderBuilder(context, imageRes)
    builder.buildImageLoader()

    return builder.load()
}

object ImageLoader {

    /**
     * @param context Context
     * @param imageUrl Image web url
     * @param imageView [ImageView] on which image needed to be draw
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        imageUrl: String?,
        imageView: ImageView
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageUrl, imageView)
    }

    /**
     * @param context Context
     * @param imageUrl Image web url
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        imageUrl: String?
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageUrl)
    }


    /**
     * @param context Context
     * @param imageUri Image uri
     * @param imageView [ImageView] on which image needed to be draw
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        imageUri: Uri,
        imageView: ImageView
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageUri, imageView)
    }

    /**
     * @param context Context
     * @param imageUri Image uri
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        imageUri: Uri
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageUri)
    }


    /**
     * @param context Context
     * @param imageFile Image file to load from memory
     * @param imageView [ImageView] on which image needed to be draw
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        imageFile: File,
        imageView: ImageView
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageFile, imageView)
    }

    /**
     * @param context Context
     * @param imageFile to load image from memory
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        imageFile: File
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageFile)
    }


    /**
     * @param context Context
     * @param imageRes Drawable image eg. R.drawable.xyz
     * @param imageView [ImageView] on which image needed to be draw
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        @DrawableRes imageRes: Int,
        imageView: ImageView
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageRes, imageView)
    }

    /**
     * @param context Context
     * @param imageRes Drawable image eg. R.drawable.xyz
     *
     * @return [ImageLoaderBuilder]
     * */
    fun with(
        context: Context,
        @DrawableRes imageRes: Int
    ): ImageLoaderBuilder {
        return ImageLoaderBuilder(context, imageRes)
    }
}
