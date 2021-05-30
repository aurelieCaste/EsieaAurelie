package com.example.benevoletrack

import com.example.benevoletrack.MarqueApplication.Companion.context
import com.example.benevoletrack.api.MarqueApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singletons{
    companion object{
        var cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024) // 10 MiB

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()

        val marqueApi: MarqueApi = Retrofit.Builder()
                .baseUrl("https://vpic.nhtsa.dot.gov/api/vehicles/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MarqueApi::class.java)



    }

}



