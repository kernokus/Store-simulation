package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.view.ShopFragmentView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject

class ShopPresenter @Inject constructor(
    var db: RoomRepo
):MvpPresenter<ShopFragmentView>() {


     fun getCatalog() {
         GlobalScope.launch(Dispatchers.IO) {
             val catalog=db.getCatalog()
             withContext(Dispatchers.Main){
                 viewState.loadCatalogFromDB(catalog)
             }
         }

    }
}