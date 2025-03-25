package com.example.garage52develop.Domain.Utils

import android.text.TextUtils

fun String.isAgeValid():Boolean{
    return !TextUtils.isEmpty(this) && this.all { char -> char.isDigit()}
}