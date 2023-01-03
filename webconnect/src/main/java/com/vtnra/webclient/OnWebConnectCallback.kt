package com.vtnra.webclient


interface OnWebConnectCallback {
    fun onResponse(data: WebConnectNetworkResponseState, taskId: String)
}
