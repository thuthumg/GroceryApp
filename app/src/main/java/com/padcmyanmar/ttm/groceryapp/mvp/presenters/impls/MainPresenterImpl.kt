package com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.groceryapp.data.models.GroceryModelImpl


import com.padcmyanmar.ttm.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.MainPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.views.MainView

class MainPresenterImpl  : MainPresenter, AbstractBasePresenter<MainView>() {

    val mGroceryModel = GroceryModelImpl

    override fun onUiReady(owner: LifecycleOwner) {
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFaiure = {
                mView.showErrorMessage(it)
            }
        )
    }
}