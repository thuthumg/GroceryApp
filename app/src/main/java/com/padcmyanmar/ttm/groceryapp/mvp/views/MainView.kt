package com.padcmyanmar.ttm.groceryapp.mvp.views


import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO


interface MainView : BaseView {
    fun showGroceryData(groceryList: List<GroceryVO>)
    fun showGroceryDialog(name: String, description: String, amount: String,image:String?)
    fun showErrorMessage(message: String)
    fun openGallery()
    fun displayToolbarTitle(appNameFromRemoteConfig: String)
    fun getGroceryListChangeLayoutFromRemoteConfig(groceryListChangeLayoutFromRemoteConfig: Long)
}