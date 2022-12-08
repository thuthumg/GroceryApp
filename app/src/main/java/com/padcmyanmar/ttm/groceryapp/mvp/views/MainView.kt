package com.padcmyanmar.ttm.groceryapp.mvp.views


import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO


interface MainView : BaseView {
    fun showGroceryData(groceryList : List<GroceryVO>)
    fun showErrorMessage(message : String)
}