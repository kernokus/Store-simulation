package com.example.a10augportfolio

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {
    companion object{
        //lateinit var ctx:Context
    }


    override fun onCreate() {
        super.onCreate()
        //ctx=applicationContext //временный костыль
    }
}