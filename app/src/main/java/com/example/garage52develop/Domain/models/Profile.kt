package com.example.garage52develop.Domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile (
    val id: String,
    val name:String,
    val image:String?,
    @SerialName("number_iphone")
    val numberIphone:String,
    val age:String,
    val email:String
)