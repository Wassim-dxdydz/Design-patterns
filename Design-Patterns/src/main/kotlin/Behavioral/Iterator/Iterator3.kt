package fr.uga.miage.m1.Behavioral.Iterator

enum class Status { STABLE, RECOVERING, CRITICAL }

interface Iterator3 <T>{
    fun next(): T
    fun hasMore(): Boolean
}

interface IterableCollection3 <T> {
    fun createFirstIterator3(): Iterator3<T>
    fun createSecondIterator3(): Iterator3<T>
}

data class Patient(val name: String, val status: Status)

class Ward : IterableCollection3<Patient> {
    private val patients = mutableListOf<Patient>()

    fun add(p : Patient) {
        patients.add(p)
    }

    internal fun size() : Int {
        return patients.size
    }

    internal fun get(id :Int) : Patient {
        return patients[id]
    }

    override fun createFirstIterator3(): Iterator3 <Patient> {
        return FirstIterator3(this)
    }

    override fun createSecondIterator3(): Iterator3 <Patient>{
        return SecondIterator3(this)
    }
}

class FirstIterator3(private val collection : Ward) : Iterator3<Patient> {
    private var index : Int = 0

    override fun next(): Patient {
        if (!hasMore()) throw NoSuchElementException()
        return collection.get(index++)
    }

    override fun hasMore(): Boolean {
        return index < collection.size()
    }
}

class SecondIterator3(private val collection : Ward) : Iterator3<Patient> {
    private var index : Int = 0

    override fun next(): Patient {
        if (!hasMore()) throw NoSuchElementException()

        if (collection.get(index).status == Status.CRITICAL) {
            return collection.get(index++)
        } else {
            index++
            return next()
        }
    }

    override fun hasMore(): Boolean {
        var i = index

        while (i < collection.size()) {
            if (collection.get(i).status == Status.CRITICAL) { return true }
            i++
        }
        return false
    }
}
