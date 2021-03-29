package com.example.androiddevchallenge.model

data class Wind (
    var degree: Int, // Wind direction in azimuth degrees (e.g. 180° indicates wind coming from the south).
    var speed: Int // km/h
)