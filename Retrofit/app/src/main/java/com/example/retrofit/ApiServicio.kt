package com.example.retrofit

import retrofit2.http.GET

interface ApiServicio {

    @GET("caballos")
    suspend fun obtenerCaballos(): List<Caballos>
}


