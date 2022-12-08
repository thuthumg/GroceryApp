package com.padcmyanmar.ttm.groceryapp.network

import android.graphics.Bitmap
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO

interface FirebaseApi {
    fun getGroceries(onSuccess: (groceries: List<GroceryVO>) -> Unit, onFialure: (String) -> Unit)
    fun addGrocery(name: String, description: String, amount: Int, image: String)
    fun deleteGrocery(name: String)
    fun uploadImageAndEditGrocery(image : Bitmap, grocery: GroceryVO)
}