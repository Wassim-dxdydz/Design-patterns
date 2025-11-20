package fr.uga.miage.m1.weather

import java.time.Instant

data class Weather(
    var temperature: Double = 0.0,
    var timestamp: Instant? = null
)