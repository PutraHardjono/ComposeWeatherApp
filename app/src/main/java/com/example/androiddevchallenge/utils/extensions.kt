package com.example.androiddevchallenge.utils

import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.ui.theme.*
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun Int.uvQualityColor(): Color {
    return when (this) {
        in 1..2 -> green
        in 3..5 -> yellow
        in 6..7 -> orange
        in 8..10 -> red
        else -> purple
    }
}

fun Int.airQualityColor(): Color {
    return when (this) {
        in 0..50 -> green
        in 51..100 -> yellow
        in 101..150 -> orange
        in 151..200 -> red
        in 201..300 -> purple
        else -> redBrown
    }
}

fun LocalTime.toTimeString(): String = this.format(DateTimeFormatter.ofPattern("HH:mm"))

fun LocalDate.toDateString(): String =
        this.format(DateTimeFormatter.ofPattern("eee, dd LLL"))
