package com.example.garage52develop.Presentation.Screens.SignUpScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun SignUpScreen(
    navHostController: NavHostController
) {

    val vs = viewModel { SignUpViewModel() }
    val state = vs.uistate
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .size(250.dp)
                .padding(top = 120.dp),
            painter = painterResource(id = R.drawable.garage52),
            contentDescription = "Logo"
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 100.dp),
            value = state.login,
            onValueChange = { vs.updateState(state.copy(login = it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Person),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_login_user))
            }
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 10.dp),
            value = state.email,
            onValueChange = { vs.updateState(state.copy(email = it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_email))
            }
        )

        OutlinedTextField(
            value = state.phoneNumber,
            onValueChange = { vs.updateState(state.copy(phoneNumber = it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Phone),
                    contentDescription = null
                )
            },
            modifier = Modifier.padding(top = 10.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.number_iphone))
            }
        )

        OutlinedTextField(
            value = state.age,
            onValueChange = { vs.updateState(state.copy(age = it)) },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.AccountCircle),
                    contentDescription = null
                )
            },
            modifier = Modifier.padding(top = 10.dp),
            placeholder = {
                Text(text = stringResource(id = R.string.apply_age))
            }
        )

        OutlinedTextField(
            value = state.password,
            onValueChange = { vs.updateState(state.copy(password = it)) },
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

        ButtonNavigation(
            onClick = { vs.signUp(navHostController, context) },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.Registration),
                fontSize = 19.sp
            )
        }

        Text(
            text = stringResource(id = R.string.yes_account_register),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    navHostController.navigate(NavigationRoutes.SIGNIN)
                }
        )
    }
}