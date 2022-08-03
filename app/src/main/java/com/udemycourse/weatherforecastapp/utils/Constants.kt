package com.udemycourse.weatherforecastapp.utils

import java.text.SimpleDateFormat
import java.util.*

object Constants {

    //https://api.openweathermap.org/data/2.5/forecast/daily?q=ahmedabad&appid=ed60fcfbd110ee65c7150605ea8aceea&units=imperial

    const val BASE_URL = "https://api.openweathermap.org/"

    const val API_KEY = "ed60fcfbd110ee65c7150605ea8aceea"

    const val defaultCity = "Ahmedabad"

    fun formatDate(timeStamp: Int): String {
        val sdf = SimpleDateFormat("EEE, MMM d")
        val date = Date(timeStamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun formatDateTime(timeStamp: Int): String {
        val sdf = SimpleDateFormat("hh:mm aa")
        val date = Date(timeStamp.toLong() * 1000)
        return sdf.format(date)
    }

    fun formatDecimal(item: Double): String {
        return " %.0f".format(item)
    }
}