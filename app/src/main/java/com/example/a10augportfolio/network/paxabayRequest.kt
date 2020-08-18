package com.example.a10augportfolio.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface paxabayRequest {
    @GET("?")
    fun getScriptInfo(@Query("key") key: String,@Query("q") q: String,@Query("image_type") imageType: String): Call<paxabayPOJO>
    }
