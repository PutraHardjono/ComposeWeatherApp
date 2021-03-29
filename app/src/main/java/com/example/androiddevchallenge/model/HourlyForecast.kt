package com.example.androiddevchallenge.model

import java.time.LocalDateTime

data class HourlyForecast(
    val time: LocalDateTime,
    val status: Status,
    val temperatureValue: Int,
    val wind: Wind,
    val humidity: Int, // 0 to 100
    val uVIndex: Int, // 0 to 10
    val air: Air,
    val probability: Int
)