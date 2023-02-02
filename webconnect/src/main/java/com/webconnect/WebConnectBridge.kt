package com.webconnect

import android.net.Uri
import com.google.gson.Gson
import com.webconnect.model.ErrorResponse
import com.webconnect.model.Resource
import com.webconnect.model.SuccessResponse
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * To construct the web Api methods for implementing the api using third party library OkhttpClient
 * Intention to change only this class whenever there is a change in 3rd party library
 * so that client must not affect and even should not know the changes behind the scene
 * Currently using [okhttp3]
 */
object WebConnectBridge {
    var client: OkHttpClient.Builder? = null
    private var baseUrl: String? = null

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

    inline fun <reified T> connect(
        webClientParam: WebConnectParam,
        responseCallback: OnWebConnectCallback<T>
    ) {
        val request = Request.Builder()
        addHeader(request, webClientParam)
        loadUrl(request, webClientParam)
        setApiConnectionTimeOut(webClientParam)
        executeRequest(
            request = request,
            responseCallback = responseCallback,
            webClientParam = webClientParam
        )
    }

    fun addHeader(request: Request.Builder, webClientParam: WebConnectParam) {
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
            val uriBuilder = Uri.Builder().path(webClientParam.endPoint)
            webClientParam.queryParameters?.forEach { (key, value) ->
                uriBuilder.appendQueryParameter(key, value.toString())
            }
            webClientParam.endPoint = uriBuilder.build().toString()
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
        if (webClientParam.requestData==null) {
            request.delete()
        }
        if (webClientParam.isFormEncode) {
            val formBody = FormBody.Builder()
            webClientParam.queryParameters?.forEach { (key, value) ->
                formBody.add(key, value.toString())
            }
            request.delete(formBody.build())
            return
        }
        if (webClientParam.requestData!=null) {
            webClientParam.requestData?.toRequestBody()?.let { request.delete(it) }
            return
        }
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

    fun loadUrl(request: Request.Builder, webClientParam: WebConnectParam) {
        when (webClientParam.requestType?.lowercase()) {
            RequestType.GET -> {
                getRequest(request, webClientParam)
            }
            RequestType.POST -> {
                postRequest(request, webClientParam)
            }
            RequestType.DELETE -> {
                deleteRequest(request, webClientParam)
            }
            RequestType.PUT -> {
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

    fun setApiConnectionTimeOut(webClientParam: WebConnectParam) {
        if (webClientParam.connectionTimeOut.toString()=="10") {
            return
        }
        client?.connectTimeout(webClientParam.connectionTimeOut, TimeUnit.SECONDS)
    }

    @PublishedApi
    internal inline fun <reified T> executeRequest(
        request: Request.Builder,
        responseCallback: OnWebConnectCallback<T>,
        webClientParam: WebConnectParam
    ) {
        if (client==null) {
            return
        }
        val type = T::class.java
        responseCallback.onResponse(
            Resource(
                WebConnectNetworkResponseState.Loading(),
                webClientParam.endPoint
            )
        )
        client?.build()?.newCall(request.build())?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                responseCallback.onResponse(
                    Resource(
                        WebConnectNetworkResponseState.Failure(e),
                        webClientParam.endPoint
                    )
                )
            }

            override fun onResponse(call: Call, response: Response) {

                val gson = Gson()
                if (response.isSuccessful) {
                    val data = gson.fromJson(response.body?.string(), type)
                    val responseData = SuccessResponse(data, statusCode = response.code)
                    responseCallback.onResponse(
                        Resource(
                            WebConnectNetworkResponseState.Success(responseData),
                            webClientParam.endPoint
                        )
                    )
                } else {
                    val responseData =
                        ErrorResponse(statusCode = response.code, message = response.message)
                    responseCallback.onResponse(
                        Resource(
                            WebConnectNetworkResponseState.Error(responseData),
                            webClientParam.endPoint
                        )
                    )
                }
            }
        })
    }
}
