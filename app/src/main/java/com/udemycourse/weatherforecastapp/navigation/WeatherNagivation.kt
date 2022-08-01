package com.udemycourse.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udemycourse.weatherforecastapp.screens.home.HomeScreen
import com.udemycourse.weatherforecastapp.screens.splash.SplashScreen
import com.udemycourse.weatherforecastapp.viewmodel.SharedViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    val sharedViewModel: SharedViewModel = hiltViewModel()
    NavHost(
        navController = navController, 
        startDestination = WeatherScreens.SplashScreen.name) {
       
        composable(
            route = WeatherScreens.SplashScreen.name,
        ) {
            SplashScreen(navController = navController)
        }
        
        composable(
            route = WeatherScreens.HomeScreen.name
        ) {
            HomeScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
    }
}