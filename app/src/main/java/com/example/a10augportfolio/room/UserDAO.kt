package com.example.a10augportfolio.room

import androidx.room.*

@Dao
interface UserDAO {
    @Query("SELECT * FROM User")
    fun getAll(): List<User?>?

    @Query("SELECT * FROM User WHERE id = :id")
    fun getById(id: Long): User?

    @Insert
    fun insert(User: User?)

    @Update
    fun update(User: User?)

    @Delete
    fun delete(User: User?)

    @Query("SELECT * FROM User WHERE name = :name  AND password=:password AND mail=:mail")
    fun getByParams(name:String,mail:String,password:String): User?

    @Query("SELECT * FROM User WHERE password=:password AND name=:name")
    fun getByTwoParams(name:String,password:String): User?



}