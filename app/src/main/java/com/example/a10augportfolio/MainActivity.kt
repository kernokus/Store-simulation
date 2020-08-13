package com.example.a10augportfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.a10augportfolio.fragments.SplashScreenFragment
import com.example.a10augportfolio.model.NetworkRepo
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var network: NetworkRepo


    lateinit var fm:FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         fm=supportFragmentManager
        fm.beginTransaction().add(R.id.fragmentBox,
            SplashScreenFragment()
        ).commit()

    }
}