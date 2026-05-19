package fr.uga.miage.m1.Behavioral.Method

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class HotBeverageTest {

    @Test
    fun `Method test` (){
        val tea = Tea()
        val coffee = Coffee()
        val hotChocolate = HotChocolate()

        val output1 = "🫖 Boiling water...\n🍵 Steeping the tea bag for 3 minutes...\n🥤 Pouring into cup...\n🍋 Adding a slice of lemon...\n"
        assertEquals(output1, tea.prepare())
        val output2 = "🫖 Boiling water...\n☕ Brewing coffee grinds...\n🥤 Pouring into cup...\n🥛 Adding sugar and milk...\n"
        assertEquals(output2, coffee.prepare())

        assertNotEquals(tea.prepare(), coffee.prepare())
        val output3 = "🫖 Boiling water...\n🍫 melting chocolate powder into hot water...\n🥤 Pouring into cup...\n🍡 Adding marshmallows...\n"
        assertEquals(output3, hotChocolate.prepare())
    }
}