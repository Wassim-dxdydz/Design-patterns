package fr.uga.miage.m1.Behavioral.Iterator

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TaskBoardTest {
    @Test
    fun `Iterator test`() {
        val task = TaskBoard()
        task.addTask(Task("Task 1", Priority.LOW))
        task.addTask(Task("Task 2", Priority.HIGH))
        task.addTask(Task("Task 3", Priority.MEDIUM))
        task.addTask(Task("Task 4", Priority.HIGH))

        val iterator : Iterator2<Task> = FirstIterator(task)
        val fullTasks = mutableListOf<String>()
        while (iterator.hasMore()){
            fullTasks.add(iterator.next().name)
        }
        assertEquals(fullTasks.size, task.size())


        val iterator2 : Iterator2<Task> = SecondIterator(task)
        val highPriorityTasks = mutableListOf<String>()
        while (iterator2.hasMore()) {
            highPriorityTasks.add(iterator2.next().name)
        }
        assertEquals(listOf("Task 2", "Task 4"), highPriorityTasks)




    }
}