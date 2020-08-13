package com.example.a10augportfolio.presenter


import android.content.SharedPreferences
import com.example.a10augportfolio.model.NetworkRepo
import com.example.a10augportfolio.view.FirstFragmentView
import moxy.MvpPresenter
import javax.inject.Inject


class FirstPresenter @Inject constructor(
    val networkRepo: NetworkRepo,
    val sp: SharedPreferences

)

    :MvpPresenter<FirstFragmentView>() {







}