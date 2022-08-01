package com.udemycourse.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udemycourse.weatherforecastapp.screens.splash.SplashScreen

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, 
        startDestination = WeatherScreens.SplashScreen.name) {
        composable(
            route = WeatherScreens.SplashScreen.name,
        ) {
            SplashScreen(navController = navController)
        }
    }
    
}