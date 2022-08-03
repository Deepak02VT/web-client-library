package com.vtnra.webclient

/**
 * Parameters for [WebClientBuilder]
 */
class WebClientParam {
    /**
     *Url End point
     */
    var endPoint: String = ""

    /**
     * Headers for request
     */
    var headers: Map<String, String>? = null

    var requestType:String? = null

    var requestData:String? = null

    var queryParameters : Map<String,String>? = null

    var responseCallback:ResponseCallback? = null
}
