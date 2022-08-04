package com.udemycourse.weatherforecastapp.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.udemycourse.weatherforecastapp.R
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor

@Composable
fun AboutContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_version),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.textColor,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.api_used),
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.textColor,
            fontWeight = FontWeight.Light
        )
    }
}