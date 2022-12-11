package com.padcmyanmar.ttm.groceryapp.data.models

import com.padcmyanmar.ttm.groceryapp.network.auth.AuthManager

interface AuthenticationModel {
    var mAuthManager: AuthManager

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
        email: String,
        password: String,
        userName: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )

    fun getUserName(): String
}