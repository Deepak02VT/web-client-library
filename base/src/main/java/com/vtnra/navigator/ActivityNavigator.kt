package com.vtnra.navigator

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Common class to use activity navigation
 * */
object ActivityNavigator {

    fun startActivity(context: Context, className: Class<out Activity>,
                      finish: Boolean = false) {
        val intent = Intent(context, className)
        startActivity(context, intent, 0, finish)
    }

    fun startActivity(context: Context, className: Class<out Activity>, flag: Int = 0,
                      finish: Boolean = false, bundle: Bundle? = null) {
        val intent = Intent(context, className)
        bundle?.let { intent.putExtras(it) }
        startActivity(context, intent, flag, finish)
    }

    fun startActivity(context: Context, intent: Intent, flag: Int = 0,
                      finish: Boolean = false) {
        if (flag == 0) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
        if (finish && context is Activity) {
            context.finish()
        }
    }
}