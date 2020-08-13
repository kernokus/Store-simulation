package com.example.a10augportfolio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a10augportfolio.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashScreenFragment:Fragment(),CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            delay(500)
            withContext(Dispatchers.Main){
                val fm=(activity as AppCompatActivity).supportFragmentManager.beginTransaction().replace(
                    R.id.fragmentBox,
                    FirstFragment()
                ).commit()
            }
        }
    }

}
