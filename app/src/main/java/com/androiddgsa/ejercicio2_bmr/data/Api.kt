package com.androiddgsa.ejercicio2_bmr.data

import com.androiddgsa.ejercicio2_bmr.data.models.ApiResponse
import com.androiddgsa.ejercicio2_bmr.data.models.CharacterDetails
import com.androiddgsa.ejercicio2_bmr.data.models.DetailsResponse
import com.androiddgsa.ejercicio2_bmr.data.models.ItemsViewModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("character")
    fun getAllCharacters(
    ) : Call<ApiResponse>

    @GET("character/{id}")
    fun getCharacterDetails(
        @Path("id") id : String?
    ) : Call<DetailsResponse>
}