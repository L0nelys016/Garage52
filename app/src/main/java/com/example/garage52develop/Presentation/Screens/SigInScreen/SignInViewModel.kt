package com.example.garage52develop.Presentation.Screens.SigInScreen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.garage52develop.Domain.Constant
import com.example.garage52develop.Domain.State.ResultState
import com.example.garage52develop.Domain.State.SignInState
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes
import com.example.supabasesimpleproject.Domain.Utils.isEmailValid
import io.github.jan.supabase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email


class SignInViewModel: ViewModel() {
 private val _uiState = mutableStateOf(SignInState())
    val uiState: SignInState get () = _uiState.value

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Initalized)
    val resultState: StateFlow<ResultState> = _resultState.asStateFlow()

    fun updateState(newState: SignInState) {
       _uiState.value = newState
       _uiState.value.errorEmail = _uiState.value.email.isEmailValid()
        _resultState.value = ResultState.Initalized
    }

    fun signIn(navHostController: NavHostController, context: Context) {
        _resultState.value = ResultState.Loading
        if (_uiState.value.errorEmail) {
            viewModelScope.launch {
                try {
                    Constant.supabase.auth.signOut()
                   Constant.supabase.auth.signInWith(Email)
                    {
                        email = _uiState.value.email
                        password = _uiState.value.password
                    }
                    navHostController.navigate(NavigationRoutes.MAIN)
                    Log.d("SignIn", "Success")

                    _resultState.value = ResultState.Success("Success")
                } catch (_ex: AuthRestException) {
                   Log.d("SignIn", _ex.message.toString())
                    Log.d("SignIn", _ex.errorCode.toString())
                    Log.d("SignIn", _ex.errorDescription.toString())
                    _resultState.value = ResultState.Error(_ex.errorDescription ?: "Ошибка получения данных")
                }
            }
        }
        else{
            _resultState.value = ResultState.Error( "Ошибка ввода почты")
        }
    }
}