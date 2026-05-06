package fr.uga.miage.m1.Behavioral.Iterator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class WardTest {
    @Test
    fun `Iterator test`() {
        val ward = Ward()
        ward.add(Patient("John", Status.STABLE))
        ward.add(Patient("Doe", Status.RECOVERING))
        ward.add(Patient("Martin", Status.CRITICAL))
        ward.add(Patient("Kevin", Status.RECOVERING))
        ward.add(Patient("Marta", Status.CRITICAL))
        ward.add(Patient("William", Status.STABLE))
        ward.add(Patient("Safwan", Status.STABLE))
        ward.add(Patient("Marques", Status.STABLE))

        val firstIterator = ward.createFirstIterator3()
        val secondIterator = ward.createSecondIterator3()

        val fullWard = mutableListOf<String>()
        while (firstIterator.hasMore()){
            fullWard.add(firstIterator.next().name)
        }
        assertEquals(fullWard.size, ward.size())

        val criticalWard = mutableListOf<String>()
        while (secondIterator.hasMore()){
            criticalWard.add(secondIterator.next().name)
        }
        assertEquals(criticalWard, listOf("Martin", "Marta"))

        assertThrows<NoSuchElementException> { firstIterator.next() }
        assertThrows<NoSuchElementException> { secondIterator.next() }
    }
}