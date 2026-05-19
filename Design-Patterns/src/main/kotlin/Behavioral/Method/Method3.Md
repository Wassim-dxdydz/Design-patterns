# 🏥 Exam: MedReport — Patient Report Generator

## Context

A hospital management system generates medical reports for patients.
Regardless of the report type, every report follows the same generation
pipeline — but certain steps vary depending on the department.

The current codebase has grown messy with repeated logic scattered across
multiple classes. Your task is to **identify the pattern that best fits**
and refactor accordingly.

### Current (messy) code:

```kotlin
class GeneralReport {
    fun generate() {
        println("📋 Collecting patient basic info...")
        println("🩺 Running standard checkup analysis...")
        println("📊 Formatting report as plain text...")
        println("📧 Sending report to general practitioner...")
    }
}

class CardiologyReport {
    fun generate() {
        println("📋 Collecting patient basic info...")
        println("❤️ Running ECG and cardiac stress analysis...")
        println("📊 Formatting report as PDF with charts...")
        println("📧 Sending report to cardiology department...")
    }
}

class PediatricsReport {
    fun generate() {
        println("📋 Collecting patient basic info...")
        println("🧒 Running growth and vaccine schedule analysis...")
        println("📊 Formatting report as plain text...")
        println("📧 Sending report to pediatrician...")
    }
}
```

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*

Design a class diagram that models the refactored solution:
- Identify which steps are shared and which are variable
- Show the abstract class, its methods with correct visibility
- Show all three concrete subclasses and their relationships

### B — Kotlin Implementation *(2 pts)*

Refactor the three classes above:
- Extract the common skeleton into an `abstract class MedReport`
- Steps that never change across departments must **not** be overridable
- Each department only implements what is strictly specific to it
- Add a hook `onReportComplete()` — invoked at the end of `generate()`,
  with no effect by default, but `CardiologyReport` uses it to print:
  `"🔔 Notifying on-call cardiologist..."`

### C — Extension *(0.5 pt)*

Add a new department: **OncologyReport**
- Its analysis step prints: `"🎗️ Running tumor marker and imaging analysis..."`
- Its formatting step prints: `"📊 Formatting report as encrypted PDF..."`
- It sends to: `"📧 Sending report to oncology board..."`
- It also triggers the hook to print: `"🔔 Alerting oncology coordinator..."`

### D — Demo *(1 pt)*

Write a `main()` function that:
1. Stores all four report types in a `List<MedReport>`
2. Calls `generate()` on each
3. Output must clearly show each department's specific steps firing in the correct order

---

## 📝 Technical Constraints
- `generate()` must be non-overridable by subclasses
- The hook must have a **no-op default** (empty return)
- Concrete subclasses must be **as minimal as possible**
- The step responsible for collecting patient info must be **identical** across
  all departments and defined only once