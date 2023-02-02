package com.vtn.android.reusable.sample.webconnectsample.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

object Alert {

    fun createDialog(
        context: Context,
        title: String,
        message: String,
        positiveButtonTitle: String,
        negativeButtonTitle: String,
        isCancelable: Boolean = true,
        onPositiveButtonClicked: (dialog: DialogInterface) -> Unit
    ): AlertDialog {
        return AlertDialog.Builder(context)
            .setTitle(title)
            .setCancelable(isCancelable)
            .setMessage(message)
            .setPositiveButton(
                positiveButtonTitle
            ) { dialog, _ ->
                onPositiveButtonClicked(dialog)
            }
            .setNegativeButton(negativeButtonTitle) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
