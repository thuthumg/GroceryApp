package com.padcmyanmar.ttm.groceryapp.mvp.presenters

import android.graphics.Bitmap
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import com.padcmyanmar.ttm.groceryapp.delegates.GroceryViewItemActionDelegate
import com.padcmyanmar.ttm.groceryapp.mvp.views.MainView

interface MainPresenter : BasePresenter<MainView> , GroceryViewItemActionDelegate {
    fun onTapAddGrocery(name: String, description: String, amount: Int, imageUrl:String)
    fun onPhotoTaken(bitmap : Bitmap)
    fun getGroceriesByKey(name: String, onSuccess: (grocery: GroceryVO) -> Unit, onFailure: (String) -> Unit)

}