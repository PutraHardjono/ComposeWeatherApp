package com.example.androiddevchallenge.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.Opacity
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.androiddevchallenge.R

enum class Info (@StringRes val captionIdRes: Int, val icon: ImageVector) {
    WIND (captionIdRes = R.string.wind, icon = Icons.Outlined.Navigation),
    HUMIDITY (captionIdRes = R.string.humidity, icon = Icons.Outlined.Opacity),
    UV_INDEX(captionIdRes = R.string.uv_index, icon = Icons.Outlined.WbSunny),
    AIR(captionIdRes = R.string.air_quality, icon = Icons.Outlined.Air)
}