package com.vtnra.webclient

/**
 * To handle the different states while executing the Api.
 * Sealed class use to maintain the states.Here managing
 * three states.
 * Success: state comes when the Api executed successfully.
 * Failure: If the api fails because of time out or network issue.
 * Loading: Loading state when api start executing.
 */

sealed class WebConnectNetworkResponseState {
    data class Success<T>(val data: ResponseData<T>) : WebConnectNetworkResponseState()
    data class Failure(val error: Throwable) : WebConnectNetworkResponseState()
    object Loading : WebConnectNetworkResponseState()
}
