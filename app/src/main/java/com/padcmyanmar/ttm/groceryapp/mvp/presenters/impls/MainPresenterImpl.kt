package com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.groceryapp.analytics.SCREEN_HOME
import com.padcmyanmar.ttm.groceryapp.data.models.GroceryModelImpl
import com.padcmyanmar.ttm.groceryapp.data.vos.GroceryVO


import com.padcmyanmar.ttm.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.MainPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.views.MainView

class MainPresenterImpl  : MainPresenter, AbstractBasePresenter<MainView>() {

    private val mGroceryModel = GroceryModelImpl
    private var mChosenGroceryForFileUpload: GroceryVO? = null


    override fun onTapAddGrocery(name: String, description: String, amount: Int, imageUrl: String) {
        mGroceryModel.addGrocery(name,description,amount,imageUrl)
    }
    override fun onPhotoTaken(bitmap: Bitmap,onSuccess: (returnUrlString: String) -> Unit){



        mChosenGroceryForFileUpload?.let {
            mGroceryModel.uploadImageAndUpdateGrocery(it, bitmap, onSuccess = {
                it?.let {
                        it1 -> onSuccess(it1)
                }
            })
        }

    }


    override fun onTapFileUpload(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
        mView.openGallery();
    }

    override fun onTapFileUploadForDialog(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
       // mView.openGallery();
    }
    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {
        sendEventsToFirebaseAnalytics(context, SCREEN_HOME)
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFaiure = {
                mView.showErrorMessage(it)
            }
        )

        mView.displayToolbarTitle(mGroceryModel.getAppNameFromRemoteConfig())

        mView.getGroceryListChangeLayoutFromRemoteConfig(mGroceryModel.getGroceryListChangeLayoutFromRemoteConfig())
    }



    override fun onTapDeleteGrocery(name: String) {
        mGroceryModel.removeGrocery(name)
    }

    override fun onTapEditGrocery(name: String, description: String, amount: Int, image: String?) {
        mView.showGroceryDialog(name, description, amount.toString(),image)
    }



}