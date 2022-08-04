package com.udemycourse.weatherforecastapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemycourse.weatherforecastapp.model.Unit
import com.udemycourse.weatherforecastapp.repository.WeatherForecastDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(private val weatherForecastDbRepository: WeatherForecastDbRepository): ViewModel() {

    private val _allUnits = MutableStateFlow<List<Unit>>(emptyList())
    val allUnits: StateFlow<List<Unit>> = _allUnits

    init {
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecastDbRepository.getAllUnits().collect() { unitList ->
                _allUnits.value = unitList
            }
        }
    }

    fun addUnit(unit: Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecastDbRepository.addUnit(unit = unit)
        }
    }

    fun deleteAllUnits() {
        viewModelScope.launch(Dispatchers.IO) {
            weatherForecastDbRepository.deleteAllUnits()
        }
    }
}