package com.udemycourse.weatherforecastapp.di

import android.content.Context
import androidx.room.Room
import com.udemycourse.weatherforecastapp.data.FavoriteDao
import com.udemycourse.weatherforecastapp.data.FavoriteDatabase
import com.udemycourse.weatherforecastapp.model.Weather
import com.udemycourse.weatherforecastapp.remote.WeatherApi
import com.udemycourse.weatherforecastapp.repository.FavoriteRepository
import com.udemycourse.weatherforecastapp.repository.WeatherForecastRepository
import com.udemycourse.weatherforecastapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            "favorite_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(favoriteDatabase: FavoriteDatabase): FavoriteDao {
        return favoriteDatabase.favoriteDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteRepository(favoriteDao: FavoriteDao) = FavoriteRepository(favoriteDao)
}