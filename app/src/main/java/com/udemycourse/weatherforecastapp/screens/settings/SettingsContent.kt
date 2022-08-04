package com.udemycourse.weatherforecastapp.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.udemycourse.weatherforecastapp.R
import com.udemycourse.weatherforecastapp.model.Unit
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.viewmodel.SettingsViewModel

@Composable
fun SettingsContent(settingsViewModel: SettingsViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var unitState by remember {
            mutableStateOf(false)
        }
        val allUnits = settingsViewModel.allUnits.collectAsState().value
        val defaultUnit = if (allUnits.isEmpty()) "Imperial (F)" else allUnits[0].unit
        var choiceState by remember {
            mutableStateOf(defaultUnit)
        }
        Text(
            text = stringResource(id = R.string.change_units_of_measurement),
            color = MaterialTheme.colors.textColor,
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            unitState = !unitState
            choiceState = if (unitState) {
                "Imperial (F)"
            } else {
                "Metric (C)"
            }
        },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta.copy(alpha = 0.6f),
                contentColor = Color.Black
            ),
        shape = RectangleShape) {
            Text(
                text = if (unitState) "Fahrenheit ºF" else "Celsius ºC",
                color = MaterialTheme.colors.textColor,
                modifier = Modifier.padding(5.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            settingsViewModel.deleteAllUnits()
                         settingsViewModel.addUnit(Unit(
                             unit = choiceState
                         ))
            },
            modifier = Modifier
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFFC400),
                contentColor = Color.Black
            ),
            shape = RoundedCornerShape(34.dp)
        ) {
            Text(
                text = stringResource(id = R.string.save),
                color = Color.Black,
                modifier = Modifier.padding(4.dp),
                fontSize = 17.sp
            )
        }
    }
}