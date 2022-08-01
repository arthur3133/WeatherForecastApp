package com.udemycourse.weatherforecastapp.remote

import com.udemycourse.weatherforecastapp.model.Weather
import com.udemycourse.weatherforecastapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET("data/2.5/forecast/daily")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "imperial"
    ): Weather
}