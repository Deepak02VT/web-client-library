package com.vtnra.webclient


/**
 * To build web client builder and pass data to the related class
 */
object WebConnect {

    fun get(endPoint: String): WebConnectBuilder {
        return WebConnectBuilder("get", endPoint)
    }

    fun get(endPoint: String, queryParam: Map<String, String>): WebConnectBuilder {
        return WebConnectBuilder("get", queryParam, false, endPoint)
    }

    fun post(endPoint: String, requestData: String): WebConnectBuilder {
        return WebConnectBuilder("post", requestData, endPoint)
    }

    fun post(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean
    ): WebConnectBuilder {
        return WebConnectBuilder("post", requestData, formEncode, endPoint)
    }

    fun delete(endPoint: String, requestData: String): WebConnectBuilder {
        return WebConnectBuilder("delete", requestData, endPoint)
    }

    fun put(endPoint: String, requestData: String): WebConnectBuilder {
        return WebConnectBuilder("put", requestData, endPoint)
    }

    fun get(endPoint: String, webConnectBuilder: WebConnectBuilder.() -> Unit) {
        val builder = WebConnectBuilder("get", endPoint)
        builder.webConnectBuilder()
    }

    fun get(
        endPoint: String,
        queryParam: Map<String, String>,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder("get", queryParam, false, endPoint)
        builder.webConnectBuilder()
    }

    fun post(
        endPoint: String,
        requestData: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder("post", requestData, endPoint)
        builder.webConnectBuilder()
    }

    fun post(
        endPoint: String,
        requestData: Map<String, Any>,
        formEncode: Boolean, webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder("post", requestData, formEncode, endPoint)
        builder.webConnectBuilder()
    }

    fun delete(
        endPoint: String,
        requestData: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder("delete", requestData, endPoint)
        builder.webConnectBuilder()
    }

    fun put(
        endPoint: String,
        requestData: String,
        webConnectBuilder: WebConnectBuilder.() -> Unit
    ) {
        val builder = WebConnectBuilder("put", requestData, endPoint)
        builder.webConnectBuilder()
    }
}
