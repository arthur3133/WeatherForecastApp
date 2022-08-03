package com.udemycourse.weatherforecastapp.screens.search

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.navigation.WeatherScreens
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.ui.theme.tintColor

@Composable
fun SearchTopAppBar(
    navController: NavController
) {
    TopAppBar(
        title = {
            Text(
                text = "Search",
                color = MaterialTheme.colors.textColor,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = MaterialTheme.colors.backgroundColor,
        elevation = AppBarDefaults.TopAppBarElevation,
        navigationIcon = {
            IconButton(onClick = {
//                navController.navigate(WeatherScreens.HomeScreen.name) {
//                    popUpTo(WeatherScreens.SearchScreen.name){
//                        inclusive = true
//                    }
//                }
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow_back_icon",
                    tint = MaterialTheme.colors.tintColor
                )
            }
        }
    )
}