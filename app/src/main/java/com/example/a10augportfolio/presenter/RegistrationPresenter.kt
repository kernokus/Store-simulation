package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo

import com.example.a10augportfolio.view.RegFragmentView
import moxy.MvpPresenter
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
    var db: RoomRepo
): MvpPresenter<RegFragmentView>() {





}