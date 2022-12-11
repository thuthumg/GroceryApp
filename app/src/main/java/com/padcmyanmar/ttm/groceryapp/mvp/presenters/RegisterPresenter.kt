package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import com.padcmyanmar.ttm.groceryapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(email: String, password: String, userName: String)
}