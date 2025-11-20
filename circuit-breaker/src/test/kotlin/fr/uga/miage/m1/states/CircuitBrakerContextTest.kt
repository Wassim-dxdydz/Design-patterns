package fr.uga.miage.m1.states

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import fr.uga.miage.m1.weather.ConnectionException
import fr.uga.miage.m1.weather.Weather
import org.junit.jupiter.api.Test

class CircuitBrakerContextTest {

    @Test
    fun structure() {
        val circuitBreakerContextClass = Konsist
            .scopeFromProject(moduleName = "circuit-breaker")
            .classes()
            .withPackage("fr.uga.miage.m1.states")
            .withName("CircuitBreakerContext")

        circuitBreakerContextClass.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [CircuitBreakerContext] doit être déclarée dans le package [fr.uga.miage.m1.states]"
        )

        circuitBreakerContextClass.assertTrue(
            strict = true,
            additionalMessage = "La classe [CircuitBreakerContext] doit avoir un bloc init [init {}] afin de mettre le premier state en place à [ClosedContext]"
        ) {
            it.hasInitBlocks()
        }

        val changeStateFunction = circuitBreakerContextClass
            .functions()
            .withName("changeState")

        changeStateFunction
            .assertNotEmpty(
                strict = true,
                additionalMessage = "La classe [CircuitBreakerContext] doit avoir une fonction [changeState]"
            )

        changeStateFunction
            .parameters
            .withName("state")
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [changeState] doit avoir un paramètre [state] de type [${State::class}]"
            ) {
                it.hasType { it.name == State::class.simpleName  }
            }

        val getWeatherFunction = circuitBreakerContextClass
            .functions()
            .withName("getWeather")

        getWeatherFunction
            .assertNotEmpty(
                strict = true,
                additionalMessage = "La classe [CircuitBreakerContext] doit avoir une fonction [getWeather]"
            )


        getWeatherFunction
            .parameters
            .withName("country")
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeather] doit avoir un paramètre [country] de type [${String::class}]"
            ) {
                it.hasTypeOf(String::class)
            }

        getWeatherFunction
            .parameters
            .withName("city")
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeather] doit avoir un paramètre [city] de type [${String::class}]"
            ) {
                it.hasTypeOf(String::class)
            }

        getWeatherFunction
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeather] doit renvoyer le type [Weather?]"
            ) { it ->
                it.hasReturnTypeOf(Weather::class) && it.hasReturnType { it.isNullable }
            }


        val getWeatherFromCacheFunction = circuitBreakerContextClass
            .functions()
            .withName("getWeatherFromCache")


        getWeatherFromCacheFunction
            .assertTrue {
                it.hasParametersWithAllNames("country", "city")
            }


        getWeatherFromCacheFunction
            .assertNotEmpty(
                strict = true,
                additionalMessage = "La classe [CircuitBreakerContext] doit avoir une fonction [getWeatherFromCache]"
            )


        getWeatherFromCacheFunction
            .parameters
            .withName("country")
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeatherFromCache] doit avoir un paramètre [country] de type [${String::class}]"
            ) {
                it.hasTypeOf(String::class)
            }

        getWeatherFromCacheFunction
            .parameters
            .withName("city")
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeatherFromCache] doit avoir un paramètre [city] de type [${String::class}]"
            ) {
                it.hasTypeOf(String::class)
            }

        getWeatherFromCacheFunction
            .parameters
            .withName("ce")
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeatherFromCache] doit avoir un paramètre [ce] de type [${ConnectionException::class}] (il correspond à l'exception de connection remontée et qu'il faut renvoyer si vous n'avez rien en cache)"
            ) {
                it.hasTypeOf(ConnectionException::class)
            }

        getWeatherFromCacheFunction
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [getWeather] doit renvoyer le type [Weather?]"
            ) { it ->
                it.hasReturnTypeOf(Weather::class) && it.hasReturnType { it.isNullable }
            }


    }
}