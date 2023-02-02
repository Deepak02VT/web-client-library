package com.webconnect

/**
 * To build webclient parameters
 * */
class WebConnectBuilder {
    val webClientParam: WebConnectParam = WebConnectParam()

    constructor(requestType: String, endPoint: String) {
        webClientParam.requestType = requestType
        webClientParam.endPoint = endPoint
    }

    constructor(requestType: String?, requestData: String, endPoint: String) {
        webClientParam.requestType = requestType
        webClientParam.requestData = requestData
        webClientParam.endPoint = endPoint
    }

    constructor(
        requestType: String?,
        queryParam: Map<String, Any>,
        endPoint: String
    ) {
        webClientParam.requestType = requestType
        webClientParam.queryParameters = queryParam
        webClientParam.endPoint = endPoint
    }

    constructor(
        requestType: String?,
        requestData: String,
        formEncoded: Boolean,
        endPoint: String
    ) {
        webClientParam.requestType = requestType
        webClientParam.requestData = requestData
        webClientParam.isFormEncode = formEncoded
        webClientParam.endPoint = endPoint
    }

    fun headers(headers: Map<String, String>) = apply {
        webClientParam.headers = headers
    }

    fun setConnectionTimeOut(connectionTimeOut: Long) = apply {
        webClientParam.connectionTimeOut = connectionTimeOut
    }

    inline fun <reified T> connect(responseCallback: OnWebConnectCallback<T>) {
        WebConnectBridge.connect(webClientParam, responseCallback)
    }
}
