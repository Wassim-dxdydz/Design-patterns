package fr.uga.miage.m1.weather

abstract class WeatherService(
    val citiesByCountry: MutableMap<String, MutableCollection<String>> = mutableMapOf()
) {
    abstract fun loadWeather(country: String, city : String) : Weather?;

    infix fun citiesOf(country: String): MutableCollection<String> {
        return citiesByCountry[country] ?: throw UnknownException("Country <$country> unknown")
    }
}

object WeatherServiceFactory {
    val service: WeatherService
        get() = WeatherServiceImpl()
}
