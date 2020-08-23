package com.example.a10augportfolio.presenter


import android.content.SharedPreferences
import android.util.Log
import com.example.a10augportfolio.OnRequestFinishedListener
import com.example.a10augportfolio.model.NetworkRepo
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.room.itemCatalogs
import com.example.a10augportfolio.view.FirstFragmentView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class LoginPresenter @Inject constructor(
    val sp: SharedPreferences,
    var db: RoomRepo,
    var network: NetworkRepo


)

    :MvpPresenter<FirstFragmentView>(),CoroutineScope {
    companion object{
        const val API_PIXABAY="17951668-b5172637b18686031bb7db33b"
        const val IS_A_FIRST_LOAD="is first load"
        const val NOT_FIRST="not a first"
    }


    override val coroutineContext: CoroutineContext= SupervisorJob()+Dispatchers.Main.immediate





    fun checkUserInDb (username:String,password:String) {
        launch {
            val answer=db.isHaveUser(username,password)
            viewState.redirectAfterCheck(answer)
        }



        CoroutineScope(Dispatchers.IO).launch {

            withContext(Dispatchers.Main) {

            }
        }
    }
     fun loadCatalog() {
        if (sp.contains(IS_A_FIRST_LOAD) && sp.getString(IS_A_FIRST_LOAD,"def")!=null) {
            //загружаю из бд
        }
        else {
            launch {
                val ourData=network.getCatalog()
                db.saveCatalogInDb(ourData)
            }
                sp.edit().putString(IS_A_FIRST_LOAD, NOT_FIRST).apply()
            }

        }
    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}






