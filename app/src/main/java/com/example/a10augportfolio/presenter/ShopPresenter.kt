package com.example.a10augportfolio.presenter

import android.util.Log
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.view.ShopFragmentView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject

class ShopPresenter @Inject constructor(
    var db: RoomRepo
):MvpPresenter<ShopFragmentView>() {


     fun getCatalog() {
         CoroutineScope(Dispatchers.IO).launch {
             val catalog=db.getCatalog()
             Log.d("SHOPPRESENTERCATALOG",catalog.toString())
             withContext(Dispatchers.Main) {
                 viewState.loadCatalogFromDB(catalog)
             }
         }

    }
}