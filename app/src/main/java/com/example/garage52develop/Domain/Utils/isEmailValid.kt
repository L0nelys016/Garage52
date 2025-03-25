package com.example.garage52develop.Domain.Utils

import android.text.TextUtils

fun String.isEmailValid () : Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
