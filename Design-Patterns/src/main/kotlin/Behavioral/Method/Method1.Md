# 🏥 Exam: MediLab — Medical Report Generator

## Context

A medical laboratory processes different types of biological analyses (blood tests, urine tests, imaging reports). Each analysis follows the **same global pipeline** — collect the sample, run the analysis, interpret the results, and generate the final report — but the **analysis step and interpretation logic differ** depending on the type of exam.

The current codebase is a mess of duplicated methods spread across unrelated classes. Your job is to **refactor it using the Template Method Pattern**.

---

## 🧬 Analysis Types

| Type         | Analysis Step                            | Interpretation                        |
|--------------|------------------------------------------|---------------------------------------|
| `BloodTest`  | Measures hemoglobin, white cells, etc.   | Flags values outside normal ranges    |
| `UrineTest`  | Measures pH, protein, glucose, etc.      | Detects abnormal concentration levels |
| `ImagingReport` | Processes scan/X-ray metadata         | Generates a radiologist summary note  |

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*
Model the Template Method structure:
- An abstract class `LabAnalysis` with the template method and its steps
- Identify which steps are **fixed** (common to all), which are **abstract** (must be overridden), and whether any **hook** is relevant here
- The three concrete subclasses

### B — Kotlin Implementation *(2.5 pts)*
- Implement the abstract class `LabAnalysis` with a template method `runAnalysis(patientName: String)`
- Fixed steps (implement in base class): sample collection and report generation
- Variable steps (abstract): the core analysis and interpretation
- Include at least **one hook** that subclasses may optionally override (think: does every analysis type need a calibration step before running?)
- Implement the three concrete classes

### C — Demonstration *(1 pt)*
In a `main()` function:
1. Run a `BloodTest` for patient `"Marie Curie"`
2. Run a `UrineTest` for patient `"Louis Pasteur"`
3. Run an `ImagingReport` for patient `"Ada Lovelace"`
4. Show that the hook behaves differently between at least two of the three types

---

## 📝 Technical Constraints
- The template method must **not** be overridable by subclasses
- All shared steps must live **only** in the base class — no duplication
- Hooks must have a **sensible default** in the base class
- No `if/else` or `when` branching on type inside the base class