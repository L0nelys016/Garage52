package com.example.garage52develop.Presentation.Screens.SignUpScreen

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class SignUpViewModel: ViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    fun updateLogin(login: String) {
        this.login = login
    }

    fun updateEmail(email: String) {
        this.email = email
    }

    fun updatePassword(password: String) {
        this.password = password
    }
}