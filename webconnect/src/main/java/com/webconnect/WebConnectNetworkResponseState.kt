package com.webconnect

import com.webconnect.model.ErrorResponse
import com.webconnect.model.SuccessResponse

/**
 * To handle the different states while executing the Api.
 * Sealed class use to maintain the states.Here managing
 * three states.
 * Success: state comes when the Api executed successfully.
 * Failure: If the api fails because of time out or network issue.
 * Loading: Loading state when api start executing.
 * Error: Any error occurs in the Api like unauthorized.
 */
sealed class WebConnectNetworkResponseState<in T>(
    val body: SuccessResponse<@UnsafeVariance T>? = null,
    val failure: Throwable? = null,
    val errorBody: ErrorResponse? = null
) {
    class Success<T>(body: SuccessResponse<T>) : WebConnectNetworkResponseState<T>(body)
    class Failure<T>(failure: Throwable) : WebConnectNetworkResponseState<T>(failure = failure)
    class Error<T>(errorBody: ErrorResponse) : WebConnectNetworkResponseState<T>(errorBody = errorBody)
    class Loading<T> : WebConnectNetworkResponseState<T>()
}