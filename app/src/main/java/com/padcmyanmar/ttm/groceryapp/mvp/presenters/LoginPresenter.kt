package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import android.content.Context
import com.padcmyanmar.ttm.groceryapp.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView>{
    fun onTapLogin(context : Context, email: String, password: String)
    fun onTapRegister()

    fun getUserName() :String
}