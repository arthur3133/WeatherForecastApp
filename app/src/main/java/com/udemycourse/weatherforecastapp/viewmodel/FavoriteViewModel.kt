package com.udemycourse.weatherforecastapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemycourse.weatherforecastapp.model.Favorite
import com.udemycourse.weatherforecastapp.repository.WeatherForecastDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val weatherForecastDbRepository: WeatherForecastDbRepository): ViewModel() {

    private val _allFavorites = MutableStateFlow<List<Favorite>>(emptyList())
    val allFavorite: StateFlow<List<Favorite>> = _allFavorites

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecastDbRepository.getAllFavorites().collect() { favoriteList ->
                _allFavorites.value = favoriteList
            }
        }
    }

    fun addFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecastDbRepository.addFavorite(favorite = favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecastDbRepository.deleteFavorite(favorite = favorite)
        }
    }
}