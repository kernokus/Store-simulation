package com.example.a10augportfolio.model

import com.example.a10augportfolio.network.paxabayRequest
import com.example.a10augportfolio.network.paxabayPOJO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepo {
    companion object {
        const val BASE_URL_PIXABAY="https://pixabay.com/api/"
        const val API_KEY_PIXABAY="17951668-b5172637b18686031bb7db33b"
    }

    fun getRetrofit(BaseUrl:String): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = (HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    fun getCatalog() {
        val network=getRetrofit(BASE_URL_PIXABAY).create(paxabayRequest::class.java).getScriptInfo(
            API_KEY_PIXABAY,"yellow+flowers","photo")
        network.enqueue(object : Callback<paxabayPOJO> {
            override fun onResponse(call: Call<paxabayPOJO>, response: Response<paxabayPOJO>) {
                //TODO сохранение в базу данных
            }
            override fun onFailure(call: Call<paxabayPOJO>, t: Throwable) {
                //TODO обработать неудачный запрос
            }
        })
    }
}