package com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.groceryapp.analytics.PARAMETER_EMAIL
import com.padcmyanmar.ttm.groceryapp.analytics.SCREEN_LOGIN
import com.padcmyanmar.ttm.groceryapp.analytics.TAP_LOGIN
import com.padcmyanmar.ttm.groceryapp.data.models.AuthenticationModel
import com.padcmyanmar.ttm.groceryapp.data.models.AuthenticationModelImpl
import com.padcmyanmar.ttm.groceryapp.data.models.GroceryModel
import com.padcmyanmar.ttm.groceryapp.data.models.GroceryModelImpl
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.LoginPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl
    private val mGroceryModel: GroceryModel = GroceryModelImpl

    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ){
        sendEventsToFirebaseAnalytics(context, SCREEN_LOGIN)
        mGroceryModel.setUpRemoteConfigWithDefaultValues()
        mGroceryModel.fetchRemoteConfigs()

    }

    override fun onTapLogin(context: Context,email: String, password: String) {
        sendEventsToFirebaseAnalytics(context, TAP_LOGIN, PARAMETER_EMAIL, email)
        mAuthenticationModel.login(email, password, onSuccess = {
            mView.navigateToHomeScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }

    override fun getUserName(): String {
      return  mAuthenticationModel.getUserName() ?: ""
    }
}