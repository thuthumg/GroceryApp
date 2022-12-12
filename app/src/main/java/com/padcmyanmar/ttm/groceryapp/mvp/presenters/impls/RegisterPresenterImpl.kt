package com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.padcmyanmar.ttm.groceryapp.analytics.PARAMETER_EMAIL
import com.padcmyanmar.ttm.groceryapp.analytics.SCREEN_HOME
import com.padcmyanmar.ttm.groceryapp.analytics.TAP_REGISTER
import com.padcmyanmar.ttm.groceryapp.data.models.AuthenticationModel
import com.padcmyanmar.ttm.groceryapp.data.models.AuthenticationModelImpl
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.AbstractBasePresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.RegisterPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.views.RegisterView

class RegisterPresenterImpl  : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mAuthenticationModel: AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(context:Context,email: String, password: String, userName: String) {
        sendEventsToFirebaseAnalytics(context, TAP_REGISTER, PARAMETER_EMAIL, email)

        mAuthenticationModel.register(email, password, userName, onSuccess = {
            mView.navigateToToLoginScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(
        context: Context,
        owner: LifecycleOwner
    ) {
        sendEventsToFirebaseAnalytics(context, SCREEN_HOME)
    }
}