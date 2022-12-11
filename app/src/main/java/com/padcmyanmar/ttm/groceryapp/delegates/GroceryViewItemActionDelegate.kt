package com.padcmyanmar.ttm.groceryapp.delegates

import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO


interface GroceryViewItemActionDelegate{
    fun onTapDeleteGrocery(name : String)
    fun onTapEditGrocery(name: String, description: String, amount: Int, image: String?)
    fun onTapFileUpload(grocery: GroceryVO)

}