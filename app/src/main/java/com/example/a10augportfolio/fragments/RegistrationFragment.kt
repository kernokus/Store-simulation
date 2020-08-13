package com.example.a10augportfolio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.a10augportfolio.R
import com.example.a10augportfolio.presenter.FirstPresenter
import com.example.a10augportfolio.presenter.RegistrationPresenter
import com.example.a10augportfolio.view.RegFragmentView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class RegistrationFragment:MvpAppCompatFragment(),RegFragmentView {

    @Inject
    lateinit var presenterProvider: Provider<RegistrationPresenter>

    private val regPresenter by moxyPresenter { presenterProvider.get() }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.second_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}