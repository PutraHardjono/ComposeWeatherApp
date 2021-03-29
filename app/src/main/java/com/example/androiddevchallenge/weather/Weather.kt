package com.example.androiddevchallenge.weather

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex

@Composable
fun Weather(
    modifier: Modifier = Modifier,
    generator: Generator,
    category: Category
) {
    val transition = rememberInfiniteTransition()
    val value by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1000f,
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(10000)
        )
    )

    generator.trigger(value) // to trigger recomposition
    generator.move()

    Canvas(modifier = modifier.zIndex(-1000f)) {
        generator.particles.forEach { particle ->
            when (category) {
                is Category.Snow -> {
                    drawCircle(
                        brush = category.brush,
                        radius = particle.width,
                        center = Offset(
                            x = particle.x,
                            y = particle.y
                        )
                    )
                }

                is Category.Rain -> {
                    drawLine(
                        brush = category.brush,
                        pathEffect = PathEffect.cornerPathEffect(20f),
                        start = Offset(particle.x, particle.y),
                        end = Offset(particle.x, particle.y - particle.height),
                        strokeWidth = particle.width
                    )
                }

                is Category.Cloudy -> {
                    drawImage(
                        image = category.image,
                        dstOffset = IntOffset(particle.x.toInt(), particle.y.toInt())
                    )
                }
//                else -> {}
            }
        }
    }
}