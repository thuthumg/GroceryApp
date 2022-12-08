package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.groceryapp.mvp.views.BaseView

interface BasePresenter<V: BaseView> {
    fun onUiReady(owner: LifecycleOwner)
    fun initPresenter(view: V)
}