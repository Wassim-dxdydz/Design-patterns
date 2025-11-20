package fr.uga.miage.m1.states

import fr.uga.miage.m1.weather.ConnectionException
import fr.uga.miage.m1.weather.Weather


/**
 * The Failure Threshold.
 */
const val FAILURE_THRESHOLD: Int = 3

/**
 * The Open Threshold.
 */
const val OPEN_THRESHOLD: Int = 5


class CircuitBreakerContext(
    var state: State,
    val cache: MutableMap<String, MutableMap<String, Weather?>> = mutableMapOf()
)
