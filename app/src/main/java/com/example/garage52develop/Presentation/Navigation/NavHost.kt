package com.example.garage52develop.Presentation.Navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.garage52develop.Presentation.Screens.SplashScreen.SplashScreen
import com.example.garage52develop.Presentation.Screens.MainSreen.MainScreen
import com.example.garage52develop.Presentation.Screens.SigInScreen.SignInScreen
import com.example.garage52develop.Presentation.Screens.SignUpScreen.SignUpScreen
import com.example.garage52develop.Presentation.Screens.CreateCard.CreateCardViewScreen
import com.example.garage52develop.Presentation.Screens.ChangeScreen.ChangeViewScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
                NavHost(navController = navController, startDestination = NavigationRoutes.SPLASH) {
                    composable(NavigationRoutes.SPLASH)
                    {
                       SplashScreen(navController)
                    }
                    composable(NavigationRoutes.SIGNIN)
                    {
                       SignInScreen(navController)
                    }
                    composable(NavigationRoutes.SIGNUP)
                    {
                       SignUpScreen(navController)
                    }
                    composable(NavigationRoutes.MAIN)
                    {
                        MainScreen(navController)
                    }
                    composable(NavigationRoutes.CREATECARD)
                    {
                        CreateCardViewScreen(navController)
                    }
                }
            }
        }
    }
