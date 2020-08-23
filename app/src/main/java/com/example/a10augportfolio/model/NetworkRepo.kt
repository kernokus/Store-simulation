package com.example.a10augportfolio.model

import android.util.Log
import com.example.a10augportfolio.network.hitPOJO
import com.example.a10augportfolio.network.paxabayRequest
import com.example.a10augportfolio.room.itemCatalogs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

suspend fun getCatalog(): MutableList<itemCatalogs> {
    val items =mutableListOf<itemCatalogs>()
    var count=0
    val dataNetwork=getRetrofit(BASE_URL_PIXABAY).create(paxabayRequest::class.java).getScriptInfo(
        API_KEY_PIXABAY,"yellow+flowers","photo").hits
    Log.d("TAGS",dataNetwork.toString())

            while(count<10) {
                items.add(hitPOJOinItemCatalog(dataNetwork?.get(count)))
                count++
            }
    return items
}


    private fun hitPOJOinItemCatalog(hitPOJO: hitPOJO?): itemCatalogs {
        return itemCatalogs(0,hitPOJO?.likes.toString(),hitPOJO?.tags.toString(),hitPOJO?.largeImageURL.toString())
    }


}