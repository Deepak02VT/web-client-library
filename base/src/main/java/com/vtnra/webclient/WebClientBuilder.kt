package com.vtnra.webclient

/**
 * To build webclient parameters
 * */
class WebClientBuilder{
    private val webClientParam : WebClientParam = WebClientParam()

    constructor(requestType: String) {
        webClientParam.requestType = requestType
    }

    constructor(requestType: String?,requestData: String) {
        webClientParam.requestType = requestType
        webClientParam.requestData = requestData
    }

    constructor(requestType: String?,queryParam:Map<String,Any>,formEncoded:Boolean){
        webClientParam.requestType = requestType
        webClientParam.queryParameters  = queryParam
        webClientParam.isFormEncode = formEncoded
    }

    fun endPoint(endpoint: String)=apply{
        webClientParam.endPoint =  endpoint
    }

    fun headers(headers:Map<String,String>)=apply {
        webClientParam.headers = headers
    }

    fun setConnectionTimeOut(connectionTimeOut:Long)=apply {
        webClientParam.connectionTimeOut = connectionTimeOut
    }

    fun connect(responseCallback:ResponseCallback){
        WebClientBridge.connect(webClientParam,responseCallback)
    }
}
