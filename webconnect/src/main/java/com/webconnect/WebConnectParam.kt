package com.webconnect

/**
 * Parameters for [WebConnectBuilder]
 */
class WebConnectParam {

    /**
     *Url End point
     */
    var endPoint: String = ""

    /**
     * Headers for request
     */
    var headers: Map<String, String>? = null

    /**
     * Request type GET,PUT,POST,DELETE
     */
    var requestType: String? = null

    /**
     * Request data
     */
    var requestData: String? = null

    /**
     * Query parameters In GET request
     */
    var queryParameters: Map<String, Any>? = null

    /**
     * Http request time out
     */
    var connectionTimeOut: Long = 10

    /**
     * Parameter used for to check post method query is form data or not
     */
    var isFormEncode = false
}
