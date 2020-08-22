package com.example.a10augportfolio.room

import androidx.room.*

@Dao
interface ItemCatalogDAO {
    @Query("SELECT * FROM itemCatalogs")
    fun getAll(): List<itemCatalogs?>?

    @Insert
    fun insertAll(users: List<itemCatalogs?>)
    @Insert
    fun insert(item: itemCatalogs?)

    @Update
    fun update(item: itemCatalogs?)

    @Delete
    fun delete(item: itemCatalogs?)



}