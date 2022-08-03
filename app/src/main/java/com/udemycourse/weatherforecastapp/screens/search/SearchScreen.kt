package com.udemycourse.weatherforecastapp.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.components.AppBar
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor

@Composable
fun SearchScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.backgroundColor
    ) {
        Scaffold(
            topBar = {
                AppBar(navController = navController, title = "Search")
            },
            content = {
                SearchContent(navController = navController)
            }
        )
    }
}