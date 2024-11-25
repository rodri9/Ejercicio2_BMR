package com.androiddgsa.ejercicio2_bmr.data.models

import com.google.gson.annotations.SerializedName

data class CharacterDetails(
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val nombre: String,
    @SerializedName("imageUrl")
    val imagen: String,
    @SerializedName("films")
    val peliculas: String
)
