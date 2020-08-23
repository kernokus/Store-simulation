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

    private fun getDb(): AppDatabase { //паблик на время
        return Room.databaseBuilder(App.ctx, AppDatabase::class.java, "db").build()
    }

//USER
suspend fun addUser(user:User): String {
        val db=getDb()

        if (db.userDao()?.getByParams(user.name,user.mail,user.password) ==null) {
            db.userDao()?.insert(user)
           return SUCCESS
        }
        return UNSUCCESSFUL

    }


    suspend fun isHaveUser(username:String, password:String): Boolean {
        val db=getDb()
        val user= db.userDao()?.getByTwoParams(username,password)
        return user!=null
    }

    suspend fun getUsers(): List<User?>? {
        val db=getDb()
        return db.userDao()?.getAll()
    }


     suspend fun getCatalog(): Collection<itemCatalogs>? {
        return getDb().itemCatalogsDao()?.getAll()
    }

    suspend fun saveCatalogInDb(ourData: MutableList<itemCatalogs>) {
        getDb().itemCatalogsDao()?.insertAll(ourData)
    }

//    fun addCatalogInBD(catalog: MutableList<itemCatalogs?>) {
//        getDb().itemCatalogsDao()?.insertAll(catalog)
//    }



}