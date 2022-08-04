package com.udemycourse.weatherforecastapp.repository

import com.udemycourse.weatherforecastapp.data.FavoriteDao
import com.udemycourse.weatherforecastapp.model.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val favoriteDao: FavoriteDao) {

    fun getAllFavorites(): Flow<List<Favorite>> {
        return favoriteDao.getAllFavorites()
    }

    suspend fun addFavorite(favorite: Favorite) {
        favoriteDao.addFavorite(favorite = favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDao.deleteFavorite(favorite = favorite)
    }
}