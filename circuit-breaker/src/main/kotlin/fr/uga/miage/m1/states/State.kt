package fr.uga.miage.m1.states

import fr.uga.miage.m1.weather.ConnectionException
import fr.uga.miage.m1.weather.UnknownException
import fr.uga.miage.m1.weather.Weather
import fr.uga.miage.m1.weather.WeatherService
import fr.uga.miage.m1.weather.WeatherServiceFactory


sealed class State(
    open var context: CircuitBreakerContext ?= null,
    var service: WeatherService = WeatherServiceFactory.service
) {
    @Throws(ConnectionException::class, UnknownException::class)
    open fun loadWeather(country: String, city: String): Weather? = service.loadWeather(country,city)
}
