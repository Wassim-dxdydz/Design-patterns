# 📈 Scenario 3 — Stock Market Price Alert

## Context

You are building a **stock market monitoring tool**. A stock tracker follows
the price of a given stock in real time. Every time the price changes, several
**independent watchers** need to react: a mobile alert, a trading bot, and a
portfolio dashboard.

The current code handles all reactions directly inside the tracker, making it
impossible to add new watchers without modifying the core class.

Your task is to **refactor it using the Observer Pattern**.

### Current broken code:

```kotlin
class StockTracker(val symbol: String) {
    var price: Double = 0.0

    fun updatePrice(newPrice: Double) {
        price = newPrice

        // Alert the mobile app
        println("📱 Mobile alert: $symbol is now $$price")

        // Notify the trading bot
        println("🤖 Bot: analyzing new price $$price for $symbol")

        // Refresh the dashboard
        println("📊 Dashboard: updating chart for $symbol at $$price")
    }
}
```

### Possible price changes:
- A price going **up** (e.g. from `150.0` to `178.5`)
- A price going **down** (e.g. from `178.5` to `140.0`)
- A price staying **stable** (same value triggered again)

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*
Design a class diagram that models the Observer Pattern applied to this scenario.

### B — Kotlin Implementation *(2.5 pts)*
Refactor the code so that:
- `StockTracker` no longer directly calls any watcher
- Watchers can be attached or detached at runtime
- Each watcher reacts independently when the price changes
- There must be **at least three** distinct watchers
- Watchers must expose a `lastPrice` property for testing

### C — Test Scenario *(1 pt)*
Write a JUnit test that:
1. Triggers a price update with no watchers attached — verifies no crash
2. Attaches all watchers, updates the price, and asserts each received the correct value
3. Detaches one watcher, updates the price again, and asserts the detached one did **not** update
4. Re-attaches the detached watcher and confirms it reacts to the next price change