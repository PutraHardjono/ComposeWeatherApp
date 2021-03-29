package com.example.androiddevchallenge.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.androiddevchallenge.R

enum class Status (@StringRes val weatherResId: Int, @DrawableRes val iconResId: Int) {
    CLEAR_DAY(R.string.clear, R.drawable.ic_clear_day),
    CLEAR_NIGHT(R.string.clear, R.drawable.ic_clear_night),
    CLOUDY_DAY(R.string.cloudy, R.drawable.ic_cloudy_day),
    CLOUDY_NIGHT(R.string.cloudy, R.drawable.ic_cloudy_night),
    FOGGY_DAY(R.string.foggy, R.drawable.ic_fog_day),
    FOGGY_NIGHT(R.string.foggy, R.drawable.ic_fog_night),
    HEAVY_RAIN(R.string.heavy_rain, R.drawable.ic_heavy_rain),
    HEAVY_SNOW(R.string.heavy_snow, R.drawable.ic_heavy_snow),
    LIGHT_RAIN(R.string.light_rain, R.drawable.ic_light_rain),
    LIGHT_SNOW(R.string.light_snow, R.drawable.ic_light_snow),
    SNOW_STORM(R.string.snow_storm, R.drawable.ic_snow_storm),
    STORM(R.string.storm, R.drawable.ic_storm);

    fun isRainOrSnow(): Boolean {
        return when (this) {
            HEAVY_RAIN, LIGHT_RAIN, HEAVY_SNOW, LIGHT_SNOW, SNOW_STORM, STORM -> true
            else -> false
        }
    }
}