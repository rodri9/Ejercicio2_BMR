package com.androiddgsa.ejercicio2_bmr.data.models

import com.google.gson.annotations.SerializedName

data class DetailsResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("data")
    val data: CharacterDetails
)

data class DetailsInfo(
    @SerializedName("count")
    val count: Int,
    @SerializedName("totalPages")
    val totalPages: Int,
    @SerializedName("previousPage")
    val previousPage: String?,
    @SerializedName("nextPage")
    val nextPage: String?
)
