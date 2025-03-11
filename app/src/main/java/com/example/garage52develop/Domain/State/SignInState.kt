package com.example.garage52develop.Domain.State

data class SignInState (
    val email: String = "",
    val password: String = "",
    val errorEmail: Boolean = false,
    val errorPassword: Boolean = false
)