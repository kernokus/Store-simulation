package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.view.ShopFragmentView
import moxy.MvpPresenter
import javax.inject.Inject

class ShopPresenter @Inject constructor(
    var db: RoomRepo
):MvpPresenter<ShopFragmentView>() {
}