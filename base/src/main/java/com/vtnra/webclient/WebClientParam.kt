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
    var queryParameters: Map<String, String>? = null

    /**
     * Callbacks for success and failure
     */
    var responseCallback: ResponseCallback? = null

    /**
     * Http request time out
     */
    var connectionTimeOut: Long = 10
}
