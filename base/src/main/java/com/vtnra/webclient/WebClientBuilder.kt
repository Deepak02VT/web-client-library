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

    constructor(requestType: String?,queryParam:Map<String,String>){
        webClientParam.requestType = requestType
        webClientParam.queryParameters  = queryParam
    }

    fun endPoint(endpoint: String)=apply{
        webClientParam.endPoint =  endpoint
    }

    fun headers(headers:Map<String,String>)=apply {
        webClientParam.headers = headers
    }

    fun setResponseCallback(responseCallback: ResponseCallback)=apply{
        webClientParam.responseCallback = responseCallback
    }

    fun connect(){
        WebClientBridge.connect(webClientParam)
    }
}
