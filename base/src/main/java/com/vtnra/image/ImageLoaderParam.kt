package com.vtnra.image

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.DrawableRes
import java.io.File
import java.lang.ref.WeakReference

/**
 * Parameters for [ImageLoader]
 * */

class ImageLoaderParam {
    /**
     * The Context.
     */
    lateinit var context: Context

    /**
     * The image uri to load from
     */
    var imageUri: Uri? = null

    /**
     * The image web url to load from
     */
    var imageUrl: String? = null

    /**
     * The image File to load from
     */
    var imageFile: File? = null

    /**
     * The image File to load from
     */
    @DrawableRes
    var imageRes: Int = -1

    /**
     * The Loading image while image is being drawn
     */
    @DrawableRes
    var loadingImage = -1

    /**
     * The Error image if image loading is got error such as file not found or url corrupted
     */
    @DrawableRes
    var errorImage = -1

    /**
     * The image task identifier.
     * For example if you are loading multiple images on same screen and to know the status of particular image success/failure
     */
    var taskId = 0

    /**
     * The pre defined height of image to be drawn
     */
    var height = 0

    /**
     * The pre defined width of image to be drawn
     */
    var width = 0

    /**
     * The Image view on which image needed to be drawn
     */
    var imageView: WeakReference<ImageView>? = null

    /**
     * Image scale type to be applied on
     */
    var scaleType: ImageView.ScaleType? = null

    /**
     * Enable/disable memory cache
     */
    var enableCache: Boolean = false

    /**
     * Image filtration/transformation
     * */
     var transformation: Any? = null

    /**
     * To show loader while loading image from Url
     */
    var progressBar:WeakReference<ProgressBar>?=null
}