package com.example.a10augportfolio.model


import androidx.room.Room
import com.example.a10augportfolio.App
import com.example.a10augportfolio.di.DatabaseModule
import com.example.a10augportfolio.room.AppDatabase
import com.example.a10augportfolio.room.User
import com.example.a10augportfolio.room.UserDAO
import com.example.a10augportfolio.room.itemCatalogs
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

class RoomRepo {
    companion object{
        const val SUCCESS="success"
        const val UNSUCCESSFUL="unsuccessful"
    }

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface HiltProviderEntryPoint{
        fun appDatabase(): AppDatabase
        fun userDAO():UserDAO?
    }

    private val appDatabase=getAppDatabase()
    private val userDAO=getUserDao()

    private fun getAppDatabase(): AppDatabase {
        val hiltEntryPoint=EntryPointAccessors.fromApplication(App.ctx,HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.appDatabase()
    }
    private fun getUserDao(): UserDAO? {
        val hiltEntryPoint=EntryPointAccessors.fromApplication(App.ctx,HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.userDAO()
    }

suspend fun addUser(user:User): String {
        if (userDAO?.getByParams(user.name,user.mail,user.password) ==null) {
            userDAO?.insert(user)
           return SUCCESS
        }
        return UNSUCCESSFUL
    }

    suspend fun isHaveUser(username:String, password:String): Boolean {
        val user= userDAO?.getByTwoParams(username,password)
        return user!=null
    }

     suspend fun getCatalog(): Collection<itemCatalogs>? {
        return appDatabase.itemCatalogsDao()?.getAll()
    }

    suspend fun saveCatalogInDb(ourData: MutableList<itemCatalogs>) {
        appDatabase.itemCatalogsDao()?.insertAll(ourData)
    }
}