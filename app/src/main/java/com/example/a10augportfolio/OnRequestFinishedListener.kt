package com.example.a10augportfolio

import com.example.a10augportfolio.room.itemCatalogs

interface OnRequestFinishedListener {
    fun loadFromOnResponse(res: MutableList<itemCatalogs?>)
}