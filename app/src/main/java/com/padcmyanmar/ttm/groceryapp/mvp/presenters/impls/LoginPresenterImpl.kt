package com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.groceryapp.data.models.AuthenticationModel
import com.padcmyanmar.ttm.groceryapp.data.models.AuthenticationModelImpl
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.LoginPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onUiReady(owner: LifecycleOwner) {}

    override fun onTapLogin(email: String, password: String) {
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