package com.example.androiddevchallenge.weather

import kotlin.random.Random

class Generator(
    private val frameWidth: Int,
    private val frameHeight: Int,
    private val category: Category
) {
    private val _particles = mutableListOf<Particle>()
    val particles: List<Particle> get() = _particles

    private var _animate = 0f

    fun generateDroplet() {
        while (_particles.size < category.amount) {
            _particles.add(createParticle())
        }
    }

    fun move() {
        _particles.forEach { particle ->
            if (particle.y > frameHeight || particle.x > frameWidth || particle.x < 0) {
                reCreateParticle(particle = particle)
            } else {
                when (category) {
                    is Category.Cloudy -> {
                        particle.x = particle.x - (category.movement * particle.speed)
                    }
                    else -> particle.y = particle.y + (category.movement * particle.speed)
                }
            }
        }
    }

    fun trigger(animate: Float) {
        _animate = animate
    }

    private fun createParticle(): Particle {
        return when (category) {
            is Category.Snow -> {
                Particle(
                    x = Random.nextInt(frameWidth).toFloat(),
                    y = Random.nextInt(frameHeight).toFloat(),
                    width = Random.nextInt(category.minRadius, category.maxRadius).toFloat(),
                    height = Random.nextInt(category.minRadius, category.maxRadius).toFloat(),
                    speed = Random.nextFloat() * (category.maxSpeed - category.minSpeed) + category.minSpeed,
                    angle = 0f
                )
            }
            is Category.Rain -> {
                Particle(
                    x = Random.nextInt(frameWidth).toFloat(),
                    y = Random.nextInt(frameHeight).toFloat(),
                    width = Random.nextInt(category.minWidth, category.maxWidth).toFloat(),
                    height = Random.nextInt(category.minHeight, category.maxHeight).toFloat(),
                    speed = Random.nextFloat() * (category.maxSpeed - category.minSpeed) + category.minSpeed,
                    angle = 0f
                )
            }
            is Category.Cloudy -> {
                Particle(
                    x = frameWidth.toFloat(),
                    y = Random.nextInt(frameHeight).toFloat(),
                    width = 0f,
                    height = 0f,
                    speed = Random.nextFloat() * (category.maxSpeed - category.minSpeed) + category.minSpeed,
                    angle = 0f
                )
            }
        }
    }

    private fun reCreateParticle(particle: Particle) {
        particle.apply {
            speed =
                Random.nextFloat() * (category.maxSpeed - category.minSpeed) + category.minSpeed
            angle = 0f

            when (category) {
                is Category.Snow -> {
                    x = Random.nextInt(frameWidth).toFloat()
                    y = 0f
                    width = Random.nextInt(category.minRadius, category.maxRadius).toFloat()
                    height = width
                }
                is Category.Rain -> {
                    x = Random.nextInt(frameWidth).toFloat()
                    y = 0f
                    width = Random.nextInt(category.minWidth, category.maxWidth).toFloat()
                    height = Random.nextInt(category.minHeight, category.maxHeight).toFloat()
                }
                is Category.Cloudy -> {
                    x = frameWidth.toFloat()
                    y = Random.nextInt(frameHeight).toFloat()
                }
            }
        }
    }
}