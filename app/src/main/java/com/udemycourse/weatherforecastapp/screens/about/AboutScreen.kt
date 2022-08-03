package com.udemycourse.weatherforecastapp.screens.about

import androidx.compose.foundation.background
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
fun AboutScreen(navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.backgroundColor
    ) {
        Scaffold(
            topBar = {
                AppBar(navController = navController, title = "About")
            },
            content = {
                AboutContent()
            }
        )
    }
}