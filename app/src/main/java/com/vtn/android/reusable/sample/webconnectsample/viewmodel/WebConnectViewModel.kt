package com.vtn.android.reusable.sample.webconnectsample.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vtn.android.reusable.sample.webconnectsample.model.User
import com.vtn.android.reusable.sample.webconnectsample.webapi.ApiState
import com.webconnect.OnWebConnectCallback
import com.webconnect.model.Resource
import com.webconnect.WebConnect
import com.webconnect.WebConnectNetworkResponseState

const val TAG = "rc-sample-view-model"

class WebConnectViewModel : ViewModel() {

    var users = MutableLiveData<ApiState<User>>()
    var createUser = MutableLiveData<ApiState<Unit>>()
    var updateUser = MutableLiveData<ApiState<Unit>>()
    var deleteUser = MutableLiveData<ApiState<Unit>>()

    fun getUsers() {
        val params = HashMap<String, String>()
        params["page"] = "1"

        WebConnect.get("users", params) {
            connect(object : OnWebConnectCallback<User> {
                override fun onResponse(response: Resource<User>) {
                    when (response.state) {
                        is WebConnectNetworkResponseState.Loading -> {
                            Log.d(TAG, "Data is loading for ${response.taskId}")
                            users.postValue(ApiState.Loading())
                        }
                        is WebConnectNetworkResponseState.Success -> {
                            Log.d(
                                TAG,
                                "Success Method of api call :${response.taskId} with response ${response.state.body?.data?.data}"
                            )
                            users.postValue(ApiState.SuccessResponse(response.state.body?.data))
                        }
                        is WebConnectNetworkResponseState.Failure -> {
                            users.postValue(ApiState.OnFailure(response.state.failure))
                            Log.e(
                                TAG,
                                "Failure occurs in the api call ${response.taskId} with reason ${response.state.failure?.message} ${response.state.failure?.message}"
                            )
                        }
                        is WebConnectNetworkResponseState.Error -> {
                            Log.e(
                                TAG,
                                "Error occurs in the api call ${response.taskId} with reason ${response.state.errorBody?.message}"
                            )
                            users.postValue(ApiState.OnError(response.state.errorBody?.message))
                        }
                    }
                }
            })
        }
    }

    fun createUser() {
        val params = HashMap<String, String>()
        params["name"] = "morpheus"
        params["job"] = "leader"

        WebConnect.post("users", params, false) {
            connect(object : OnWebConnectCallback<Unit> {
                override fun onResponse(response: Resource<Unit>) {
                    when (response.state) {
                        is WebConnectNetworkResponseState.Loading -> {
                            Log.d(TAG, "Data is loading for ${response.taskId}")
                            createUser.postValue(ApiState.Loading())
                        }
                        is WebConnectNetworkResponseState.Success -> {
                            Log.d(
                                TAG,
                                "Success Method of api call :${response.taskId} with response ${response.state.body?.data}"
                            )
                            createUser.postValue(ApiState.SuccessResponse(response.state.body?.data))
                        }
                        is WebConnectNetworkResponseState.Failure -> {
                            createUser.postValue(ApiState.OnFailure(response.state.failure))
                            Log.e(
                                TAG,
                                "Failure occurs in the api call ${response.taskId} with reason ${response.state.failure?.message} ${response.state.failure?.message}"
                            )
                        }
                        is WebConnectNetworkResponseState.Error -> {
                            Log.e(
                                TAG,
                                "Error occurs in the api call ${response.taskId} with reason ${response.state.errorBody?.message}"
                            )
                            createUser.postValue(ApiState.OnError(response.state.errorBody?.message))
                        }
                    }
                }
            })
        }
    }

    fun updateUser(userId: Int?) {
        val params = HashMap<String, String>()
        params["name"] = "morpheus"
        params["job"] = "zion resident"

        val endPoint = "users/$userId"
        val url = Uri.Builder().apply {
            this.path(endPoint)
        }.build().toString()

        WebConnect.put(url, params, false) {
            connect(object : OnWebConnectCallback<Unit> {
                override fun onResponse(response: Resource<Unit>) {
                    when (response.state) {
                        is WebConnectNetworkResponseState.Loading -> {
                            Log.d(TAG, "Data is loading for ${response.taskId}")
                            updateUser.postValue(ApiState.Loading())
                        }
                        is WebConnectNetworkResponseState.Success -> {
                            Log.d(
                                TAG,
                                "Success Method of api call :${response.taskId} with response ${response.state.body?.data}"
                            )
                            updateUser.postValue(ApiState.SuccessResponse(response.state.body?.data))
                        }
                        is WebConnectNetworkResponseState.Failure -> {
                            updateUser.postValue(ApiState.OnFailure(response.state.failure))
                            Log.e(
                                TAG,
                                "Failure occurs in the api call ${response.taskId} with reason ${response.state.failure?.message} ${response.state.failure?.message}"
                            )
                        }
                        is WebConnectNetworkResponseState.Error -> {
                            Log.e(
                                TAG,
                                "Error occurs in the api call ${response.taskId} with reason ${response.state.errorBody?.message}"
                            )
                            updateUser.postValue(ApiState.OnError(response.state.errorBody?.message))
                        }
                    }
                }
            })
        }
    }

    fun deleteUser(userId: Int?) {

        val endPoint = "users/$userId"
        val url = Uri.Builder().apply {
            this.path(endPoint)
        }.build().toString()

        WebConnect.delete(url) {
            connect(object : OnWebConnectCallback<Unit> {
                override fun onResponse(response: Resource<Unit>) {
                    when (response.state) {
                        is WebConnectNetworkResponseState.Loading -> {
                            Log.d(TAG, "Data is loading for ${response.taskId}")
                            deleteUser.postValue(ApiState.Loading())
                        }
                        is WebConnectNetworkResponseState.Success -> {
                            Log.d(
                                TAG,
                                "Success Method of api call :${response.taskId} with response ${response.state.body?.data}"
                            )
                            deleteUser.postValue(ApiState.SuccessResponse(response.state.body?.data))
                        }
                        is WebConnectNetworkResponseState.Failure -> {
                            deleteUser.postValue(ApiState.OnFailure(response.state.failure))
                            Log.e(
                                TAG,
                                "Failure occurs in the api call ${response.taskId} with reason ${response.state.failure?.message} ${response.state.failure?.message}"
                            )
                        }
                        is WebConnectNetworkResponseState.Error -> {
                            Log.e(
                                TAG,
                                "Error occurs in the api call ${response.taskId} with reason ${response.state.errorBody?.message}"
                            )
                            deleteUser.postValue(ApiState.OnError(response.state.errorBody?.message))
                        }
                    }
                }
            })
        }
    }
}
