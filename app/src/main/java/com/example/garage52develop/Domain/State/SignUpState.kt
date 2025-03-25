package com.example.garage52develop.Domain.State

data class SignUpState (
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isErrorEmail: Boolean = false,
    val phoneNumber: String = "",
    val age: String = "",

)