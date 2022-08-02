package com.udemycourse.weatherforecastapp.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.ui.theme.tintColor
import com.udemycourse.weatherforecastapp.R

@Composable
fun WeatherTopAppBar(
    city: String?,
    country: String?
) {
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
                onClick = { /*TODO*/ }
            ) {
              Icon(
                  imageVector = Icons.Default.Search,
                  contentDescription = "Search Icon",
                  tint = MaterialTheme.colors.tintColor
              )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More Option Icon",
                    tint = MaterialTheme.colors.tintColor
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = getFavoriteIcon()),
                    contentDescription = "Heart Icon")
            }
        }
    ) 
}

@Composable
fun getFavoriteIcon(): Int {
    return if (isSystemInDarkTheme()) R.drawable.ic_favorite_light else R.drawable.ic_favorite_dark
}