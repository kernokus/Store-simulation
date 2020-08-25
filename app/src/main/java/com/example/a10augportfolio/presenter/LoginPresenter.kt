package com.example.a10augportfolio.presenter


import android.content.SharedPreferences
import android.util.Log
import com.example.a10augportfolio.model.NetworkRepo
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.view.FirstFragmentView
import kotlinx.coroutines.*
import moxy.MvpPresenter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class LoginPresenter @Inject constructor(
    private val sp: SharedPreferences,
    var db: RoomRepo,
    var network: NetworkRepo
) :MvpPresenter<FirstFragmentView>(),CoroutineScope {
    companion object{
        const val IS_A_FIRST_LOAD="is first load"
        const val NOT_FIRST="not a first"
    }


    override val coroutineContext: CoroutineContext= SupervisorJob()+Dispatchers.Main.immediate

    fun checkUserInDb (username:String,password:String) {
        launch {
            val answer=db.isHaveUser(username,password)
            viewState.redirectAfterCheck(answer)
        }
    }

    fun isFirstLoad():Boolean{
        return if(sp.getString(IS_A_FIRST_LOAD,"def")!= NOT_FIRST) {
            sp.edit().putString(IS_A_FIRST_LOAD, NOT_FIRST).apply()
            true
        } else false
    }

     fun loadCatalog() {

            Log.d("LOAD","LOAD!!!!")
            launch {
                val ourData=network.getCatalog()
                Log.d("catalogData",ourData.toString())
                db.saveCatalogInDb(ourData)
            }
        }
    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}






