package com.example.a10augportfolio.presenter

import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.room.User
import com.example.a10augportfolio.view.RegFragmentView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

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





