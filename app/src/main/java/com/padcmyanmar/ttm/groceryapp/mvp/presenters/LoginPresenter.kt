package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import com.padcmyanmar.ttm.groceryapp.mvp.views.LoginView

interface LoginPresenter : BasePresenter<LoginView>{
    fun onTapLogin(email: String, password: String)
    fun onTapRegister()

    fun getUserName() :String
}