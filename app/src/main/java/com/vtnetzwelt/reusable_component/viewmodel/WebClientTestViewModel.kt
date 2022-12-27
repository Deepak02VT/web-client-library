package com.vtnetzwelt.reusable_component.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vtnetzwelt.reusable_component.CreateProduct
import com.vtnra.webclient.ResponseCallback
import com.vtnra.webclient.WebConnect

const val TAG = "web-client-view-model"

class WebClientTestViewModel : ViewModel() {

    //private val baseUrl = "https://fakestoreapi.com/products"
    fun testGetCallWithOutParameter() {

        WebConnect.get().endPoint("products")
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e(TAG, "On GET method without parameter onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e(TAG, "On GET method without parameter onFailure")
                }

            }).connect()
    }

    fun testGetRequestWithParameter() {
        val queryParam = HashMap<String, String>()
        queryParam["sort"] = "desc"

        WebConnect.get(queryParam).endPoint("products/")
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e(TAG, "GET method with parameter onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e(TAG, "GET method with parameter onFailure")
                }

            }).connect()
    }

    fun testPostCall() {
        val data = CreateProduct(
            "test product",
            "1345",
            "lorem ipsum set",
            "https://i.pravatar.cc",
            "electronic"
        )


        WebConnect.post(data.toString()).endPoint("products")
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e(TAG, "Post method onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e(TAG, "Post method onFailure: ${error.printStackTrace()}")
                }
            }).connect()
    }

    fun testDeleteCall() {
        val productId = "6"

        WebConnect.delete(productId).endPoint("products/")
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e(TAG, "Delete method onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e(TAG, "Delete method onFailure: ${error.printStackTrace()}")
                }

            }).setConnectionTimeOut(70).connect()
    }

    fun testPutCall() {
        val data = CreateProduct(
            "test product",
            "13.5",
            "lorem ipsum set",
            "https://i.pravatar.cc",
            "electronic"
        )


        WebConnect.put(data.toString()).endPoint("products/7")
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e(TAG, "Put Method onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e(TAG, "Put Method onFailure: ${error.printStackTrace()}")
                }

            }).connect()
    }
}
