package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.room.User

import com.example.a10augportfolio.view.RegFragmentView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class RegistrationPresenter @Inject constructor(
    var db: RoomRepo
): MvpPresenter<RegFragmentView>() {


    fun addNewUser(user:User){
        CoroutineScope(Dispatchers.IO).launch{
            val answer=db.addUser(user)
            withContext(Dispatchers.Main){
                viewState.showResAddUser(answer)
            }
        }
    }





}