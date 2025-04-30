package com.example.garage52develop.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val id: String,
    val name: String
)