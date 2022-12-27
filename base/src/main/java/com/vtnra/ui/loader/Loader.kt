package com.vtnra.ui.loader

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.vtnetzwelt.web_client.R


class Loader(val context: Context, val lifecycle: Lifecycle) : DefaultLifecycleObserver {

    private var loaderDialog: Dialog? = null

    init {
        lifecycle.addObserver(this)
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        loaderDialog = Dialog(context, R.style.LoaderDialog)
        loaderDialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        loaderDialog?.setContentView(R.layout.progress_bar)
        loaderDialog?.setCancelable(false)
        loaderDialog?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        loaderDialog?.window?.statusBarColor =
            ContextCompat.getColor(context, R.color.loader_status_bar)
    }

    fun show() {
        loaderDialog?.show()
    }

    fun hide() {
        if (loaderDialog != null && loaderDialog?.isShowing == true) {
            loaderDialog?.dismiss()
        }
    }
}
