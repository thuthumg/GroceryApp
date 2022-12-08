package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import androidx.lifecycle.ViewModel

import com.padcmyanmar.ttm.groceryapp.mvp.views.BaseView

abstract class AbstractBasePresenter<T: BaseView> : BasePresenter<T>, ViewModel() {

    protected lateinit var mView : T

    override fun initPresenter(view: T){
        mView = view
    }
}