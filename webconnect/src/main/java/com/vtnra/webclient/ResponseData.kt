package com.vtnra.webclient

import okhttp3.Callback

/**
 * To handle the success and error response.It contains
 * @param statusCode: Returns the HTTP status code.
 * @param data: Returns a non-null value.if this response was passed to [Callback.onResponse]
 * @param isSuccessful: Returns true if the code is in [200..300), which means the request was successfully received, understood, and accepted.
 * @param errorMessage: Returns the HTTP status message.
 */

data class ResponseData<T>(
    val statusCode: Int,
    val data: T?,
    val isSuccessful: Boolean,
    val errorMessage: String
)