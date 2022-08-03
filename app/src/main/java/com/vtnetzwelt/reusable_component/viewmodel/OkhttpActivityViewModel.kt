package com.vtnetzwelt.reusable_component.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vtnetzwelt.reusable_component.CreateProduct
import com.vtnra.webclient.ResponseCallback
import com.vtnra.webclient.WebConnect


class OkhttpActivityViewModel : ViewModel() {

    fun testGetCallWithOutParameter() {
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        WebConnect.get().endPoint("https://fakestoreapi.com/products")
            .headers(headers).setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e("GetCallWithOutParameter", "onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e("GetCallWithOutParameter", "onFailure")
                }

            }).connect()
    }

    fun testGetRequestWithParameter() {
        val queryParam = HashMap<String, String>()
        queryParam["sort"] = "desc"

        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        WebConnect.get(queryParam).endPoint("https://fakestoreapi.com/products")
            .headers(headers).setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e("GetRequestWithParameter", "onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e("GetRequestWithParameter", "onFailure")
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
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"

        WebConnect.post(data.toString()).endPoint("https://fakestoreapi.com/products")
            .headers(headers)
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e("PostCall", "onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e("PostCall", "onFailure: ${error.printStackTrace()}")
                }
            }).connect()
    }

    fun testDeleteCall() {
        val productId = "6"
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"

        WebConnect.delete(productId).endPoint("https://fakestoreapi.com/products/").headers(headers)
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e("DeleteCall", "onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e("DeleteCall", "onFailure: ${error.printStackTrace()}")
                }

            }).connect()
    }

    fun testPutCall() {
        val data = CreateProduct(
            "test product",
            "13.5",
            "lorem ipsum set",
            "https://i.pravatar.cc",
            "electronic"
        )
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"

        WebConnect.put(data.toString()).endPoint("https://fakestoreapi.com/products/7")
            .headers(headers)
            .setResponseCallback(object : ResponseCallback {
                override fun <T> onSuccess(data: T) {
                    Log.e("PutCall", "onSuccess: ${data.toString()}")
                }

                override fun onFailure(error: Throwable) {
                    Log.e("PutCall", "onFailure: ${error.printStackTrace()}")
                }

            }).connect()
    }
}
