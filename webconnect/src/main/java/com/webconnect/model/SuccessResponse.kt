package com.webconnect.model

import okhttp3.Callback

/**
 * To handle the success and error response.It contains
 * @param data: Returns a non-null value.if this response was passed to [Callback.onResponse]
 * @param statusCode: Returns the HTTP status code.
 */
data class SuccessResponse<T>(
    val data: T?, override var statusCode: Int
) : ApiResponse()
