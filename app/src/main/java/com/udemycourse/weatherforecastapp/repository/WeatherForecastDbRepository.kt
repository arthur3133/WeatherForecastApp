package com.udemycourse.weatherforecastapp.repository

import com.udemycourse.weatherforecastapp.data.WeatherDao
import com.udemycourse.weatherforecastapp.model.Favorite
import com.udemycourse.weatherforecastapp.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherForecastDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getAllFavorites(): Flow<List<Favorite>> {
        return weatherDao.getAllFavorites()
    }

    suspend fun addFavorite(favorite: Favorite) {
        weatherDao.addFavorite(favorite = favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        weatherDao.deleteFavorite(favorite = favorite)
    }

    fun getAllUnits(): Flow<List<Unit>> {
        return weatherDao.getAllUnits()
    }

    suspend fun addUnit(unit: Unit) {
        weatherDao.addUnit(unit = unit)
    }

    suspend fun deleteAllUnits() {
        weatherDao.deleteAllUnits()
    }
}