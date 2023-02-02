package com.webconnect

import androidx.annotation.StringDef

class RequestType {

    companion object {

        @StringDef(
            "GET",
            "POST",
            "DELETE",
            "PUT"
        )
        @Retention(AnnotationRetention.SOURCE)
        annotation class Type

        const val GET = "get"
        const val POST = "post"
        const val DELETE = "delete"
        const val PUT = "put"
    }
}