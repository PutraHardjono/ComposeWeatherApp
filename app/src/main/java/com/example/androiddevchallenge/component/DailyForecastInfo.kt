package com.example.androiddevchallenge.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.DailyForecast
import com.example.androiddevchallenge.model.HourlyForecast
import com.example.androiddevchallenge.utils.toDateString
import java.time.format.DateTimeFormatter

@Composable
fun HourlyForecastInfo(
    hourlyForecasts: List<HourlyForecast>,
    isToday: Boolean
) {
    if (isToday) {
        LazyRow(
            Modifier
                .padding(top = 64.dp, start = 8.dp, end = 8.dp)
        ) {
            items(hourlyForecasts) { hourlyForecast ->
                HourlyForecastItem(item = hourlyForecast)
            }
        }
    }
}

@Composable
fun HourlyForecastItem(item: HourlyForecast) {
    Column(
        Modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = item.time.format(DateTimeFormatter.ofPattern("HH:mm")),
            style = MaterialTheme.typography.caption
        )

        Box(modifier = Modifier.height(24.dp)) {
            if (item.status.isRainOrSnow()) {
                Text(
                    text = "${item.probability}%",
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.paddingFromBaseline(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Image(
            painter = painterResource(id = item.status.iconResId),
            contentDescription = stringResource(
                id = item.status.weatherResId
            ),
            modifier = Modifier.size(48.dp, 48.dp)
        )
        Text(
            text = " ${item.temperatureValue}\u00B0",
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun DailyForecastInfo(
    dailyForecasts: List<DailyForecast>,
    onSelect: (DailyForecast) -> Unit,
    currentDailyForecast: DailyForecast,
    modifier: Modifier = Modifier
) {
    LazyRow(modifier.padding(start = 8.dp, end = 8.dp)) {
        items(dailyForecasts) { daily ->
            DailyForecastItem(
                item = daily,
                isSelected = daily == currentDailyForecast,
                modifier = Modifier.clickable {
                    Log.d("DailyForecastInfo","DailyForecastInfo - onSelect: $daily")
                    onSelect(daily)
                }
            )
        }
    }
}

@Composable
fun DailyForecastItem(
    item: DailyForecast,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = item.date.toDateString())
        Surface(
            shape = CircleShape,
            border = BorderStroke(2.dp, MaterialTheme.colors.onSurface),
            color = if (isSelected) MaterialTheme.colors.onSurface else Color.Transparent,
            modifier = modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = item.status.iconResId),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .size(48.dp)
                    .padding(8.dp)
            )
        }
        Text(
            text = stringResource(
                id = R.string.temperature_format,
                item.temperature.minimum,
                item.temperature.maximum
            ),
            modifier = modifier
                .paddingFromBaseline(16.dp)
        )
    }
}