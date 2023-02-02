package com.vtn.android.reusable.sample.webconnectsample.webapi

sealed class ApiState<in T>(
    val data: @UnsafeVariance T?=null,
    val loading: Boolean = false,
    val error: String? = null,
    val throwable:Throwable?=null
) {
    class Loading<T> : ApiState<T>()
    class SuccessResponse<T>(data: T):ApiState<T>(data = data)
    class OnError<T>(message: String?):ApiState<T>(error = message)
    class OnFailure<T>(throwable: Throwable?):ApiState<T>(throwable = throwable)
}
