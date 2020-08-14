package com.example.a10augportfolio.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a10augportfolio.R
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.presenter.RegistrationPresenter
import com.example.a10augportfolio.view.RegFragmentView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.registration_fragment.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class RegistrationFragment:MvpAppCompatFragment(),RegFragmentView,View.OnClickListener,View.OnFocusChangeListener {

    @Inject
    lateinit var presenterProvider: Provider<RegistrationPresenter>

    private val regPresenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var db: RoomRepo



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.registration_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mailETR.onFocusChangeListener=this
        passwETR.onFocusChangeListener = this
        mailETR.onFocusChangeListener = this



        btnReg.setOnClickListener (this)
        signUpBtnTV.setOnClickListener (this)



    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btnReg ->{

            }
            R.id.signUpBtnTV->{
                findNavController().popBackStack()
            }
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if(v?.id== R.id.loginETR || v?.id== R.id.passwETR || v?.id ==R.id.mailETR) {
            if (!hasFocus) {
                hideKeyboard()
            }
        }
    }

    //Закрытие клавиатуры TODO вынести в utils
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

}