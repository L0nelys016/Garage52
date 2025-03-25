package com.example.garage52develop.Domain.Utils

import android.text.TextUtils

fun String.isPhoneNumberValid ():Boolean{
    return !TextUtils.isEmpty(this) && android.util.Patterns.PHONE.matcher(this).matches()
}