package com.udemycourse.weatherforecastapp.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.udemycourse.weatherforecastapp.model.Weather
import com.udemycourse.weatherforecastapp.ui.theme.backgroundColor
import com.udemycourse.weatherforecastapp.ui.theme.textColor
import com.udemycourse.weatherforecastapp.utils.Constants.formatDate
import com.udemycourse.weatherforecastapp.utils.Constants.formatDecimal
import com.udemycourse.weatherforecastapp.R
import com.udemycourse.weatherforecastapp.model.WeatherItem
import com.udemycourse.weatherforecastapp.utils.Constants.formatDateTime

@Composable
fun WeatherContent(weatherData: Weather?, isImperial: Boolean) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.backgroundColor),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherData!!.list[0].dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.textColor,
            modifier = Modifier.padding(all = 6.dp)
        )
        Surface(
            modifier = Modifier
                .size(200.dp)
                .padding(4.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageUrl = "https://openweathermap.org/img/wn/${weatherData.list[0].weather[0].icon}.png"
                WeatherImage(imageUrl = imageUrl, imageSize = 80.dp)
                Text(
                    text = formatDecimal(weatherData.list[0].temp.day)+"º",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Text(
                    text = weatherData.list[0].weather[0].main,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
            }
        }
        HumidityWindPressureRow(weatherData = weatherData, isImperial = isImperial)
        Divider()
        SunRiseAndSunSetRow(weatherData = weatherData)
        Text(
            text = stringResource(id = R.string.this_week),
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.textColor
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(weatherData.list) { item: WeatherItem ->
                WeatherDetailRow(item)
            }
        }
    }
}

@Composable
fun WeatherImage(imageUrl: String, imageSize: Dp) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = R.string.weather_image),
        modifier = Modifier.size(imageSize)
    )
}

@Composable
fun HumidityWindPressureRow(weatherData: Weather?, isImperial: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = getHumidityIcon()), 
                contentDescription = stringResource(id = R.string.humidity_icon),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "${weatherData!!.list[0].humidity} %",
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = getPressureIcon()),
                contentDescription = stringResource(id = R.string.pressure_icon),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "${weatherData!!.list[0].pressure} psi",
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = getWindIcon()),
                contentDescription = stringResource(id = R.string.wind_icon),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "${weatherData!!.list[0].speed} " + if (isImperial) "mph" else "m/s",
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}

@Composable
fun getHumidityIcon(): Int {
    return if (isSystemInDarkTheme()) R.drawable.ic_humdity_light else R.drawable.ic_humidity_dark
}

@Composable
fun getPressureIcon(): Int {
    return if (isSystemInDarkTheme()) R.drawable.ic_pressure_light else R.drawable.ic_pressure_dark
}

@Composable
fun getWindIcon(): Int {
    return if (isSystemInDarkTheme()) R.drawable.ic_wind_light else R.drawable.ic_wind_dark
}

@Composable
fun SunRiseAndSunSetRow(weatherData: Weather?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = stringResource(id = R.string.sunrise_icon),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = formatDateTime(weatherData!!.list[0].sunrise),
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = formatDateTime(weatherData!!.list[0].sunset),
                color = MaterialTheme.colors.textColor,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(end = 4.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = stringResource(id = R.string.sunset_icon),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun WeatherDetailRow(weatherItem: WeatherItem) {
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.5.dp , color = Color(0xFFFFC400)),
        elevation = 2.dp,
        color = MaterialTheme.colors.backgroundColor
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDate(weatherItem.dt).split(",")[0],
                color = MaterialTheme.colors.textColor,
                modifier = Modifier.padding(start = 6.dp)
            )
            WeatherImage(imageUrl = imageUrl, imageSize = 50.dp)
            Surface(
                shape = CircleShape,
                color = Color(0xFFFFC400),
            ) {
                Text(
                    text = weatherItem.weather[0].description,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.caption,
                    color = Color.Black
                )
            }
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    color = if (isSystemInDarkTheme()) Color(0xFFFFC400) else Color.Blue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.SemiBold
                )) {
                    append(formatDecimal(weatherItem.temp.max) + "º")
                }
                withStyle(style = SpanStyle(
                    color = Color.LightGray
                )) {
                    append(formatDecimal(weatherItem.temp.min) + "º")
                }
            },
            modifier = Modifier.padding(end = 6.dp))
        }
    }
}