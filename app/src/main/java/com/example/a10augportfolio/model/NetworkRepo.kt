package com.example.a10augportfolio.model

import android.util.Log
import com.example.a10augportfolio.OnRequestFinishedListener
import com.example.a10augportfolio.network.hitPOJO
import com.example.a10augportfolio.network.paxabayPOJO
import com.example.a10augportfolio.network.paxabayRequest
import com.example.a10augportfolio.room.itemCatalogs
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()

    }

suspend fun getCatalog(): MutableList<itemCatalogs?> {
    val items =mutableListOf<itemCatalogs?>()
    var count=0
    val dataNetwork=getRetrofit(BASE_URL_PIXABAY).create(paxabayRequest::class.java).getScriptInfo(
        API_KEY_PIXABAY,"yellow+flowers","photo").hits
    Log.d("TAGS",dataNetwork.toString())

            while(count<10) {
                items.add(hitPOJOinItemCatalog(dataNetwork?.get(count)))
                count++
            }
    return items

//    network.enqueue(object : Callback<paxabayPOJO> {
//        override fun onResponse(call: Call<paxabayPOJO>, response: Response<paxabayPOJO>) {
//            val res= response.body()?.hits
//            var count=0
//            while(count<10) {
//                items.add(hitPOJOinItemCatalog(res?.get(count)))
//                count++
//            }
//
//            //CoroutineScope(Dispatchers.IO).launch {
//                myCallback.loadFromOnResponse(items) //данные передаются
//                Log.d("listCATALOG", items.toString())
//           // }
//        }
//        override fun onFailure(call: Call<paxabayPOJO>, t: Throwable) {
//            //TODO обработать неудачный запрос
//        }
//    })
   // Log.d("catalogInNetwork",items.toString())
}


    fun hitPOJOinItemCatalog(hitPOJO: hitPOJO?): itemCatalogs {
        return itemCatalogs(0,hitPOJO?.likes.toString(),hitPOJO?.tags.toString(),hitPOJO?.largeImageURL.toString())
    }


}