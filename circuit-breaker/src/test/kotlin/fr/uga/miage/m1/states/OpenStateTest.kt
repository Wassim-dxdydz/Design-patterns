package fr.uga.miage.m1.states

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.parameters
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import fr.uga.miage.m1.weather.WeatherServiceImpl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.reflect.full.primaryConstructor

class OpenStateTest {
    lateinit var cbc: CircuitBreakerContext;

    @Test
    fun structure() {
        val openStateClass = Konsist
            .scopeFromProject(moduleName = "circuit-breaker")
            .classes()
            .withPackage("fr.uga.miage.m1.states")
            .withName("OpenState")

        openStateClass.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [OpenState] doit être déclarée dans le package [fr.uga.miage.m1.states]"
        )

        openStateClass
            .assertTrue(
                strict = true,
                additionalMessage = "La classe [OpenState] doit hériter de la classe [${State::class.qualifiedName}]"
            ) {
                it.hasParentClassOf(State::class)
            }

        val numberOfCalls = openStateClass
            .properties()
            .withName("numberOfCalls")

        numberOfCalls.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [OpenState] doit avoir une propriété [numberOfCalls]"
        )

        numberOfCalls.assertTrue(
            strict = true,
            additionalMessage = "La propriété [numberOfCalls] doit être variable et avoir une valuer par défaut à 0"
        ) {
            it.isVar && it.hasValue("0")
        }

        val loadWeatherFunction = openStateClass
            .functions()
            .withName("loadWeather")

        loadWeatherFunction
            .assertNotEmpty(
                strict = true,
                additionalMessage = "La fonction [loadWeather()] doit être définie dans la classe [CloseState]"
            )

        loadWeatherFunction
            .assertTrue(
                strict = true,
                additionalMessage = "La fonction [loadWeather()] doit être override de la classe mère [State]"
            ) {
                it.hasOverrideModifier
            }
    }


    @BeforeEach
    fun initialize_state() {
        val closeStateClass = Konsist
            .scopeFromProject(moduleName = "circuit-breaker")
            .classes()
            .withPackage("fr.uga.miage.m1.states")
            .withName("CloseState")

        closeStateClass.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [CloseState] doit être déclarée dans le package [fr.uga.miage.m1.states]"
        )

        val state: State = Class.forName(closeStateClass.first().fullyQualifiedName)
            .kotlin
            .primaryConstructor
            ?.callBy(
                mapOf(
                    Class.forName(closeStateClass.first().fullyQualifiedName).kotlin.primaryConstructor!!.parameters.first { it.name == "context" } to null
                )) as State

        val circuitBreakerContext = CircuitBreakerContext(state)
        state.context = circuitBreakerContext
        cbc = circuitBreakerContext
    }

    @AfterEach
    fun reset_service() {
        WeatherServiceImpl.setErrorStatus(false)
    }

    @Test
    fun test_open_to_halfopen_on_open_threshold() {
        // To initialize the cache if needed
        getWeather(cbc,"France", "Grenoble")
        val openStateClass = Konsist
            .scopeFromProject(moduleName = "circuit-breaker")
            .classes()
            .withPackage("fr.uga.miage.m1.states")
            .withName("OpenState")

        openStateClass.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [OpenState] doit être déclarée dans le package [fr.uga.miage.m1.states]"
        )

        val state: State = Class.forName(openStateClass.first().fullyQualifiedName)
            .kotlin
            .primaryConstructor
            ?.callBy(
                mapOf(
                    Class.forName(openStateClass.first().fullyQualifiedName).kotlin.primaryConstructor!!.parameters.first { it.name == "context" } to null
                )) as State

        changeStateOnContext(cbc,state)
        state.context = cbc

        Assertions.assertTrue(
            cbc.state.javaClass.getName().endsWith("OpenState"),
            "Bad states, current states must be ..OpenState"
        )
        for (i in 0..<OPEN_THRESHOLD) {
            cbc.state.loadWeather("France", "Grenoble")
            Assertions.assertTrue(
                cbc.state.javaClass.getName().endsWith("OpenState"),
                "Bad states, current states must be ..OpenState"
            )
        }
        cbc.state.loadWeather("France", "Grenoble")
        Assertions.assertTrue(
            cbc.state.javaClass.getName().endsWith("HalfOpenState"),
            "Bad states, current states must be ..HalfOpenState"
        )
    }


    companion object{

        fun changeStateOnContext(cbc: CircuitBreakerContext, state :State){
            val circuitBreakerContextClass = Konsist
                .scopeFromProject(moduleName = "circuit-breaker")
                .classes()
                .withPackage("fr.uga.miage.m1.states")
                .withName("CircuitBreakerContext")

            circuitBreakerContextClass.assertNotEmpty(
                strict = true,
                additionalMessage = "La classe [CircuitBreakerContext] doit être déclarée dans le package [fr.uga.miage.m1.states]"
            )

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

            CircuitBreakerContext::class
                .members
                .first { it.name == "changeState" }
                .call(cbc,state)
        }

        fun getWeather(cbc: CircuitBreakerContext, country: String, city: String){
            val circuitBreakerContextClass = Konsist
                .scopeFromProject(moduleName = "circuit-breaker")
                .classes()
                .withPackage("fr.uga.miage.m1.states")
                .withName("CircuitBreakerContext")

            circuitBreakerContextClass.assertNotEmpty(
                strict = true,
                additionalMessage = "La classe [CircuitBreakerContext] doit être déclarée dans le package [fr.uga.miage.m1.states]"
            )

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

            val function = CircuitBreakerContext::class
                .members
                .first { it.name == "getWeather" }

            function
                .callBy(mapOf(
                    function.parameters.first() to cbc,
                    function.parameters.first { it.name=="country" } to country,
                    function.parameters.first { it.name=="city" } to city
                ))
        }
    }

}