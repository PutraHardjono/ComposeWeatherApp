package com.example.androiddevchallenge.model

import java.time.LocalDate
import java.time.LocalTime

data class DailyForecast(
    val date: LocalDate = LocalDate.now(),
    var status: Status = Status.CLOUDY_DAY,
    val temperature: Temperature = Temperature(0, 0, 0),
    val wind: Wind = Wind(0, 0),
    val humidity: Int = 0, // 0 to 100
    val uVIndex: Int = 0, // 0 to 10
    val air: Air = Air(0),
    val sun: Sun = Sun(LocalTime.now(), LocalTime.now())
)