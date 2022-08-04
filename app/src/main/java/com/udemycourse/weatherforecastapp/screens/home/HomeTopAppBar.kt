package com.udemycourse.weatherforecastapp.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.R
import com.udemycourse.weatherforecastapp.components.MoreOption
import com.udemycourse.weatherforecastapp.model.Favorite
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.ui.theme.tintColor
import com.udemycourse.weatherforecastapp.navigation.WeatherScreens
import com.udemycourse.weatherforecastapp.viewmodel.FavoriteViewModel

@Composable
fun HomeTopAppBar(
    city: String?,
    country: String?,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    if (expanded.value) {
        ShowDropDownMenu(expanded = expanded, navController = navController)
    }

    val context = LocalContext.current
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
                  contentDescription = stringResource(id = R.string.search_icon),
                  tint = MaterialTheme.colors.tintColor
              )
            }
            IconButton(onClick = {
                expanded.value = true
            }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more_option_icon),
                    tint = MaterialTheme.colors.tintColor
                )
            }
        },
        navigationIcon = {
            val favoriteList = favoriteViewModel.allFavorite.collectAsState().value.filter { item ->
                (item.city == city)
            }
            if (favoriteList.isEmpty()) {
                IconButton(onClick = {
                    favoriteViewModel.addFavorite(
                        Favorite(
                            city = city!!,
                            country = country!!
                        )
                    )
                    Toast.makeText(context, "Added $city to favorites", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = stringResource(id = R.string.favorite_icon),
                        tint = MaterialTheme.colors.tintColor
                    )
                }
            } else {
                IconButton(onClick = {
                    favoriteViewModel.deleteFavorite(
                        Favorite(
                            city = city!!,
                            country = country!!
                        )
                    )
                    Toast.makeText(context, "Removed $city from favorites", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(id = R.string.favorite_icon),
                        tint = MaterialTheme.colors.tintColor
                    )
                }
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
    ) {
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.background(MaterialTheme.colors.backgroundColor)
        ) {
            DropdownMenuItem(onClick = {
                expanded.value = false
                navController.navigate(WeatherScreens.FavouriteScreen.name) {
                    popUpTo(WeatherScreens.HomeScreen.name) {
                        inclusive = true
                    }
                }
            }) {
                MoreOption(title = stringResource(id = R.string.favorite), icon = Icons.Default.Favorite)
            }
            DropdownMenuItem(onClick = {
                expanded.value = false
                navController.navigate(WeatherScreens.AboutScreen.name) {
                    popUpTo(WeatherScreens.HomeScreen.name) {
                        inclusive = true
                    }
                }
            }) {
                MoreOption(title = stringResource(id = R.string.about), icon = Icons.Default.Info)
            }
            DropdownMenuItem(onClick = {
                expanded.value = false
                navController.navigate(WeatherScreens.SettingsScreen.name) {
                    popUpTo(WeatherScreens.HomeScreen.name) {
                        inclusive = true
                    }
                }
            }) {
                MoreOption(title = stringResource(id = R.string.settings), icon = Icons.Default.Settings)
            }
        }
    }
}