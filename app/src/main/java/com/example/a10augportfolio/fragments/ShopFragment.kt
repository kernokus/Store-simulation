package com.example.a10augportfolio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a10augportfolio.R
import com.example.a10augportfolio.model.RoomRepo

import com.example.a10augportfolio.presenter.ShopPresenter
import com.example.a10augportfolio.view.ShopFragmentView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.shop_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class ShopFragment:MvpAppCompatFragment(),ShopFragmentView {


    @Inject
    lateinit var presenterProvider: Provider<ShopPresenter>

    private val shopPresenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var db: RoomRepo


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.shop_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        testBtn.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val test=db.getUsers()
                withContext(Dispatchers.Main){
                    testTV.text=test.toString()
                }
            }
        }
    }
}