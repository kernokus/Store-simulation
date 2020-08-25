package com.example.a10augportfolio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a10augportfolio.model.NetworkRepo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var network: NetworkRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}