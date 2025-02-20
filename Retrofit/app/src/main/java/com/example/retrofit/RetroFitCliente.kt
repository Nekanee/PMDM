package com.example.retrofit

object RetroFitCliente {

    private const val BASE_URL = "http://10.1.9.112:8080/"

    val instancia: ApiServicio by lazy{
        Retrofit.Builder()
            .baseURL(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServicio::class.java)
    }
}