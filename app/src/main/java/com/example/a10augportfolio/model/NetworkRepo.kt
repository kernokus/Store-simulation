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
    var items =mutableListOf<itemCatalogs?>()

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
//MutableList<itemCatalogs?>
//    fun getCatalog(): MutableList<itemCatalogs?> {
//    val items =mutableListOf<itemCatalogs?>()
//    var count=0
//
//
//
//    val network=getRetrofit(BASE_URL_PIXABAY).create(paxabayRequest::class.java)
//    network.getScriptInfo(
//        API_KEY_PIXABAY,"yellow+flowers","photo")
////        .subscribeOn(Schedulers.io())
////        .observeOn(AndroidSchedulers.mainThread())
////        .subscribe(object :DisposableSingleObserver<paxabayPOJO>(){
////            override fun onSuccess(t: paxabayPOJO?) {
////                Log.d("RXHELLO", "onSuccess: ${t?.hits.toString()}")
////                while(count<10) {
////                    items.add(hitPOJOinItemCatalog(t?.hits?.get(count)))
////                    count++
////                }
////                Log.d("RXHELLOAFTERNETWORK", "onSuccess: ${items.toString()}")
////
////            }
////
////            override fun onError(e: Throwable?) {
////                Log.d("RXHELLO", "onSuccess: ${e?.toString()}")
////            }
////
////        })
//        network.enqueue(object : Callback<paxabayPOJO> {
//            override fun onResponse(call: Call<paxabayPOJO>, response: Response<paxabayPOJO>) {
//               val res= response.body()?.hits
//                var count=0
//
//                while(count<10) {
//                    val items =mutableListOf<itemCatalogs?>()
//                    items.add(hitPOJOinItemCatalog(res?.get(count)))
//                    count++
//                }
//                //ДОСТАТЬ ITEMS И ПОЛОЖИТЬ В БАЗУ!!!
//
//               // Log.d("listCATALOG",items.toString())
//
//            }
//
//            override fun onFailure(call: Call<paxabayPOJO>, t: Throwable) {
//                //TODO обработать неудачный запрос
//            }
//        })
//            Log.d("catalogRX",items.toString())
//    return items
//    }
fun getCatalog(myCallback:OnRequestFinishedListener) {

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

            CoroutineScope(Dispatchers.IO).launch {
                myCallback.loadFromOnResponse(items) //данные передаются
                Log.d("listCATALOG", items.toString())
            }
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