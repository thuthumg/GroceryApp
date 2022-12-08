package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import com.padcmyanmar.ttm.groceryapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView> {
    fun onTapAddGrocery(name: String, description: String, amount: Int)
}