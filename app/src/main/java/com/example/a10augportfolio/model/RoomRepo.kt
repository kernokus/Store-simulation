package com.example.a10augportfolio.model

import android.content.Context
import androidx.room.Room
import com.example.a10augportfolio.App
import com.example.a10augportfolio.room.AppDatabase

class RoomRepo {

    fun getDb(): AppDatabase {
        return Room.databaseBuilder(App.ctx, AppDatabase::class.java, "db").build()
    }

}