package com.vtnra.webclient

/**
 * To build webclient parameters
 * */
class WebConnectBuilder{
    private val webClientParam: WebConnectParam = WebConnectParam()

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
        formEncoded: Boolean,
        endPoint: String
    ) {
        webClientParam.requestType = requestType
        webClientParam.queryParameters = queryParam
        webClientParam.isFormEncode = formEncoded
        webClientParam.endPoint = endPoint
    }

    fun headers(headers: Map<String, String>) = apply {
        webClientParam.headers = headers
    }

    fun setConnectionTimeOut(connectionTimeOut: Long) = apply {
        webClientParam.connectionTimeOut = connectionTimeOut
    }

    fun connect(responseCallback: OnWebConnectCallback) {
        WebConnectBridge.connect(webClientParam, responseCallback)
    }
}
