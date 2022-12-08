package com.padcmyanmar.ttm.groceryapp.data.models

import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import com.padcmyanmar.ttm.groceryapp.network.FirebaseApi

interface GroceryModel {

    var mFirebaseApi : FirebaseApi

    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)
}