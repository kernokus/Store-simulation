package com.example.a10augportfolio.network


import retrofit2.http.GET
import retrofit2.http.Query


interface paxabayRequest {
    @GET("?")
    suspend fun getScriptInfo(@Query("key") key: String,@Query("q") q: String,@Query("image_type") imageType: String): PaxabayPOJO
    }
