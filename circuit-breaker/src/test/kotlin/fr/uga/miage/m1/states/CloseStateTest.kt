package fr.uga.miage.m1.states

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.functions
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withName
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertNotEmpty
import com.lemonappdev.konsist.api.verify.assertTrue
import fr.uga.miage.m1.weather.ConnectionException
import fr.uga.miage.m1.weather.UnknownException
import fr.uga.miage.m1.weather.WeatherServiceImpl.Companion.setErrorStatus
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.reflect.InvocationTargetException
import kotlin.reflect.full.primaryConstructor
import kotlin.test.assertTrue

internal class CloseStateTest {
    lateinit var cbc: CircuitBreakerContext;

    @Test
    fun structure() {
        val closeStateClass = Konsist
            .scopeFromProject(moduleName = "circuit-breaker")
            .classes()
            .withPackage("fr.uga.miage.m1.states")
            .withName("CloseState")

        closeStateClass.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [CloseState] doit être déclarée dans le package [fr.uga.miage.m1.statess]"
        )

        closeStateClass
            .assertTrue(
                strict = true,
                additionalMessage = "La classe [CloseState] doit hériter de la classe [${State::class.qualifiedName}]"
            ) {
                it.hasParentClassOf(State::class)
            }

        val numberOfFailures = closeStateClass
            .properties()
            .withName("numberOfFailures")

        numberOfFailures.assertNotEmpty(
            strict = true,
            additionalMessage = "La classe [CloseState] doit avoir une propriété [numberOfFailures]"
        )

        numberOfFailures.assertTrue(
            strict = true,
            additionalMessage = "La propriété [numberOfFailures] doit être variable et avoir une valuer par défaut à 0"
        ) {
            it.isVar && it.hasValue("0")
        }

        val loadWeatherFunction = closeStateClass
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
        setErrorStatus(false)
    }

    @Test
    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        IllegalArgumentException::class,
        InvocationTargetException::class,
        NoSuchMethodException::class,
        SecurityException::class
    )
    fun test_close_to_open_on_failure_threshold() {
        assertNotNull(cbc.state, "Implement the getState method of the CircuitBreakerContext")
        assertTrue(
            cbc.state.javaClass.getName().endsWith("CloseState"),
            "Bad states, current states must be ..CloseState"
        )

        (0..9).forEach { _ ->
            cbc.state.loadWeather("France", "Grenoble")
            assertTrue(
                cbc.state.javaClass.getName().endsWith("CloseState"),
                "Bad states, current states must be .." + "CloseState"
            )
        }
        setErrorStatus(true)
        (0..<FAILURE_THRESHOLD).forEach { _ ->
            try {
                cbc.state.loadWeather("France", "Grenoble")
            } catch (e: ConnectionException) {
                assertTrue(
                    cbc.state.javaClass.getName().endsWith("CloseState"),
                    "Bad states, current states must be ..CloseState"
                )
            }
        }
        // 4th call triggers a transition to the Open State
        try {
            cbc.state.loadWeather("France", "Grenoble")
            Assertions.fail<Any?>()
        } catch (e: ConnectionException) {
        }
        assertTrue(
            cbc.state.javaClass.getName().endsWith("OpenState"),
            "Bad states, current states must be .." + "OpenState"
        )
    }

    @Test
    @Throws(
        UnknownException::class,
        ConnectionException::class,
        InstantiationException::class,
        IllegalAccessException::class,
        IllegalArgumentException::class,
        InvocationTargetException::class,
        NoSuchMethodException::class,
        SecurityException::class
    )
    fun test_come_back_to_normal_before_threshold() {
        assertNotNull(cbc.state, "Implement the getState method of the CircuitBreakerContext")
        assertTrue(
            cbc.state.javaClass.getName().endsWith("CloseState"),
            "Bad states, current states must be ..CloseState"
        )
        (0..9).forEach { _ ->
            cbc.state.loadWeather("France", "Grenoble")
            assertTrue(
                cbc.state.javaClass.getName().endsWith("CloseState"),
                "Bad states, current states must be ..CloseState"
            )
        }
        setErrorStatus(true)
        (0..<FAILURE_THRESHOLD).forEach { _ ->
            try {
                cbc.state.loadWeather("France", "Grenoble")
            } catch (e: ConnectionException) {
                assertTrue(
                    cbc.state.javaClass.getName().endsWith("CloseState"),
                    "Bad states, current states must be ..CloseState"
                )
            }
        }
        setErrorStatus(false)
        try {
            cbc.state.loadWeather("France", "Grenoble")
        } catch (e: ConnectionException) {
        }
        assertTrue(
            cbc.state.javaClass.getName().endsWith("CloseState"), "Bad states, current states must be ..CloseState"
        )

    }
}
