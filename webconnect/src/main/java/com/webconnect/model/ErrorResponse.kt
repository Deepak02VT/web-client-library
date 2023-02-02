package com.webconnect.model

/**
 * To handle the error response.It contains
 * @param message: Return cause of error.
 * @param statusCode: Returns the HTTP status code.
 */
data class ErrorResponse(
    val message: String? = null,
    override var statusCode: Int
) : ApiResponse()
