package fr.uga.miage.m1.Behavioral.Observer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class HubSubscriber {
    @Test
    fun `Test observer`() {
        val hub = TheHub()
        val sub1 = Alarm()
        val sub2 = Lights()
        val sub3 = Thermostat()

        hub.attach(sub1)
        hub.attach(sub2)
        hub.attach(sub3)
        hub.trigger("motion_detected")

        assertEquals("motion_detected", sub1.lastEvent)
        assertEquals("motion_detected", sub2.lastEvent)
        assertEquals("motion_detected", sub3.lastEvent)

        hub.detach(sub3)
        hub.trigger("door_opened")

        assertEquals("door_opened", sub1.lastEvent)
        assertEquals("door_opened", sub2.lastEvent)
        assertEquals("motion_detected", sub3.lastEvent)
    }
}