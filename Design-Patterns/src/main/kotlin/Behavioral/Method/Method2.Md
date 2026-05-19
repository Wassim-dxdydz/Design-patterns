# 🍵 Exam: BrewMaster — Hot Beverage Preparation

## Context

A café automation system needs to prepare different hot beverages.
Regardless of the type, every beverage follows the **same general preparation
pipeline** — but some steps differ depending on the drink.

You notice that the current codebase has two nearly identical classes with
duplicated logic. Your task is to **refactor them using the Template Method Pattern**.

### Current (duplicated) code:

```kotlin
class TeaMaker {
    fun prepare() {
        boilWater()
        steepTeaBag()
        pourInCup()
        addLemon()
    }

    private fun boilWater() = println("🫖 Boiling water...")
    private fun steepTeaBag() = println("🍵 Steeping the tea bag for 3 minutes...")
    private fun pourInCup() = println("🥤 Pouring into cup...")
    private fun addLemon() = println("🍋 Adding a slice of lemon...")
}

class CoffeeMaker {
    fun prepare() {
        boilWater()
        brewCoffeeGrinds()
        pourInCup()
        addSugarAndMilk()
    }

    private fun boilWater() = println("🫖 Boiling water...")
    private fun brewCoffeeGrinds() = println("☕ Brewing coffee grinds...")
    private fun pourInCup() = println("🥤 Pouring into cup...")
    private fun addSugarAndMilk() = println("🥛 Adding sugar and milk...")
}
```

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*

Design a class diagram showing:
- The abstract class `HotBeverage` with the template method `prepare()` and its steps
- Which steps are `abstract`, which are concrete, and which are **hooks**
- The two concrete subclasses: `Tea` and `Coffee`

### B — Kotlin Implementation *(2 pts)*

Refactor the code above into the Template Method Pattern:
- `abstract class HotBeverage` with `fun prepare()` as the sealed template method
- Common steps (`boilWater`, `pourInCup`) implemented directly in the abstract class
- Variable steps (`brew()`) declared as `abstract`
- An optional hook `addCondiments()` — override it only if the beverage needs extras
- `class Tea` and `class Coffee` as concrete subclasses

### C — Extension *(0.5 pt)*

Add a third beverage: **HotChocolate**
- It brews by melting chocolate powder into hot water
- It always adds marshmallows as a condiment

### D — Demo *(1 pt)*

Write a `main()` function that:
1. Prepares a `Tea`, a `Coffee`, and a `HotChocolate` using a shared list of type `List<HotBeverage>`
2. Calls `prepare()` on each — output should clearly reflect each beverage's steps

---

## 📝 Technical Constraints
- `prepare()` must be `final` (cannot be overridden by subclasses)
- The hook `addCondiments()` must have a **no-op default** in the abstract class
- No business logic inside `Tea` or `Coffee` other than their specific steps
- Each concrete class must be **minimal** — only override what is strictly necessary