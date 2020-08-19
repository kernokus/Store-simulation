package com.example.a10augportfolio.model

import android.util.Log
import com.example.a10augportfolio.network.hitPOJO
import com.example.a10augportfolio.network.paxabayRequest
import com.example.a10augportfolio.network.paxabayPOJO
import com.example.a10augportfolio.room.AppDatabase
import com.example.a10augportfolio.room.itemCatalogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

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
//MutableList<itemCatalogs?>
    fun getCatalog() {
        val items =mutableListOf<itemCatalogs?>()
        val network=getRetrofit(BASE_URL_PIXABAY).create(paxabayRequest::class.java).getScriptInfo(
            API_KEY_PIXABAY,"yellow+flowers","photo")
        network.enqueue(object : Callback<paxabayPOJO> {
            override fun onResponse(call: Call<paxabayPOJO>, response: Response<paxabayPOJO>) {
               val res= response.body()?.hits
                var count=0

                while(count<10) {
                    items.add(hitPOJOinItemCatalog(res?.get(count)))
                    count++
                }
                //ДОСТАТЬ ITEMS И ПОЛОЖИТЬ В БАЗУ!!!

                Log.d("listCATALOG",items.toString())

            }

            override fun onFailure(call: Call<paxabayPOJO>, t: Throwable) {
                //TODO обработать неудачный запрос
            }
        })
        Log.d("catalogInNetwork",items.toString())
    }


    fun hitPOJOinItemCatalog(hitPOJO: hitPOJO?): itemCatalogs {
        return itemCatalogs(0,hitPOJO?.likes.toString(),hitPOJO?.tags.toString(),hitPOJO?.largeImageURL.toString())
    }
}