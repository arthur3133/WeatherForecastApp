package com.udemycourse.weatherforecastapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.udemycourse.weatherforecastapp.screens.about.AboutScreen
import com.udemycourse.weatherforecastapp.screens.favorite.FavoriteScreen
import com.udemycourse.weatherforecastapp.screens.home.HomeScreen
import com.udemycourse.weatherforecastapp.screens.search.SearchScreen
import com.udemycourse.weatherforecastapp.screens.settings.SettingsScreen
import com.udemycourse.weatherforecastapp.screens.splash.SplashScreen
import com.udemycourse.weatherforecastapp.viewmodel.FavoriteViewModel
import com.udemycourse.weatherforecastapp.viewmodel.HomeViewModel
import com.udemycourse.weatherforecastapp.viewmodel.SettingsViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    val homeViewModel: HomeViewModel = hiltViewModel()
    val favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val settingsViewModel: SettingsViewModel = hiltViewModel()

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
                homeViewModel = homeViewModel,
                favoriteViewModel = favoriteViewModel,
                settingsViewModel = settingsViewModel,
                city = city!!
            )
        }
        
        composable(route = WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        composable(route = WeatherScreens.FavouriteScreen.name) {
            FavoriteScreen(navController = navController, favoriteViewModel = favoriteViewModel)
        }

        composable(route = WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        composable(route = WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController = navController, settingsViewModel = settingsViewModel)
        }
    }
}