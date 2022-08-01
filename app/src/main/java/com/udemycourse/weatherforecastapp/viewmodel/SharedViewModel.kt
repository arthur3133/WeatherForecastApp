package com.udemycourse.weatherforecastapp.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemycourse.weatherforecastapp.data.DataOrException
import com.udemycourse.weatherforecastapp.model.Weather
import com.udemycourse.weatherforecastapp.repository.WeatherForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: WeatherForecastRepository): ViewModel() {
    val data: MutableState<DataOrException<Weather, Boolean, Exception>> =
        mutableStateOf(DataOrException(data = null, loading = true, e = null))

    fun getWeatherData(city: String) {
        try {
            data.value.loading = true
            viewModelScope.launch(Dispatchers.IO) {
                data.value = repository.getWeatherData(city = city)
                if (data.value.data.toString().isNotEmpty())
                    data.value.loading = false
            }
        } catch (e: Exception) {
            data.value.e = e
        }
    }

}