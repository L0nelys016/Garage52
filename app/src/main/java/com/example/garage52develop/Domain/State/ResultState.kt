package com.example.garage52develop.Domain.State

import androidx.core.app.NotificationCompat.MessagingStyle.Message

sealed class ResultState {
    data object Loading: ResultState()
    data object Initalized: ResultState()
    data class Success(val message: String) : ResultState()
    data class Error(val message: String) : ResultState()
}