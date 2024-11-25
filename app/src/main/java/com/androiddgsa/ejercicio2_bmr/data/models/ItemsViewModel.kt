package com.androiddgsa.ejercicio2_bmr.data.models

import com.google.gson.annotations.SerializedName

data class ItemsViewModel(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("imageUrl")
    val imagen: String,
    @SerializedName("createdAt")
    val fecha: String
)
