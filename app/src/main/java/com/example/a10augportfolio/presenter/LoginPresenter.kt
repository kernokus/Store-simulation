package com.example.a10augportfolio.presenter


import android.content.SharedPreferences
import com.example.a10augportfolio.model.NetworkRepo
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.view.FirstFragmentView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpPresenter
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    val sp: SharedPreferences,
    var db: RoomRepo

)

    :MvpPresenter<FirstFragmentView>() {


    fun checkUserInDb (username:String,password:String){
        GlobalScope.launch(Dispatchers.IO) {
            val answer=db.isHaveUser(username,password)
            withContext(Dispatchers.Main){
                viewState.redirectAfterCheck(answer)
            }
        }
    }







}