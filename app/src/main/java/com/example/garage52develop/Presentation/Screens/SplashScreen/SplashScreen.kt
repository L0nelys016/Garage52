
package com.example.garage52develop.Presentation.Screens.SplashScreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.garage52develop.Presentation.Navigation.NavigationRoutes
import com.example.garage52develop.R

import kotlinx.coroutines.delay


@Composable
fun SplashScreen (navController: NavHostController) {


        val scale = remember {
            Animatable(0f)
        }

        LaunchedEffect (key1 = true) {
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(
                    durationMillis = 1500,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(1000L)

            navController.navigate(NavigationRoutes.SIGNIN) {
                popUpTo(NavigationRoutes.SPLASH) {
                    inclusive = true
                }
            }
        }

            Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .weight(1f)
                    .scale(scale.value ),
                painter = painterResource(id = R.drawable.garage52),
                contentDescription = "Logo"
            )
        }
}
