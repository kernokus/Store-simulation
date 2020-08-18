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
    var db: RoomRepo,
    var network: NetworkRepo

)

    :MvpPresenter<FirstFragmentView>() {
    companion object{
        const val API_PIXABAY="17951668-b5172637b18686031bb7db33b"
        const val IS_A_FIRST_LOAD="is first load"
        const val NOT_FIRST="not a first"
    }


    fun checkUserInDb (username:String,password:String) {
        GlobalScope.launch(Dispatchers.IO) {
            val answer=db.isHaveUser(username,password)
            withContext(Dispatchers.Main){
                viewState.redirectAfterCheck(answer)
            }
        }
    }
    fun loadCatalog() {
        if (sp.contains(IS_A_FIRST_LOAD) && sp.getString(IS_A_FIRST_LOAD,"def")!=null) {
            //загружаю из бд
        }
        else {
            network.getCatalog()
            sp.edit().putString(IS_A_FIRST_LOAD, NOT_FIRST).apply()
        }
    }







}