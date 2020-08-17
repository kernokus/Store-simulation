package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.room.User

import com.example.a10augportfolio.view.RegFragmentView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject

class RegistrationPresenter @Inject constructor(
    var db: RoomRepo
): MvpPresenter<RegFragmentView>() {

     fun addNewUser(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            val answer=db.addUser(user)
            withContext(Dispatchers.Main){
                viewState.showResAddUser(answer)
            }
        }


    }





}