package com.example.androiddevchallenge.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyForecast
import com.example.androiddevchallenge.model.Info
import com.example.androiddevchallenge.utils.airQualityColor
import com.example.androiddevchallenge.utils.uvQualityColor

// The display for UV Index, Air Quality, Humidity and Wind
@Composable
fun ForecastInformation(
    modifier: Modifier = Modifier,
    currentForecast: DailyForecast
) {
    Column(modifier = modifier.fillMaxWidth()) {
        ForecastInformationItem(
            info = Info.UV_INDEX,
            stringValue = currentForecast.uVIndex.toString(),
            tintColor = currentForecast.uVIndex.uvQualityColor()
        )
        ForecastInformationItem(
            info = Info.AIR,
            stringValue = stringResource(id = currentForecast.air.categoryIdRes),
            tintColor = currentForecast.air.value.airQualityColor()
        )
        ForecastInformationItem(
            info = Info.HUMIDITY,
            stringValue = stringResource(
                id = R.string.humidity_format,
                currentForecast.humidity
            )
        )
        ForecastInformationItem(
            info = Info.WIND,
            stringValue = stringResource(
                id = R.string.wind_speed_format,
                currentForecast.wind.speed
            ),
            rotateDegree = currentForecast.wind.degree.toFloat()
        )
    }
}

@Composable
fun ForecastInformationItem(
    modifier: Modifier = Modifier,
    info: Info,
    stringValue: String,
    rotateDegree: Float = 0f,
    tintColor: Color = Color.White
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(bottom = 16.dp)
    ) {
        Surface(
            shape = CircleShape,
            border = BorderStroke(2.dp, Color.White),
        ) {
            Icon(
                info.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
                    .rotate(rotateDegree),
                tint = tintColor
            )
        }

        Column(modifier = modifier.padding(start = 8.dp)) {
            Text(
                text = stringValue,
                style = MaterialTheme.typography.subtitle1,
                color = tintColor
            )

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = stringResource(id = info.captionIdRes),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}