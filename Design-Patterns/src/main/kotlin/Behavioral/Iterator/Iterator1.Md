# 📚 Exam: LibraryShelf — Browse Without Exposing

## Context

A local library management system stores books on shelves. Each shelf contains a collection of books. The librarian needs to be able to **browse through the books on a shelf one by one**, without the browsing logic having any knowledge of how the books are stored internally.

Currently, the system exposes the internal list directly, leading to tight coupling and fragile code.

Your task is to **refactor this using the Iterator Pattern**.

---

### Existing Code:

```kotlin
data class Book(val title: String, val author: String, val year: Int)

class Shelf {
    val books = mutableListOf<Book>()

    fun add(book: Book) {
        books.add(book)
    }
}

fun main() {
    val shelf = Shelf()
    shelf.add(Book("Clean Code", "Robert Martin", 2008))
    shelf.add(Book("The Pragmatic Programmer", "Hunt & Thomas", 1999))
    shelf.add(Book("Kotlin in Action", "Jemerov & Isakova", 2017))

    for (book in shelf.books) {
        println("${book.title} by ${book.author}")
    }
}
```

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*

Design a PlantUML class diagram that models the Iterator Pattern applied to this scenario.

### B — Kotlin Implementation *(2.5 pts)*

Refactor the code above using the Iterator Pattern. The internal storage of the `Shelf` must **not** be directly accessible from outside the class.

### C — Test Scenario *(1 pt)*

Write a JUnit test that:
1. Creates a `Shelf` and adds at least 3 books
2. Uses the iterator to traverse all books and verifies the count
3. Verifies that calling `next()` beyond the last element throws an exception
4. Creates a **second iterator** on the same shelf to demonstrate that each iterator has its own independent state

---

## 📝 Technical Constraints
- The internal collection must be **fully encapsulated** — no public fields or getters exposing it
- The iterator must maintain its own traversal state independently from the collection
- Do **not** use Kotlin's built-in `Iterable` or `Iterator` interfaces — define your own