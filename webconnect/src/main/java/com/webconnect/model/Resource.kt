package com.webconnect.model

import com.webconnect.WebConnectNetworkResponseState

data class Resource<in T>(val state: WebConnectNetworkResponseState<T>, val taskId: String)
