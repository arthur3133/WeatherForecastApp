package com.udemycourse.weatherforecastapp.screens.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.components.AppBar
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.backgroundColor
    ) {
        Scaffold(
            topBar = {
                AppBar(navController = navController, title = "Settings")
            },
            content = {
                SettingsContent(settingsViewModel = settingsViewModel)
            }
        )
    }
}