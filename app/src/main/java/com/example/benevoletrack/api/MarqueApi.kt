package com.example.benevoletrack.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MarqueApi {
    @GET("getallmakes")
    fun getMarqueList(@Query("format") format: String): Call<MarqueResponse>

}

/**@GET("getallmakes?format=json")
fun getMarqueList(): Call<MarqueResponse>
        */