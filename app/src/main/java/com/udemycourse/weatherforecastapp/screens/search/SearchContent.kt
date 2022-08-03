package com.udemycourse.weatherforecastapp.screens.search

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udemycourse.weatherforecastapp.navigation.WeatherScreens
import com.udemycourse.weatherforecastapp.ui.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchContent(
    navController: NavController
) {
    val searchText = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val valid = remember(searchText.value) {
        searchText.value.trim().isNotEmpty()
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.backgroundColor).padding(12.dp),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = searchText.value,
            onValueChange = {
                searchText.value = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "City")
            },
            placeholder = {
                Text(text = "City")
            },
            singleLine = true,
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.textColor,
                backgroundColor = MaterialTheme.colors.backgroundColor,
                cursorColor = MaterialTheme.colors.cursorColor,
                focusedLabelColor = MaterialTheme.colors.focusedLabelColor,
                focusedIndicatorColor = MaterialTheme.colors.focusedIndicatorColor
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    if (valid) {
                        keyboardController?.hide()
                        navController.navigate(WeatherScreens.HomeScreen.name + "/${searchText.value.trim()}") {
                            popUpTo(WeatherScreens.SearchScreen.name) {
                                inclusive = true
                            }
                        }
                    } else {
                        Toast.makeText(context, "Empty Field!", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        )
    }
}