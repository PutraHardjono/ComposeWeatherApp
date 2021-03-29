package com.example.androiddevchallenge

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.component.*
import com.example.androiddevchallenge.model.Status
import com.example.androiddevchallenge.utils.toDateString
import com.example.androiddevchallenge.weather.Category
import com.example.androiddevchallenge.weather.rainParticle
import com.example.androiddevchallenge.weather.snowParticle
import java.time.LocalDate

@Composable
fun WeatherApp(viewModel: MainViewModel = viewModel()) {
    val date = if (viewModel.currentForecast.date.dayOfYear == LocalDate.now().dayOfYear)
        stringResource(id = R.string.today)
    else
        viewModel.currentForecast.date.toDateString()

    Scaffold {
        when (viewModel.currentForecast.status) {
            Status.CLEAR_DAY, Status.CLEAR_NIGHT -> {
            }
            Status.LIGHT_RAIN, Status.HEAVY_RAIN, Status.STORM -> Background(
                category = rainParticle,
                modifier = Modifier
            )
            Status.LIGHT_SNOW, Status.HEAVY_SNOW -> Background(
                category = snowParticle,
                modifier = Modifier
            )
            Status.SNOW_STORM -> Background(
                category = Category.Snow(
                    movement = 30f,
                    minSpeed = 3.70f,
                    maxSpeed = 4.0f,
                    minRadius = 3,
                    maxRadius = 8,
                    brush = Brush.linearGradient(listOf(Color.White, Color.White)),
                    amount = 50
                ),
                modifier = Modifier
            )
            Status.CLOUDY_DAY, Status.CLOUDY_NIGHT -> Background(
                category = Category.Cloudy(
                    movement = 30f,
                    minSpeed = 0.005f,
                    maxSpeed = 0.02f,
                    brush = Brush.linearGradient(listOf()),
                    amount = 5,
                    image = ImageBitmap.imageResource(R.drawable.cloud2)
                ), modifier = Modifier
            )
            Status.FOGGY_DAY, Status.FOGGY_NIGHT -> FoggyBackground()
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Location(
                location = viewModel.location,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Header(
                    date = date,
                    status = viewModel.currentForecast.status,
                    temperature = viewModel.currentForecast.temperature,
                    sun = viewModel.currentForecast.sun,
                    isToday = true
                )
                Spacer(modifier = Modifier.width(64.dp))
                ForecastInformation(currentForecast = viewModel.currentForecast)
            }

            Spacer(modifier = Modifier.weight(1f))

            HourlyForecastInfo(hourlyForecasts = viewModel.hourlyForecasts, isToday = true)

            Spacer(modifier = Modifier.height(32.dp))

            DailyForecastInfo(
                dailyForecasts = viewModel.dailyForecasts,
                onSelect = viewModel::setCurrentDailyForecast,
                currentDailyForecast = viewModel.currentForecast
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}