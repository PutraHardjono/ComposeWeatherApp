package com.example.androiddevchallenge

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.model.DailyForecast
import com.example.androiddevchallenge.model.HourlyForecast
import com.example.androiddevchallenge.utils.FakeDatasource

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {

    var location by mutableStateOf("Dumai Timur")
        private set

    var currentForecast by mutableStateOf(DailyForecast())
        private set

    var dailyForecasts by mutableStateOf(listOf<DailyForecast>())
        private set

    var hourlyForecasts by mutableStateOf(listOf<HourlyForecast>())

    init {
        Log.d("MainViewModel", "Init ViewModel")
        dailyForecasts = FakeDatasource.getDailyForecasts()
        hourlyForecasts = FakeDatasource.getHourlyForecasts(dailyForecasts.first())
        currentForecast = dailyForecasts.first()
//        currentForecast.status = Status.SNOW_STORM
    }

    fun setCurrentDailyForecast(dailyForecast: DailyForecast) {
        currentForecast = dailyForecast
        Log.d("MainViewModel","setCurrentDailyForecast")
    }
}