package com.padcmyanmar.ttm.groceryapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.padcmyanmar.ttm.groceryapp.R
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.LoginPresenter
import com.padcmyanmar.ttm.groceryapp.mvp.presenters.impls.LoginPresenterImpl
import com.padcmyanmar.ttm.groceryapp.mvp.views.LoginView
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity  : BaseActivity(), LoginView {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    private lateinit var mPresenter: LoginPresenter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(findViewById(R.id.toolbar))

        setUpPresenter()
        setUpActionListeners()

        mPresenter.onUiReady(this,this)
    }

    private fun setUpActionListeners() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(this,etEmail.text.toString(), etPassword.text.toString())
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl, LoginView>()
    }

    override fun navigateToHomeScreen() {

        startActivity(MainActivity.newIntent(this,  mPresenter.getUserName()))
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }
}