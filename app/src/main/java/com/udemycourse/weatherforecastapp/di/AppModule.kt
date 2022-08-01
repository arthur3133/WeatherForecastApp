package com.udemycourse.weatherforecastapp.di

import com.udemycourse.weatherforecastapp.model.Weather
import com.udemycourse.weatherforecastapp.remote.WeatherApi
import com.udemycourse.weatherforecastapp.repository.WeatherForecastRepository
import com.udemycourse.weatherforecastapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOpenWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWeatherForecastRepository(weatherApi: WeatherApi) = WeatherForecastRepository(weatherApi)
}