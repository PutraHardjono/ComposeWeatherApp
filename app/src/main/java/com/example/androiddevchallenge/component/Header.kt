package com.example.androiddevchallenge.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.Status
import com.example.androiddevchallenge.model.Sun
import com.example.androiddevchallenge.model.Temperature
import com.example.androiddevchallenge.ui.theme.yellow500
import com.example.androiddevchallenge.utils.toTimeString
import com.example.androiddevchallenge.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    date: String,
    isToday: Boolean,
    temperature: Temperature,
    status: Status,
    sun: Sun
) {
    Column(modifier = modifier) {
        Text(
            text = date,
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier
                .padding(start = 16.dp)
        )
        if (isToday) {
            Text(
                text = "\u0020${temperature.value}\u00B0",
                style = MaterialTheme.typography.h2,
                modifier = modifier.paddingFromBaseline(16.dp)
            )
            Text(
                text = stringResource(
                    id = R.string.temperature_format,
                    temperature.minimum,
                    temperature.maximum
                ),
                modifier = modifier
                    .paddingFromBaseline(16.dp)
                    .padding(start = 16.dp)
            )
        } else
            Text(
                text = stringResource(
                    id = R.string.temperature_format,
                    temperature.minimum,
                    temperature.maximum
                ),
                style = MaterialTheme.typography.h2,
                modifier = Modifier.paddingFromBaseline(16.dp)
            )

        Text(
            text = stringResource(id = status.weatherResId),
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        SunInformation(
            time = sun.sunrise.toTimeString(),
            drawableResId = R.drawable.ic_sunrise,
            contentDescription = null
        )

        SunInformation(
            time = sun.sunset.toTimeString(),
            drawableResId = R.drawable.ic_sunset,
            contentDescription = null
        )
    }
}

@Composable
fun SunInformation(
    modifier: Modifier = Modifier,
    time: String,
    @DrawableRes drawableResId: Int,
    contentDescription: String?
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(start = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = drawableResId),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = yellow500
        )
        Text(
            text = time,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}