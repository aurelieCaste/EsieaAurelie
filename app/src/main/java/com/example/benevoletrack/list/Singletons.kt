package com.example.benevoletrack.list

import com.example.benevoletrack.api.MarqueApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Singletons{
    companion object{
        val marqueApi: MarqueApi = Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarqueApi::class.java)

    }

}



