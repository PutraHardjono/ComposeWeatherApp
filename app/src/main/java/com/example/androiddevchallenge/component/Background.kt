package com.example.androiddevchallenge.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.weather.Category
import com.example.androiddevchallenge.weather.Generator
import com.example.androiddevchallenge.weather.Weather

@Composable
fun Background(
    modifier: Modifier,
    category: Category
) {
    val width = LocalConfiguration.current.screenWidthDp.dp * LocalDensity.current.density
    val height = 400.dp * LocalDensity.current.density

    val generator by remember {
        mutableStateOf(
            Generator(
                frameWidth = width.value.toInt(),
                frameHeight = height.value.toInt(),
                category = category
            )
        )
    }
    generator.generateDroplet()

    Box {
        Weather(
            generator = generator,
            category = category,
            modifier = modifier.height(410.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, MaterialTheme.colors.background)
                    )
                )
        )
    }

    Log.d("Background", "Background Recomposes")
}

@Composable
fun FoggyBackground() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.fog),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, MaterialTheme.colors.background),
                        50.dp.value * 0.6F,
                        50.dp.value * 1F
                    )
                )
        )
    }


}