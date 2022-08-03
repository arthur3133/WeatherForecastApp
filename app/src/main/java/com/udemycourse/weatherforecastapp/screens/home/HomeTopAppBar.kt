package com.udemycourse.weatherforecastapp.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.components.MoreOption
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.ui.theme.tintColor
import com.udemycourse.weatherforecastapp.navigation.WeatherScreens

@Composable
fun HomeTopAppBar(
    city: String?,
    country: String?,
    navController: NavController
) {
    var expanded = remember {
        mutableStateOf(false)
    }
    if (expanded.value) {
        ShowDropDownMenu(expanded = expanded, navController = navController)
    }
    TopAppBar(
        title = {
            Text(
                text = "$city, $country",
                color = MaterialTheme.colors.textColor,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = MaterialTheme.colors.backgroundColor,
        elevation = AppBarDefaults.TopAppBarElevation,
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = WeatherScreens.SearchScreen.name)
                }
            ) {
              Icon(
                  imageVector = Icons.Default.Search,
                  contentDescription = "search_icon",
                  tint = MaterialTheme.colors.tintColor
              )
            }
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more_option_icon",
                    tint = MaterialTheme.colors.tintColor
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite_icon",
                    tint = MaterialTheme.colors.tintColor
                )
            }
        }
    ) 
}


@Composable
fun ShowDropDownMenu(expanded: MutableState<Boolean>, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .padding(top = 45.dp, end = 20.dp)
            .background(MaterialTheme.colors.backgroundColor)
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.background(MaterialTheme.colors.backgroundColor)
        ) {
            DropdownMenuItem(onClick = { expanded.value = false }) {
                MoreOption(title = "Favorite", icon = Icons.Default.Favorite)
            }
            DropdownMenuItem(onClick = { expanded.value = false }) {
                MoreOption(title = "About", icon = Icons.Default.Info)
            }
            DropdownMenuItem(onClick = { expanded.value = false }) {
                MoreOption(title = "Settings", icon = Icons.Default.Settings)
            }
        }
    }
}