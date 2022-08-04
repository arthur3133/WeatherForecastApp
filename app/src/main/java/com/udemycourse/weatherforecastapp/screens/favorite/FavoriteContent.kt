package com.udemycourse.weatherforecastapp.screens.favorite

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.model.Favorite
import com.udemycourse.weatherforecastapp.navigation.WeatherScreens
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.viewmodel.FavoriteViewModel

@Composable
fun FavoriteContent(
    favoriteViewModel: FavoriteViewModel,
    navController: NavController
) {
    val allFavorites = favoriteViewModel.allFavorite.collectAsState().value
    if (allFavorites.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.backgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "No Favorite City Added",
                fontSize = 24.sp,
                color = MaterialTheme.colors.textColor,
                fontWeight = FontWeight.Bold
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.backgroundColor),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            LazyColumn {
                items(allFavorites) { favorite ->
                    FavoriteRow(favorite = favorite, favoriteViewModel = favoriteViewModel, navController = navController)
                }
            }
        }
    }
}


@Composable
fun FavoriteRow(favorite: Favorite, favoriteViewModel: FavoriteViewModel, navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                       navController.navigate(WeatherScreens.HomeScreen.name+"/${favorite.city}") {
                           popUpTo(WeatherScreens.FavouriteScreen.name) {
                               inclusive = true
                           }
                       }
            },
        shape = CircleShape,
        elevation = 1.dp,
        border = BorderStroke(width = 1.dp, color = Color(0xFFFFC400)),
        color = MaterialTheme.colors.backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(all = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = favorite.city,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = MaterialTheme.colors.textColor
            )
            Surface(
                shape = CircleShape,
                color = Color(0xFFFFC400),
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = favorite.country,
                    fontWeight = FontWeight.Light,
                    color = Color.Black,
                    style = MaterialTheme.typography.caption
                )
            }
            IconButton(onClick = {
                favoriteViewModel.deleteFavorite(favorite = favorite)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete_icon",
                    tint = Color.Red.copy(alpha = 0.5f)
                )
            }
        }
    }
}