package com.vtn.android.reusable.sample.webconnectsample.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data")
    var `data`: List<Data>? = null,
    @SerializedName("page")
    var page: Int? = null,
    @SerializedName("per_page")
    var perPage: Int? = null,
    @SerializedName("support")
    var support: Support? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("total_pages")
    var totalPages: Int? = null
)