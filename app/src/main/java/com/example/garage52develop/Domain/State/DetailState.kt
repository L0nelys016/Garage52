package com.example.garage52develop.Domain.State

import kotlinx.serialization.Serializable

@Serializable
data class DetailState(
    val title: String = "",
    val categoryId: String = "",
    val description: String = "",
    val image: String = "",
)