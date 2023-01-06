package com.vtnra.webclient


import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * To construct the web Api interface to consume the api using third party library OkhttpClient
 * Intention to change only this class whenever there is a change in 3rd party library
 * so that client must not affect and even should not know the changes behind the scene
 * Currently using [okhttp3]
 * */
object WebConnectBridge {
    private var client: OkHttpClient.Builder? = null
    private lateinit var baseUrl: String

    fun config(param: WebConnectConfigurationParam) {
        client = OkHttpClient().newBuilder().connectTimeout(param.connectTimeout, TimeUnit.SECONDS)
            .addInterceptor(enableLogging(param))
        baseUrl = param.baseUrl
    }

    private fun enableLogging(param: WebConnectConfigurationParam): Interceptor {
        val logging = HttpLoggingInterceptor()
        if (param.enableLog)
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        else
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        return logging
    }

    fun connect(webClientParam: WebConnectParam, responseCallback: OnWebConnectCallback) {
        val request = Request.Builder()
        addHeader(request, webClientParam)
        loadUrl(request, webClientParam)
        setApiConnectionTimeOut(webClientParam)
        executeRequest(request, responseCallback, webClientParam)
    }

    private fun addHeader(request: Request.Builder, webClientParam: WebConnectParam) {
        if (webClientParam.headers==null) {
            return
        }
        webClientParam.headers?.forEach { (key, value) ->
            request.addHeader(key, value)
        }
    }

    private fun getRequest(request: Request.Builder, webClientParam: WebConnectParam) {
        if (webClientParam.queryParameters==null) {
            request.get()
        } else {
            val httpBuilder = webClientParam.endPoint.toHttpUrl().newBuilder()
            webClientParam.queryParameters?.forEach { (key, value) ->
                httpBuilder.addQueryParameter(key, value.toString())
            }
            webClientParam.endPoint = httpBuilder.build().toString()
        }
    }

    private fun postRequest(request: Request.Builder, webClientParam: WebConnectParam) {
        if (webClientParam.isFormEncode) {
            val formBody = FormBody.Builder()
            webClientParam.queryParameters?.forEach { (key, value) ->
                formBody.add(key, value.toString())
            }
            request.post(formBody.build())
            return
        }
        if (webClientParam.requestData==null) {
            return
        }
        webClientParam.requestData?.toRequestBody()?.let { request.post(it) }
    }

    private fun deleteRequest(request: Request.Builder, webClientParam: WebConnectParam) {
        if (webClientParam.isFormEncode) {
            val formBody = FormBody.Builder()
            webClientParam.queryParameters?.forEach { (key, value) ->
                formBody.add(key, value.toString())
            }
            request.delete(formBody.build())
            return
        }
        if (webClientParam.requestData==null) {
            return
        }
        request.delete()
        webClientParam.endPoint = webClientParam.endPoint + webClientParam.requestData

    }

    private fun putRequest(request: Request.Builder, webClientParam: WebConnectParam) {
        if (webClientParam.isFormEncode) {
            val formBody = FormBody.Builder()
            webClientParam.queryParameters?.forEach { (key, value) ->
                formBody.add(key, value.toString())
            }
            request.put(formBody.build())
            return
        }
        if (webClientParam.requestData==null) {
            return
        }
        webClientParam.requestData?.toRequestBody()?.let { request.put(it) }
    }

    private fun loadUrl(request: Request.Builder, webClientParam: WebConnectParam) {
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
        if (webClientParam.endPoint=="") {
            return
        }
        request.url("$baseUrl${webClientParam.endPoint}")
    }

    private fun setApiConnectionTimeOut(webClientParam: WebConnectParam) {
        if (webClientParam.connectionTimeOut.toString()=="10") {
            return
        }
        client?.connectTimeout(webClientParam.connectionTimeOut, TimeUnit.SECONDS)
    }

    private fun executeRequest(
        request: Request.Builder,
        responseCallback: OnWebConnectCallback,
        webClientParam: WebConnectParam
    ) {
        if (client==null) {
            return
        }
        responseCallback.onResponse(WebConnectNetworkResponseState.Loading, webClientParam.endPoint)
        client?.build()?.newCall(request.build())?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseCallback.onResponse(
                    WebConnectNetworkResponseState.Failure(e),
                    webClientParam.endPoint
                )
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    responseCallback.onResponse(
                        WebConnectNetworkResponseState.Success(response.body?.string()),
                        webClientParam.endPoint
                    )
                } else {
                    responseCallback.onResponse(
                        WebConnectNetworkResponseState.Error(
                            response.code,
                            response.message
                        ), webClientParam.endPoint
                    )
                }
            }
        })
    }
}
