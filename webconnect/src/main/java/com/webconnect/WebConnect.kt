package com.webconnect

/**
 * To build web client builder and pass data to the related class
 */
object WebConnect {

    fun get(endPoint: String): WebConnectBuilder {
        return WebConnectBuilder(RequestType.GET, endPoint)
    }

    fun get(endPoint: String, queryParam: Map<String, String>): WebConnectBuilder {
        return WebConnectBuilder(RequestType.GET, queryParam, endPoint)
    }

    fun post(endPoint: String, requestData: String): WebConnectBuilder {
        return WebConnectBuilder(RequestType.POST, requestData, endPoint)
    }

    fun post(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean
    ): WebConnectBuilder {
        return WebConnectBuilder(RequestType.POST, requestData.toString(), formEncode, endPoint)
    }

    fun delete(endPoint: String, requestData: String): WebConnectBuilder {
        return WebConnectBuilder(RequestType.DELETE, requestData, endPoint)
    }

    fun delete(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean
    ): WebConnectBuilder {
        return WebConnectBuilder(RequestType.DELETE, requestData.toString(), formEncode, endPoint)
    }

    fun delete(endPoint: String): WebConnectBuilder {
        return WebConnectBuilder(RequestType.DELETE, endPoint)
    }

    fun put(endPoint: String, requestData: String): WebConnectBuilder {
        return WebConnectBuilder(RequestType.PUT, requestData, endPoint)
    }

    fun put(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean
    ): WebConnectBuilder {
        return WebConnectBuilder(RequestType.PUT, requestData.toString(), formEncode, endPoint)
    }

    fun get(endPoint: String, webConnectBuilder: WebConnectBuilder.() -> Unit) {
        val builder = WebConnectBuilder(RequestType.GET, endPoint)
        builder.webConnectBuilder()
    }

    fun get(
        endPoint: String,
        queryParam: Map<String, String>,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder(RequestType.GET, queryParam, endPoint)
        builder.webConnectBuilder()
    }

    fun post(
        endPoint: String,
        requestData: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder(RequestType.POST, requestData, endPoint)
        builder.webConnectBuilder()
    }

    fun post(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean, webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder =
            WebConnectBuilder(RequestType.POST, requestData.toString(), formEncode, endPoint)
        builder.webConnectBuilder()
    }

    fun delete(
        endPoint: String,
        requestData: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder(RequestType.DELETE, requestData, endPoint)
        builder.webConnectBuilder()
    }

    fun delete(
        endPoint: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder(RequestType.DELETE, endPoint)
        builder.webConnectBuilder()
    }

    fun delete(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder =
            WebConnectBuilder(RequestType.DELETE, requestData.toString(), formEncode, endPoint)
        builder.webConnectBuilder()
    }

    fun put(
        endPoint: String,
        requestData: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder(RequestType.PUT, requestData, endPoint)
        builder.webConnectBuilder()
    }

    fun put(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder =
            WebConnectBuilder(RequestType.PUT, requestData.toString(), formEncode, endPoint)
        builder.webConnectBuilder()
    }
}
