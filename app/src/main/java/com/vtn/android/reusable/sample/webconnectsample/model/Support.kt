package com.vtn.android.reusable.sample.webconnectsample.model


import com.google.gson.annotations.SerializedName

data class Support(
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("url")
    var url: String? = null
)