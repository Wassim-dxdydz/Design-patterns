# 🏥 Exam: HospitalWard — Patient Round

## Context

A hospital management system organizes patients by ward. Each ward contains
a list of patients. During the doctor's daily round, the doctor needs to
**visit patients one by one** without knowing how they are stored internally.

However, the head nurse also needs a way to browse **only the critical
patients** (those requiring immediate attention) to prioritize care.

Your task is to **implement this using the Iterator Pattern**, supporting
two different traversal strategies over the same collection.

---

### Existing Code:

```kotlin
enum class Status { STABLE, RECOVERING, CRITICAL }

data class Patient(val name: String, val status: Status)

class Ward {
    val patients = mutableListOf<Patient>()

    fun admit(patient: Patient) {
        patients.add(patient)
    }
}

fun main() {
    val ward = Ward()
    ward.admit(Patient("Alice", Status.STABLE))
    ward.admit(Patient("Bob", Status.CRITICAL))
    ward.admit(Patient("Charlie", Status.RECOVERING))
    ward.admit(Patient("Diana", Status.CRITICAL))

    for (patient in ward.patients) {
        println("${patient.name} — ${patient.status}")
    }
}
```

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*

Design a PlantUML class diagram that models the Iterator Pattern applied
to this scenario, including both iterators.

### B — Kotlin Implementation *(2.5 pts)*

Refactor the code above using the Iterator Pattern. The ward must support
**two iterators**:
- One that browses **all patients**
- One that browses **only CRITICAL patients**

### C — Test Scenario *(1 pt)*

Write a single JUnit test that verifies both iterators work correctly
and that the filtered iterator throws when exhausted.