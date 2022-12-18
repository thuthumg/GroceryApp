package com.padcmyanmar.ttm.groceryapp.data.models

import android.graphics.Bitmap
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import com.padcmyanmar.ttm.groceryapp.network.FirebaseApi
import com.padcmyanmar.ttm.groceryapp.network.remoteconfig.FirebaseRemoteConfigManager

interface GroceryModel {

    var mFirebaseApi : FirebaseApi
    var mFirebaseRemoteConfigManager : FirebaseRemoteConfigManager


    fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit)

    fun addGrocery(name: String ,description : String, amount: Int, image: String?)

    fun removeGrocery(name: String)

   // fun editGrocery(name: String, description: String, amount: Int)

    fun uploadImageAndUpdateGrocery(grocery : GroceryVO, image : Bitmap,onSuccess: (returnUrlString:String?) -> Unit)


    fun setUpRemoteConfigWithDefaultValues()

    fun fetchRemoteConfigs()

    fun getAppNameFromRemoteConfig():String

    fun getGroceryListChangeLayoutFromRemoteConfig(): Long

}