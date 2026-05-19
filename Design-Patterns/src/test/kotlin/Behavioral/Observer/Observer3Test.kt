package fr.uga.miage.m1.Behavioral.Observer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*


class WatchersTest {
    @Test
    fun `Test observer`() {
        val tracker = StockTracker()
        val sub1 = MobileAlert()
        val sub2 = TradingBot()
        val sub3 = PortfolioDashboard()

        assertDoesNotThrow { tracker.trigger(150.0) }

        tracker.attach(sub1)
        tracker.attach(sub2)
        tracker.attach(sub3)
        tracker.trigger(178.5)

        assertEquals(178.5, sub1.lastPrice)
        assertEquals(178.5, sub2.lastPrice)
        assertEquals(178.5, sub3.lastPrice)

        tracker.detach(sub3)
        tracker.trigger(140.0)

        assertEquals(140.0, sub1.lastPrice)
        assertEquals(140.0, sub2.lastPrice)
        assertEquals(178.5, sub3.lastPrice)

        tracker.attach(sub3)
        tracker.trigger(200.0)

        assertEquals(200.0, sub1.lastPrice)
        assertEquals(200.0, sub2.lastPrice)
        assertEquals(200.0, sub3.lastPrice)
    }
}