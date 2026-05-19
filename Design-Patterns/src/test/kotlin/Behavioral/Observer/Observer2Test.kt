package fr.uga.miage.m1.Behavioral.Observer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class ServicesTest {
    @Test
    fun `Test observer`() {
        val ecommerce = Ecommerce()
        val sub1 = Notification()
        val sub2 = Inventory()
        val sub3 = Invoice()

        ecommerce .attach(sub1)
        ecommerce .attach(sub2)
        ecommerce .attach(sub3)
        ecommerce .trigger("confirmed")

        assertEquals("confirmed", sub1.lastStatus)
        assertEquals("confirmed", sub2.lastStatus)
        assertEquals("confirmed", sub3.lastStatus)

        ecommerce .detach(sub3)
        ecommerce .trigger("shipped")

        assertEquals("shipped", sub1.lastStatus)
        assertEquals("shipped", sub2.lastStatus)
        assertEquals("confirmed", sub3.lastStatus)

        ecommerce.attach(sub3)
        ecommerce .trigger("delivered")

        assertEquals("delivered", sub1.lastStatus)
        assertEquals("delivered", sub2.lastStatus)
        assertEquals("delivered", sub3.lastStatus)
    }
}