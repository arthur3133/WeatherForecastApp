package com.udemycourse.weatherforecastapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.udemycourse.weatherforecastapp.screens.home.HomeScreen
import com.udemycourse.weatherforecastapp.screens.search.SearchScreen
import com.udemycourse.weatherforecastapp.screens.splash.SplashScreen
import com.udemycourse.weatherforecastapp.utils.Constants.defaultCity
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
            route = WeatherScreens.HomeScreen.name+"/{city}",
            arguments = listOf(navArgument("city"){
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val city = navBackStackEntry.arguments?.getString("city")
            HomeScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
                city = city!!
            )
        }
        
        composable(route = WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }
    }
}