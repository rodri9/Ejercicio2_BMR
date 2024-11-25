package com.androiddgsa.ejercicio2_bmr.data.models

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("data")
    val data: List<ItemsViewModel>
)

data class Info(
    @SerializedName("count")
    val count: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("previousPage")
    val previousPage: String?,
    @SerializedName("nextPage")
    val nextPage: String?
)
