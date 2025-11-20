package fr.uga.miage.m1.weather

interface WeatherServiceListener {
    fun serviceCalled(weatherService: WeatherService?, weather: Weather?)
}