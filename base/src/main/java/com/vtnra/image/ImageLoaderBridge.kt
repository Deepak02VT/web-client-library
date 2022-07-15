package com.vtnra.image

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.Rotate
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * To construct functionality of image loading respect to 3rd party image loading library
 * Intention to change only this class whenever there is a change in 3rd party image loading library
 * so that client must not affect and even should not know the changes behind the scene
 * Currently using [Glide]
 * */

private const val TAG = "image"

@SuppressWarnings(
    "Requires dependency:" +
            " 1. 'implementation \"com.github.bumptech.glide:glide:4.13'\"\n" +
            " 2. 'kapt \"com.github.bumptech.glide:compiler:4.13'"
)
object ImageLoaderBridge {

    fun load(imageParam: ImageLoaderParam) {
        val builder = Glide.with(imageParam.context).asDrawable()
        with(builder) {
            when {
                imageParam.imageFile != null -> {
                    load(imageParam.imageFile)
                }
                imageParam.imageUri != null -> {
                    load(imageParam.imageUri)
                }
                !imageParam.imageUrl.isNullOrEmpty() -> {
                    load(imageParam.imageUrl)
                }
                imageParam.imageRes != -1 -> {
                    load(imageParam.imageRes)
                }
                else -> {
                    Log.e(TAG, "Failed to load image: Image path is empty")
                    imageParam.progressBar?.get()?.visibility = View.GONE
                    // Let's assume default is web url
                    load(imageParam.imageUrl)
                }
            }
        }

        addListener(builder, imageParam)
        checkForScaleType(builder, imageParam)
        checkForResize(builder, imageParam)
        checkForCache(builder, imageParam)
        checkForLoadingImage(builder, imageParam)
        checkForErrorImage(builder, imageParam)
        checkForTransform(builder, imageParam)
        checkForImageView(builder, imageParam)
    }

    private fun addListener(glide: RequestBuilder<Drawable>, imageParam: ImageLoaderParam) {
        with(glide) {
            listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e(TAG, "Failed to load image: ${e?.message} ${imageParam.taskId}")
                    imageParam.progressBar?.get()?.visibility = View.GONE
                    return false // false to apply the error image
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    //Log.e(TAG, "onResourceReady: ${imageParam.taskId}")
                    imageParam.progressBar?.get()?.visibility = View.GONE
                    return false
                }
            })
        }
    }

    private fun checkForErrorImage(
        builder: RequestBuilder<Drawable>,
        imageParam: ImageLoaderParam
    ) {
        if (imageParam.imageView == null)
            return

        if (imageParam.errorImage == -1 || imageParam.errorImage == 0) {
            return
        }
        with(builder) {
            error(imageParam.errorImage)
        }
    }

    private fun checkForLoadingImage(
        builder: RequestBuilder<Drawable>,
        imageParam: ImageLoaderParam
    ) {
        if (imageParam.imageView == null)
            return

        if (imageParam.loadingImage == -1 || imageParam.loadingImage == 0) {
            return
        }
        with(builder) {
            placeholder(imageParam.loadingImage)
        }
    }

    private fun checkForImageView(glide: RequestBuilder<Drawable>, imageParam: ImageLoaderParam) {
        if (imageParam.imageView == null) {
            return
        }
        imageParam.imageView?.get()?.let { glide.into(it) }
    }

    /**
     * By default ImageView.ScaleType.CENTER_CROP
     * */
    private fun checkForScaleType(glide: RequestBuilder<Drawable>, imageParam: ImageLoaderParam) {
        if (imageParam.scaleType == null) {
            return
        }

        with(glide) {
            when (imageParam.scaleType) {
                ImageView.ScaleType.CENTER_CROP -> {
                    centerCrop()
                }
                ImageView.ScaleType.CENTER_INSIDE -> {
                    centerInside()
                }
                ImageView.ScaleType.FIT_XY,
                ImageView.ScaleType.FIT_CENTER -> {
                    fitCenter()
                }
                else -> {
                    centerCrop()
                }
            }
        }
    }

    private fun checkForResize(glide: RequestBuilder<Drawable>, imageParam: ImageLoaderParam) {
        if (imageParam.height == 0 || imageParam.width == 0) {
            return
        }
        with(glide) {
            override(imageParam.width, imageParam.height)
        }
    }

    private fun checkForCache(glide: RequestBuilder<Drawable>, imageParam: ImageLoaderParam) {
        with(glide) {
            skipMemoryCache(imageParam.enableCache)
        }
    }

    private fun checkForTransform(glide: RequestBuilder<Drawable>, imageParam: ImageLoaderParam) {
        if (imageParam.transformation == null) {
            return
        }
        with(glide) {
            transform(CenterCrop(), Rotate(90))
        }
    }
}
