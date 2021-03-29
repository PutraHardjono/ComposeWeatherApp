package com.example.androiddevchallenge.weather

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap

sealed class Category(
    open val movement: Float,
    open val minSpeed: Float,
    open val maxSpeed: Float,
    open val brush: Brush,
    open val amount: Int
) {
    data class Rain(
        override val movement: Float,
        override val minSpeed: Float,
        override val maxSpeed: Float,
        override val brush: Brush,
        override val amount: Int,
        val minWidth: Int,
        val maxWidth: Int,
        val minHeight: Int,
        val maxHeight: Int
    ) : Category(movement, minSpeed, maxSpeed, brush, amount)

    data class Snow(
        override val movement: Float,
        override val minSpeed: Float,
        override val maxSpeed: Float,
        override val brush: Brush,
        override val amount: Int,
        val minRadius: Int,
        val maxRadius: Int
    ) : Category(movement, minSpeed, maxSpeed, brush, amount)

    data class Cloudy(
        override val movement: Float,
        override val minSpeed: Float,
        override val maxSpeed: Float,
        override val brush: Brush,
        override val amount: Int,
        val image: ImageBitmap
    ) : Category(movement, minSpeed, maxSpeed, brush, amount)
}

val snowParticle = Category.Snow(
    movement = 30f,
    minSpeed = 0.07f,
    maxSpeed = 0.1f,
    minRadius = 3,
    maxRadius = 8,
    brush = Brush.linearGradient(listOf(Color.White, Color.White)),
    amount = 50
)

val rainParticle = Category.Rain(
    movement = 30f,
    minSpeed = 0.7f,
    maxSpeed = 1.0f,
    brush = Brush.linearGradient(
        listOf(
            Color.White,
            Color.White,
            Color.Transparent
        )
    ),
    amount = 20,
    minWidth = 1,
    maxWidth = 3,
    minHeight = 10,
    maxHeight = 15
)