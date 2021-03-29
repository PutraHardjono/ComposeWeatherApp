package com.example.androiddevchallenge.model

import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

data class Air(
    val value: Int
) {
    @StringRes
    val categoryIdRes: Int = when (value) {
        in 0..50 -> R.string.good
        in 51..100 -> R.string.moderate
        in 101..150 -> R.string.unhealthy_for_sensitive
        in 151..200 -> R.string.unhealthy
        in 201..300 -> R.string.very_unhealthy
        else -> R.string.hazardous
    }
}