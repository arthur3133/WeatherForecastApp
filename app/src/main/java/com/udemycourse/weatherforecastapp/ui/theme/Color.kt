package com.udemycourse.weatherforecastapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val Colors.backgroundColor: Color
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.textColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.circularProgressIndicatorColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White

val Colors.tintColor: Color
    @Composable
    get() = if (isLight) Color.Black else Color.White
