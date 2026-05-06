package fr.uga.miage.m1.Behavioral.Iterator

interface Iterator1 <T> {
    fun next() : T
    fun hasMore(): Boolean
}

interface IterableCollection1 <T> {
    fun createIterator() : Iterator1<T>
}

data class Book(val title: String, val author: String, val year: Int)

class Shelf() : IterableCollection1<Book> {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
    }

    override fun createIterator(): Iterator1<Book> {
        return BookIterator(this)
    }

    internal fun size(): Int {
        return books.size
    }
    internal fun get(index: Int): Book {
        return books[index]
    }
}

class BookIterator(private val shelf : Shelf) : Iterator1<Book> {
    private var index = 0

    override fun next(): Book {
        if (!hasMore()) throw NoSuchElementException()
        return shelf.get(index++)
    }

    override fun hasMore(): Boolean {
        return index < shelf.size()
    }
}

