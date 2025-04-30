package com.example.garage52develop.Domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Detail(
    val id: String,
    val title: String,
    val description: String,
    val categoryId: String,
    val image: String
)