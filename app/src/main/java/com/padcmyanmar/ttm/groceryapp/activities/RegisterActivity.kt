package com.padcmyanmar.ttm.groceryapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.RegisterPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls.RegisterPresenterImpl
import com.padcmyanmar.ttm.groceryapp.mvp.views.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    private lateinit var mPresenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setUpPresenter()
        setUpActionListeners()
        mPresenter.onUiReady(this, this)

    }

    private fun setUpActionListeners() {
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(
                this,
                etEmail.text.toString(),
                etPassword.text.toString(),
                etUserName.text.toString()
            )
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl, RegisterView>()
    }

    override fun navigateToToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
    }
}