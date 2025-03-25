package com.example.garage52develop.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id:String,
    val name:String,
    val image:String?,
    val number_iphone:String,
    val age:String,
    val email:String
)