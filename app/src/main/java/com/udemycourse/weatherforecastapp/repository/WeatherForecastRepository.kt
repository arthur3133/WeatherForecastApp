package com.udemycourse.weatherforecastapp.repository

import com.udemycourse.weatherforecastapp.data.DataOrException
import com.udemycourse.weatherforecastapp.model.Weather
import com.udemycourse.weatherforecastapp.remote.WeatherApi
import javax.inject.Inject

class WeatherForecastRepository @Inject constructor(private val weatherApi: WeatherApi) {

    private var dataOrException: DataOrException<Weather, Boolean, Exception> = DataOrException()

    suspend fun getWeatherData(city: String): DataOrException<Weather, Boolean, Exception> {
        try {
            dataOrException.data = weatherApi.getWeatherData(city = city)
        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }
}