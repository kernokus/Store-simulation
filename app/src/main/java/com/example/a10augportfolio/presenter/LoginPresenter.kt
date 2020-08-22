package com.example.a10augportfolio.presenter


import android.content.SharedPreferences
import android.util.Log
import com.example.a10augportfolio.OnRequestFinishedListener
import com.example.a10augportfolio.model.NetworkRepo
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.network.paxabayPOJO
import com.example.a10augportfolio.room.itemCatalogs
import com.example.a10augportfolio.view.FirstFragmentView
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.*
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
        CoroutineScope(Dispatchers.IO).launch {
            val answer=db.isHaveUser(username,password)
            withContext(Dispatchers.Main) {
                viewState.redirectAfterCheck(answer)
            }
        }
    }
    fun loadCatalog() {
        var items =mutableListOf<itemCatalogs?>()
        if (sp.contains(IS_A_FIRST_LOAD) && sp.getString(IS_A_FIRST_LOAD,"def")!=null) {
            //загружаю из бд
        }
        else {
                val network=network.getCatalog(object : OnRequestFinishedListener {
                    override  fun loadFromOnResponse(res: MutableList<itemCatalogs?>) {
                        Log.d("res",res.toString())

                        CoroutineScope(Dispatchers.IO).launch {
                            delay(1000)
                            db.getDb().itemCatalogsDao()?.insert(itemCatalogs(0, "1", "#", "$")) //НЕ РАБОТАЕТ!
                            db.getDb().itemCatalogsDao()?.insert(itemCatalogs(0, "2", "#", "$")) //НЕ РАБОТАЕТ!
                            //db.addCatalogInBD(res) //добавление полученных данных в бд не работает
                        }
                    }
                })

                sp.edit().putString(IS_A_FIRST_LOAD, NOT_FIRST).apply()
            }
        Log.d("ITEMS",items.toString())
        }
        }





