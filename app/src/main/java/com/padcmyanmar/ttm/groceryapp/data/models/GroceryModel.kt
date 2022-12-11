package com.padcmyanmar.ttm.groceryapp.data.models

import android.graphics.Bitmap
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import com.padcmyanmar.ttm.groceryapp.network.FirebaseApi

interface GroceryModel {

    var mFirebaseApi : FirebaseApi

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addGrocery(name: String ,description : String, amount: Int, image: String?)

    fun removeGrocery(name: String)

   // fun editGrocery(name: String, description: String, amount: Int)

    fun uploadImageAndUpdateGrocery(grocery : GroceryVO, image : Bitmap)


    fun getGroceriesByKey(
        name:String,
        onSuccess: (grocery: GroceryVO) -> Unit,
        onFailure: (String) -> Unit
    )

}