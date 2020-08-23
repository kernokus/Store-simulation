package com.example.a10augportfolio.presenter

import android.util.Log
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.view.ShopFragmentView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ShopPresenter @Inject constructor(
    var db: RoomRepo
):MvpPresenter<ShopFragmentView>(),CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob()+Dispatchers.Main.immediate

     fun getCatalog() {
         launch {
             val catalog=db.getCatalog()
             Log.d("SHOPPRESENTERCATALOG",catalog.toString())
             viewState.loadCatalogFromDB(catalog)
         }
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}