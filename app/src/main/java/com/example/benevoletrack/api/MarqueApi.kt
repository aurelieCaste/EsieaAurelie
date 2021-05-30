package com.example.benevoletrack.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarqueApi {
    @GET("getallmakes")
    fun getMarqueList(@Query("format") format: String): Call<MarqueListResponse>

    @GET("GetModelsForMakeId/{Make_ID}?format=json")
    fun getMarqueDetail(@Path("Make_ID") id: Int): Call<MarqueDetailResponse>


}

