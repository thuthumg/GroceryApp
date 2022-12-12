package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import android.content.Context
import com.padcmyanmar.ttm.groceryapp.mvp.views.RegisterView

interface RegisterPresenter : BasePresenter<RegisterView> {
    fun onTapRegister(context : Context, email: String, password: String, userName: String)
}