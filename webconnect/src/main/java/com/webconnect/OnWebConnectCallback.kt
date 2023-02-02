package com.webconnect

import com.webconnect.model.Resource

interface OnWebConnectCallback<out T> {
    fun onResponse(response: Resource<T>)
}
