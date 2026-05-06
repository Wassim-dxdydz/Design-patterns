package fr.uga.miage.m1.Behavioral.Iterator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ShelfIteratorTest {

    @Test
    fun `iterator traverses all books and throws when exhausted`() {
        val shelf = Shelf()
        shelf.addBook(Book("Clean Code", "Robert Martin", 2008))
        shelf.addBook(Book("Kotlin in Action", "Jemerov & Isakova", 2017))
        shelf.addBook(Book("The Pragmatic Programmer", "Hunt & Thomas", 1999))

        val iterator = shelf.createIterator()

        val titles = mutableListOf<String>()
        while (iterator.hasMore()) {
            titles.add(iterator.next().title)
        }

        assertEquals(listOf("Clean Code", "Kotlin in Action", "The Pragmatic Programmer"), titles)
        assertFalse(iterator.hasMore())
        assertThrows(NoSuchElementException::class.java) { iterator.next() }
    }
}