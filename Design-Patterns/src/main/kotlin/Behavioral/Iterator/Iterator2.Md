# 🗂️ Exam: TaskBoard — Filtered Browsing

## Context

A project management tool organizes tasks on a board. Each board contains
a list of tasks, and a task manager needs to be able to **browse through
tasks one by one** without knowing how they are stored internally.

However, the team also needs to browse **only the high-priority tasks**
without changing the collection itself.

Your task is to **implement this using the Iterator Pattern**, supporting
two different traversal strategies over the same collection.

---

### Existing Code:

```kotlin
enum class Priority { LOW, MEDIUM, HIGH }

data class Task(val name: String, val priority: Priority)

class TaskBoard {
    val tasks = mutableListOf<Task>()

    fun add(task: Task) {
        tasks.add(task)
    }
}

fun main() {
    val board = TaskBoard()
    board.add(Task("Fix login bug", Priority.HIGH))
    board.add(Task("Update README", Priority.LOW))
    board.add(Task("Write tests", Priority.HIGH))
    board.add(Task("Refactor DB layer", Priority.MEDIUM))

    for (task in board.tasks) {
        println(task.name)
    }
}
```

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*

Design a PlantUML class diagram that models the Iterator Pattern applied
to this scenario, including both iterators.

### B — Kotlin Implementation *(2.5 pts)*

Refactor the code above using the Iterator Pattern. The board must support
**two iterators**:
- One that browses **all tasks**
- One that browses **only HIGH priority tasks**

### C — Test Scenario *(1 pt)*

Write a single JUnit test that:
1. Adds a mix of LOW, MEDIUM, and HIGH priority tasks to the board
2. Verifies that the full iterator returns all tasks in order
3. Verifies that the filtered iterator returns **only** HIGH priority tasks
4. Verifies that the filtered iterator throws when exhausted