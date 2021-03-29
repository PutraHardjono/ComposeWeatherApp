package com.example.androiddevchallenge.utils

import com.example.androiddevchallenge.model.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.random.Random

object FakeDatasource {
    fun getHourlyForecasts(dailyForecast: DailyForecast): List<HourlyForecast> {
        val hourlyForecast = mutableListOf<HourlyForecast>()
        var currentTime = LocalDateTime.now().withMinute(0).withSecond(0)

        repeat(24) { // 24 hours
            currentTime = currentTime.plusHours(1)
            hourlyForecast.add(
                HourlyForecast(
                    time = currentTime,
                    status = randomTropicsStatus(
                        currentTime.hour,
                        statusValue(dailyForecast.status)
                    ),
                    temperatureValue = Random.nextInt(
                        dailyForecast.temperature.minimum,
                        dailyForecast.temperature.maximum + 1 // + 1 is used to include it as well
                    ),
                    wind = Wind(
                        Random.nextInt(0, 360),
                        Random.nextInt(0, dailyForecast.wind.speed + 1)
                    ),
                    humidity = Random.nextInt(30, dailyForecast.humidity + 1),
                    uVIndex = Random.nextInt(1, dailyForecast.uVIndex + 1),
                    air = Air(Random.nextInt(0, dailyForecast.air.value + 1)),
                    probability = Random.nextInt(50, 100)
                )
            )
        }
        return hourlyForecast
    }

    fun getDailyForecasts(): List<DailyForecast> {
        val dailyForecasts = mutableListOf<DailyForecast>()
        var currentDate = LocalDate.now()

        repeat(7) { // 7 days
            val minTemperature = Random.nextInt(20, 30)
            val maxTemperature = Random.nextInt(30, 40)

            dailyForecasts.add(
                DailyForecast(
                    date = currentDate,
                    status = randomTropicsStatus(
                        10, // day
                        STORM
                    ),
                    temperature = Temperature(
                        maximum = maxTemperature,
                        minimum = minTemperature,
                        value = Random.nextInt(minTemperature, maxTemperature + 1)
                    ),
                    wind = Wind(
                        Random.nextInt(0, 360),
                        Random.nextInt(0, 101)
                    ),
                    humidity = Random.nextInt(30, 100),
                    uVIndex = Random.nextInt(1, 11),
                    air = Air(Random.nextInt(0, 250)),
                    sun = Sun(
                        sunrise = LocalTime.of(Random.nextInt(6, 8), Random.nextInt(0, 60)),
                        sunset = LocalTime.of(Random.nextInt(17, 19), Random.nextInt(0, 60)),
                    )
                )
            )
            currentDate = currentDate.plusDays(1)
        }

        return dailyForecasts
    }

    private fun randomTropicsStatus(hour: Int, statusValue: Int): Status {
        return when (Random.nextInt(CLEAR, statusValue + 1)) {
            CLEAR -> if (hour in 18 downTo 6) Status.CLEAR_DAY else Status.CLEAR_NIGHT
            CLOUDY -> if (hour in 18 downTo 6) Status.CLOUDY_DAY else Status.CLOUDY_NIGHT
            FOGGY -> if (hour in 18 downTo 6) Status.FOGGY_DAY else Status.FOGGY_NIGHT
            LIGHT_RAIN -> Status.LIGHT_RAIN
            HEAVY_RAIN -> Status.HEAVY_RAIN
            else -> Status.STORM
        }
    }

    private fun statusValue(status: Status): Int {
        return when (status) {
            Status.CLEAR_DAY, Status.CLEAR_NIGHT -> CLEAR
            Status.CLOUDY_DAY, Status.CLOUDY_NIGHT -> CLOUDY
            Status.FOGGY_DAY, Status.FOGGY_NIGHT -> FOGGY
            Status.LIGHT_RAIN -> LIGHT_RAIN
            Status.HEAVY_RAIN -> HEAVY_RAIN
            else -> STORM
        }
    }
}

private const val CLEAR = 0
private const val CLOUDY = 1
private const val FOGGY = 2
private const val LIGHT_RAIN = 3
private const val HEAVY_RAIN = 4
private const val STORM = 5
