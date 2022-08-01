package com.udemycourse.weatherforecastapp.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.R
import com.udemycourse.weatherforecastapp.navigation.WeatherScreens
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var animate by remember {
        mutableStateOf(false)
    }
    val offSet by animateDpAsState(
        targetValue = if (animate) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alpha by animateFloatAsState(
        targetValue = if (animate) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        animate = true
        delay(3000L)
        navController.navigate(route = WeatherScreens.HomeScreen.name)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .size(300.dp)
                    .alpha(alpha),
                shape = CircleShape,
                color = MaterialTheme.colors.backgroundColor,
                border = BorderStroke(width = 2.dp, color = Color.LightGray)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .offset(y = offSet),
                        painter = painterResource(id = getLogo()),
                        contentDescription = "Sunny Icon",
                    )
                    Text(
                        modifier = Modifier.offset(y = offSet),
                        text = "Find the Sun?",
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.textColor
                    )
                }
            }
        }
    }
}

@Composable
fun getLogo(): Int {
    return if (isSystemInDarkTheme()) R.drawable.sunny_light else R.drawable.sunny_dark
}