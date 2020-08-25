package com.example.a10augportfolio.model


import androidx.room.Room
import com.example.a10augportfolio.App
import com.example.a10augportfolio.room.AppDatabase
import com.example.a10augportfolio.room.User
import com.example.a10augportfolio.room.itemCatalogs

class RoomRepo {
    companion object{
        const val SUCCESS="success"
        const val UNSUCCESSFUL="unsuccessful"
    }



    private val appDatabase:AppDatabase=Room.databaseBuilder(App.ctx, AppDatabase::class.java, "db").build() //1 экземпляр т.к. класс синглтон

    private fun getDb(): AppDatabase {
        return appDatabase
    }


suspend fun addUser(user:User): String {
        //val db=getDb()

        if (getDb().userDao()?.getByParams(user.name,user.mail,user.password) ==null) {
            getDb().userDao()?.insert(user)
           return SUCCESS
        }
        return UNSUCCESSFUL

    }

    suspend fun isHaveUser(username:String, password:String): Boolean {
        val user= getDb().userDao()?.getByTwoParams(username,password)
        return user!=null
    }

     suspend fun getCatalog(): Collection<itemCatalogs>? {
        return getDb().itemCatalogsDao()?.getAll()
    }

    suspend fun saveCatalogInDb(ourData: MutableList<itemCatalogs>) {
        getDb().itemCatalogsDao()?.insertAll(ourData)
    }
}