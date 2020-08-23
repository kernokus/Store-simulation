package com.example.a10augportfolio.room

import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM User")
    suspend fun getAll(): List<User?>?

    @Query("SELECT * FROM User WHERE id = :id")
    fun getById(id: Long): User?

    @Insert
    suspend fun insert(User: User?)

    @Update
    suspend fun update(User: User?)

    @Delete
    suspend fun delete(User: User?)

    @Query("SELECT * FROM User WHERE name = :name  AND password=:password AND mail=:mail")
    suspend fun getByParams(name:String,mail:String,password:String): User?

    @Query("SELECT * FROM User WHERE password=:password AND name=:name")
    suspend fun getByTwoParams(name:String,password:String): User?



}