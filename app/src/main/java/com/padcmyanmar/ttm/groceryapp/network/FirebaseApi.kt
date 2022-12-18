package com.padcmyanmar.ttm.groceryapp.network

import android.graphics.Bitmap
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO

interface FirebaseApi {
    fun getGroceries(onSuccess: (groceries: List<GroceryVO>) -> Unit, onFailure: (String) -> Unit)
    fun addGrocery(name: String, description: String, amount: Int, s: String)
    fun deleteGrocery(name: String)
    fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO, onSuccess: (returnStringData:String?) -> Unit)

}