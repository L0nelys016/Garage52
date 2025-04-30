package com.example.garage52develop.Presentation.Screens.SignUpScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.garage52develop.Domain.models.Profile
import com.example.garage52develop.Domain.Constant
import com.example.garage52develop.Domain.State.SignUpState
import com.example.garage52develop.Domain.Utils.isEmailValid
import com.example.garage52develop.Domain.Utils.isPasswordValid
import com.example.garage52develop.Domain.Utils.isPhoneNumberValid
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val uistate: SignUpState get() = _state.value

    fun updateState(newState: SignUpState) {
        _state.value = newState
    }

    fun signUp(controller: NavHostController, context: Context) {
        viewModelScope.launch {
            try {
                if (uistate.password.isEmpty() &&
                    uistate.email.isEmpty() &&
                    uistate.phoneNumber.isEmpty() &&
                    uistate.age.isEmpty() &&
                    uistate.login.isEmpty()
                ) {
                    Toast.makeText(context, "Поля пустые", Toast.LENGTH_LONG).show()
                } else {
                    if (!uistate.email.isEmailValid()) {
                        Toast.makeText(
                            context,
                            "Email не соответствует",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        if (!uistate.password.isPasswordValid()) {
                            Toast.makeText(
                                context,
                                "В пароле должны быть заглавные буквы, строчные, спец. символы и цифры",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            if (!uistate.phoneNumber.isPhoneNumberValid()) {
                                Toast.makeText(
                                    context,
                                    "Номер телефона не соответствует",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {

                                Constant.supabase.auth.signUpWith(Email) {
                                    email = uistate.email
                                    password = uistate.password
                                }

                                controller.navigate(NavigationRoutes.SIGNIN)
                                val iduser = Constant.supabase.auth.currentUserOrNull()
                                if (iduser != null) {
                                    Constant.supabase.from("profile").insert(
                                        Profile(
                                            id = iduser.id,
                                            email = uistate.email,
                                            name = uistate.login,
                                            age = uistate.age,
                                            numberIphone = uistate.phoneNumber,
                                            image = null,
                                        )
                                    )


                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("Не удалось зарегистрироваться", e.message.toString())
            }

        }
    }
}