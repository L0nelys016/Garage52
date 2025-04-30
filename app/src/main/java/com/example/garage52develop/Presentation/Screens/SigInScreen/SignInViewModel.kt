package com.example.garage52develop.Presentation.Screens.SigInScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.garage52develop.Domain.State.SignInState
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes
import com.example.garage52develop.Domain.Utils.isEmailValid
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.launch
import io.github.jan.supabase.auth.providers.builtin.Email
import android.widget.Toast
import com.example.garage52develop.Domain.Constant
import com.example.garage52develop.Domain.Utils.isPasswordValid

class SignInViewModel : ViewModel() {
    private val _state = mutableStateOf(SignInState())
    val uistate: SignInState get() = _state.value

    fun updateState(newState: SignInState) {
        _state.value = newState
    }

    fun SignIn(controller: NavHostController, context: Context) {
        viewModelScope.launch {
            try {
                if (uistate.password.isEmpty() && uistate.email.isEmpty()) {
                    Toast.makeText(context, "Поля пустые", Toast.LENGTH_LONG).show()
                } else {
                    if (uistate.email.isEmailValid()) {
                        if (uistate.password.isPasswordValid()) {
                            Constant.supabase.auth.signOut()
                            Constant.supabase.auth.signInWith(Email) {
                                email = uistate.email
                                password = uistate.password
                            }
                            controller.navigate(NavigationRoutes.MAIN)
                        } else {
                            Toast.makeText(
                                context,
                                "Неверный пароль",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Неверный email",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(context, "Не удалось авторизоваться", Toast.LENGTH_LONG).show()
            }
        }
    }
}
