package com.example.a10augportfolio.room


import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [User::class,itemCatalogs::class], version = 1,exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    abstract fun userDao(): UserDAO?
    abstract fun itemCatalogsDao(): ItemCatalogDAO?
}