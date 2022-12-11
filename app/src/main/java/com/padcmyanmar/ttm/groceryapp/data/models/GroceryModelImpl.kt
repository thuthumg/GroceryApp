package com.padcmyanmar.ttm.groceryapp.data.models
import android.graphics.Bitmap
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO
import com.padcmyanmar.ttm.groceryapp.network.FirebaseApi
import com.padcmyanmar.ttm.groceryapp.network.RealtimeDatabaseFirebaseApiImpl
import com.padcmyanmar.ttm.groceryapp.network.remoteconfig.FirebaseRemoteConfigManager


object GroceryModelImpl : GroceryModel {
    override var mFirebaseApi: FirebaseApi = RealtimeDatabaseFirebaseApiImpl
 //   override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager = FirebaseRemoteConfigManager


    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFaiure)
    }

    override fun addGrocery(name: String, description: String, amount: Int, image:String?) {
        mFirebaseApi.addGrocery(name, description, amount, image ?: "")
    }

    override fun removeGrocery(name: String) {
        mFirebaseApi.deleteGrocery(name)
    }
//
//    override fun editGrocery(name: String, description: String, amount: Int) {
//
//    }

    override fun uploadImageAndUpdateGrocery(grocery: GroceryVO, image: Bitmap){
       return mFirebaseApi.uploadImageAndEditGrocery(image, grocery)
    }

    override fun getGroceriesByKey(
        name:String,
        onSuccess: (grocery: GroceryVO) -> Unit,
        onFailure: (String) -> Unit
    ){
        mFirebaseApi.getGroceriesByKey(name,
            onSuccess,
        onFailure)

    }

    override fun setUpRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfigManager.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfigs() {
        mFirebaseRemoteConfigManager.fetchRemoteConfigs()
    }

    override fun getAppNameFromRemoteConfig(): String {
       return mFirebaseRemoteConfigManager.getToolbarName()
    }

    override fun getGroceryListChangeLayoutFromRemoteConfig(): Long {
        return mFirebaseRemoteConfigManager.groceryListChangeLayout()
    }


}