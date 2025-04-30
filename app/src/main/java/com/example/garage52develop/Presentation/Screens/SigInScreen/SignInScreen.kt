package com.example.garage52develop.Presentation.Screens.SigInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes
import com.example.garage52develop.Presentation.Screens.Components.ButtonNavigation
import com.example.garage52develop.R
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle

@Composable
fun SignInScreen (
    navHostController: NavHostController,
    viewModel: SignInViewModel = viewModel()
) {

    val uistate = viewModel{SignInViewModel()}
    val state = uistate.uistate
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(285.dp)
                .padding(top = 170.dp),
            painter = painterResource(id = R.drawable.garage52),
            contentDescription = "Logo"
        )
        val message = remember{mutableStateOf("Hello")}

        OutlinedTextField(
            modifier = Modifier.padding(top = 220.dp),
            value = state.email,
            onValueChange = {uistate.updateState(state.copy(email = it))},
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(R.string.enter_email))
            }
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = {uistate.updateState(state.copy(password = it))},
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                    contentDescription = null
                )
            },
            modifier = Modifier.padding(top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(text = stringResource(id = R.string.enter_password))
            }
        )

        ButtonNavigation(onClick = {uistate.SignIn(navHostController, context)},
            modifier = Modifier.padding(top = 50.dp)
            ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 19.sp
            )
        }

        Text(
            text = stringResource(id = R.string.no_account_register),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    navHostController.navigate(NavigationRoutes.SIGNUP)
                }
        )

    }
}