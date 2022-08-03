package com.vtnra.webclient


import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

/**
 * To construct the web Api interface to consume the api using third party library OkhttpClient
 * Intention to change only this class whenever there is a change in 3rd party library
 * so that client must not affect and even should not know the changes behind the scene
 * Currently using [okhttp3]
 * */
object WebClientBridge {
    fun connect(webClientParam: WebClientParam) {
        val request = Request.Builder()
        addHeader(request, webClientParam)
        loadUrl(request, webClientParam)
        buildRequestClient(request, webClientParam)
    }

    private fun addHeader(request: Request.Builder, webClientParam: WebClientParam) {
        if (webClientParam.headers != null) {
            webClientParam.headers?.forEach { (key, value) ->
                request.addHeader(key, value)
            }
        }
    }

    private fun getRequest(request: Request.Builder, webClientParam: WebClientParam) {
        if (webClientParam.queryParameters == null) {
            request.get()
        } else {
            val httpBuilder = webClientParam.endPoint.toHttpUrl().newBuilder()
            webClientParam.queryParameters?.forEach { (key, value) ->
                httpBuilder.addQueryParameter(key, value)
            }
           webClientParam.endPoint = httpBuilder.build().toString()
        }
    }

    private fun postRequest(request: Request.Builder, webClientParam: WebClientParam) {
        if (webClientParam.requestData == null) {
            return
        }
        webClientParam.requestData?.toRequestBody()?.let { request.post(it) }
    }

    private fun deleteRequest(request: Request.Builder, webClientParam: WebClientParam) {
        if (webClientParam.requestData == null) {
            return
        }
        webClientParam.endPoint =  webClientParam.endPoint+webClientParam.requestData
        request.delete()
    }

    private fun putRequest(request: Request.Builder, webClientParam: WebClientParam) {
        if (webClientParam.requestData == null) {
            return
        }
        webClientParam.requestData?.toRequestBody()?.let { request.put(it) }
    }

    private fun loadUrl(request: Request.Builder, webClientParam: WebClientParam) {
        when (webClientParam.requestType?.lowercase()) {
            "get" -> {
                getRequest(request, webClientParam)
            }
            "post" -> {
                postRequest(request, webClientParam)
            }
            "delete" -> {
                deleteRequest(request, webClientParam)
            }
            "put" -> {
                putRequest(request, webClientParam)
            }
            else -> {
                // Method not supported
            }
        }
        if (webClientParam.endPoint == "") {
            return
        }
        request.url(webClientParam.endPoint)
    }

    private fun buildRequestClient(request: Request.Builder, webClientParam: WebClientParam) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient().newBuilder().addInterceptor(logging).build()
        client.newCall(request.build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                webClientParam.responseCallback?.onFailure(e)
            }

            override fun onResponse(call: Call, response: Response) {
                webClientParam.responseCallback?.onSuccess(response)
            }
        })
    }
}
