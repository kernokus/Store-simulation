package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.room.User

import com.example.a10augportfolio.view.RegFragmentView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class RegistrationPresenter @Inject constructor(
    var db: RoomRepo
): MvpPresenter<RegFragmentView>(),CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob()+Dispatchers.Main.immediate

    fun addNewUser(user:User) {
        launch{
                val answer=db.addUser(user)
                viewState.showResAddUser(answer)
            }
        }
    }





