package com.udemycourse.weatherforecastapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.circularProgressIndicatorColor
import com.udemycourse.weatherforecastapp.viewmodel.SharedViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    city: String
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getWeatherData(city = city)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.backgroundColor
    ) {
        val weatherData = sharedViewModel.data
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
                            navController = navController
                        )
                    },
                    content = {
                        WeatherContent(weatherData = weatherData.value.data)
                    }
                )
        }
    }
}