package com.udemycourse.weatherforecastapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.udemycourse.weatherforecastapp.model.Favorite
import com.udemycourse.weatherforecastapp.model.Unit

@Database(entities = [Favorite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}