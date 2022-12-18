package com.padcmyanmar.ttm.groceryapp.activities

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging

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
        setContentView(com.padcmyanmar.ttm.groceryapp.R.layout.activity_login)
        setSupportActionBar(findViewById(com.padcmyanmar.ttm.groceryapp.R.id.toolbar))

        setUpPresenter()
        setUpActionListeners()


            FirebaseInstallations.getInstance().id.addOnSuccessListener {
            Log.d("fbToken", it)
        }

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("fbToken", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new FCM registration token
                val token = task.result

                Log.d("LoginActivity", token)

            })
        mPresenter.onUiReady(this,this)
    }

    private fun setUpActionListeners() {
        btnLogin.setOnClickListener {

            if(etEmail.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty())
            {
                mPresenter.onTapLogin(this,etEmail.text.toString(), etPassword.text.toString())
            }else{
                Toast.makeText(this, "Please fill the Email and Password.",Toast.LENGTH_SHORT).show()
            }


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