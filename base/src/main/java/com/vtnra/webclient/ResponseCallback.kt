package com.vtnra.webclient

interface ResponseCallback<T> {
    fun onSuccess(data: T?=null, statusCode:Int=0,message:String?=null)
    fun onFailure(error: Throwable)
}
