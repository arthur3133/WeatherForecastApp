package com.udemycourse.weatherforecastapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.circularProgressIndicatorColor
import com.udemycourse.weatherforecastapp.viewmodel.FavoriteViewModel
import com.udemycourse.weatherforecastapp.viewmodel.HomeViewModel
import com.udemycourse.weatherforecastapp.viewmodel.SettingsViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    favoriteViewModel: FavoriteViewModel,
    settingsViewModel: SettingsViewModel,
    city: String
) {
    val allUnits = settingsViewModel.allUnits.collectAsState().value
    val unit = if (allUnits.isEmpty()) "imperial" else allUnits[0].unit.split(" ")[0].lowercase()

    val isImperial by remember {
        mutableStateOf(unit == "imperial")
    }

    LaunchedEffect(key1 = true) {
        homeViewModel.getWeatherData(city = city, unit = unit)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.backgroundColor
    ) {
        val weatherData = homeViewModel.data
        if (weatherData.value.loading == true) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.circularProgressIndicatorColor
                )
            }
        } else {
                Scaffold(
                    topBar = {
                        HomeTopAppBar(
                            city = weatherData.value.data?.city?.name,
                            country = weatherData.value.data?.city?.country,
                            navController = navController,
                            favoriteViewModel = favoriteViewModel
                        )
                    },
                    content = {
                        WeatherContent(weatherData = weatherData.value.data, isImperial = isImperial)
                    }
                )
        }
    }
}