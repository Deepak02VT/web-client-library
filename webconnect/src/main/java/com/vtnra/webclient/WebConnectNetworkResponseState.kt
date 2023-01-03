package com.vtnra.webclient

sealed interface WebConnectNetworkResponseState {
    data class Success<T>(val data: T) : WebConnectNetworkResponseState
    data class Error(val code: Int, val errorMessage: String) : WebConnectNetworkResponseState
    data class Failure(val error: Throwable) : WebConnectNetworkResponseState
    object Loading : WebConnectNetworkResponseState
}


