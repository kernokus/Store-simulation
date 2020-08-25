package com.example.a10augportfolio.fragments

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a10augportfolio.App
import com.example.a10augportfolio.R
import com.example.a10augportfolio.model.NetworkRepo
import com.example.a10augportfolio.model.RoomRepo
import com.example.a10augportfolio.presenter.LoginPresenter
import com.example.a10augportfolio.view.FirstFragmentView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.login_fragment.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class LoginFragment:MvpAppCompatFragment(),FirstFragmentView,View.OnFocusChangeListener {


    @Inject
    lateinit var presenterProvider: Provider<LoginPresenter>

    private val firstPresenter by moxyPresenter { presenterProvider.get() }

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var db: RoomRepo

    @Inject
    lateinit var network: NetworkRepo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.login_fragment,
            container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginET.onFocusChangeListener = this
        passwET.onFocusChangeListener = this
        if (firstPresenter.isFirstLoad()) //загружаем только 1 раз
        {
            firstPresenter.loadCatalog()  //временно тут(загрузка из сети данных в бд)
        }
        newUserBtnTV.setOnClickListener {
            findNavController().navigate(R.id.registrationFragment)
       }
        btnLogin.setOnClickListener {
            if(isFieldsAreFill()) {
                firstPresenter.checkUserInDb(loginET.text.toString(),passwET.text.toString())
            }
            else Toast.makeText(App.ctx, "Not successfully(fields not a full)", Toast.LENGTH_SHORT).show()
        }
    }



    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if(v?.id== R.id.loginET || v?.id== R.id.passwET) {
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

    private fun isFieldsAreFill():Boolean {
        if (loginET.text.toString()!="" && passwET.text.toString()!="") {
            return true
        }
        return false
    }

    override  fun redirectAfterCheck(answer: Boolean) {
       if (answer) {
           findNavController().navigate(R.id.shopFragment)
       }
        else Toast.makeText(App.ctx, "This user not register", Toast.LENGTH_SHORT).show()
    }


}