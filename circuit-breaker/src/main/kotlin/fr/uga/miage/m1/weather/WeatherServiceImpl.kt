package fr.uga.miage.m1.weather

import java.io.FileNotFoundException
import java.io.IOException
import java.text.DecimalFormat
import java.time.Instant
import java.util.*

class WeatherServiceImpl : WeatherService(cities) {

    override fun loadWeather(country: String, city: String): Weather {
        checkCities(country, city)
        if (error) (this notify null).also { throw ConnectionException("Une erreur de connexion est apparu") }
        else return Weather(
            Random().nextFloat().toDouble() * 20.0 + 273.15,
            Instant.now()
        ).also { this notify it }
    }

    private infix fun notify(result: Weather?) = listeners.forEach { it.serviceCalled(this, result) }


    @Throws(UnknownException::class)
    private fun checkCities(country: String, city: String) = (this citiesOf country).contains(city)


    companion object {
        private var cities: MutableMap<String, MutableCollection<String>> = mutableMapOf()
        private val listeners: MutableCollection<WeatherServiceListener> = ArrayList<WeatherServiceListener>()
        private var error = false

        fun setErrorStatus(status: Boolean) {
            error = status
        }

        fun addWeatherServiceListener(aWeatherServiceListener: WeatherServiceListener?) {
            listeners.add(aWeatherServiceListener!!)
        }

        fun removeWeatherServiceListener(aWeatherServiceListener: WeatherServiceListener?) {
            listeners.remove(aWeatherServiceListener)
        }


        init {
            try {
                cities = loadCities()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Throwable) {
                throw RuntimeException(e)
            }
        }


        @Throws(Throwable::class)
        private fun loadCities(): MutableMap<String, MutableCollection<String>> {
            val records: MutableMap<String, MutableCollection<String>> = mutableMapOf()

            this::class.java.getResource("classpath:../../../../../../../small_cities.csv")
                .readText()
                .split('\n')
                .drop(1)
                .forEach { it ->
                    it
                        .split(";".toRegex())
                        .dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                        .let { values ->
                            var cities: Collection<String>? = records[values[1]]?.also {
                                it.add(values[0])
                            }
                            if (cities == null) {
                                cities = mutableListOf()
                                records[values[1].trim()] = cities
                            }
                        }
                }
            return records
        }
    }
}