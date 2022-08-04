package com.udemycourse.weatherforecastapp.data

import androidx.room.*
import com.udemycourse.weatherforecastapp.model.Favorite
import com.udemycourse.weatherforecastapp.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM favorite_tbl")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("SELECT * FROM unit_tbl")
    fun getAllUnits(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUnit(unit: Unit)

    @Query("DELETE FROM unit_tbl")
    suspend fun deleteAllUnits()
}