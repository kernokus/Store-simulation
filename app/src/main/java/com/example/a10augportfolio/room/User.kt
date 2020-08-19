package com.example.a10augportfolio.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User (@PrimaryKey(autoGenerate = true)
            @ColumnInfo(name = "id")
            var id: Long,
            @ColumnInfo(name = "mail")
            var mail: String,
            @ColumnInfo(name = "name")
            var name: String,
            @ColumnInfo(name = "password")
            var password: String) {

}

@Entity
class itemCatalogs (@PrimaryKey(autoGenerate = true)
            @ColumnInfo(name = "id")
            var id: Long,
            @ColumnInfo(name = "price")
            var price: String,
            @ColumnInfo(name = "name")
            var name: String,
            @ColumnInfo(name = "url")
            var url: String) {

}
