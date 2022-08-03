package com.vtnra.webclient

interface ResponseCallback {
    fun <T> onSuccess(data: T)
    fun onFailure(error: Throwable)
}
