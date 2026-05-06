package fr.uga.miage.m1.Behavioral.Iterator

enum class Priority { LOW, MEDIUM, HIGH }

interface Iterator2 <T>{
    fun next(): T
    fun hasMore() : Boolean
}

interface IterableCollection2 <T> {
    fun createFirstIterator(): Iterator2<T>
    fun createSecondIterator(): Iterator2<T>
}

data class Task(val name: String, val priority: Priority)

class TaskBoard : IterableCollection2<Task>{
    private val tasks = mutableListOf<Task>()

    fun addTask(task: Task) {
        tasks.add(task)
    }

    internal fun size() : Int {
        return tasks.size
    }

    internal fun get(index : Int) : Task {
        return tasks[index]
    }

    override fun createFirstIterator(): Iterator2<Task> {
        return FirstIterator(this)
    }

    override fun createSecondIterator(): Iterator2<Task> {
        return SecondIterator(this)
    }
}

class FirstIterator(private val task : TaskBoard) : Iterator2<Task>{
    private var index : Int = 0

    override fun next(): Task {
        if (!hasMore()) throw NoSuchElementException()
        return task.get(index++)
    }

    override fun hasMore(): Boolean {
        return index < task.size()
    }
}

class SecondIterator(private val task : TaskBoard) : Iterator2<Task> {
    private var index: Int = 0

    override fun next(): Task {
        if (!hasMore()) throw NoSuchElementException()
        if (task.get(index).priority == Priority.HIGH) {
            return task.get(index++)
        } else {
            index++
            return next()
        }
    }

    override fun hasMore(): Boolean {
        var i = index
        while (i < task.size()) {
            if (task.get(i).priority == Priority.HIGH) return true
            i++
        }
        return false
    }
}