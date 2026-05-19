# 🛒 Scenario 2 — E-Commerce Order Tracker

## Context

You are building an **e-commerce platform**. When a customer places an order,
its status evolves over time: confirmed, shipped, delivered, cancelled.

Every time the order status changes, several **independent services** need to
react: sending notifications, updating inventory, generating invoices, etc.

The current code has all of this crammed into a single method that directly
handles every service, making it rigid and hard to extend.

Your task is to **refactor it using the Observer Pattern**.

### Current broken code:

```kotlin
class Order(val id: String) {
    var status: String = ""

    fun updateStatus(newStatus: String) {
        status = newStatus

        // Notify customer
        println("📧 Email to customer: your order is now $status")

        // Update inventory
        println("📦 Inventory: adjusting stock for order $id")

        // Generate invoice
        println("🧾 Invoice: generating document for status $status")
    }
}
```

### Possible order statuses:
- `"confirmed"`
- `"shipped"`
- `"delivered"`
- `"cancelled"`

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*
Design a class diagram that models the Observer Pattern applied to this scenario.

### B — Kotlin Implementation *(2.5 pts)*
Refactor the code so that:
- The `Order` class no longer directly calls any service
- Services can be attached or detached at runtime
- Each service reacts independently when the order status changes
- There must be **at least three** distinct services
- Services must track the last status they received so they can be tested

### C — Test Scenario *(1 pt)*
Write a JUnit test that:
1. Creates an order and triggers a status change with no services attached — verifies no crash
2. Attaches all services, updates the status, and asserts each service received the correct status
3. Detaches one service, updates the status again, and asserts the detached one did **not** update
4. Re-attaches the detached service and confirms it reacts to the next status change

---

## 📝 Technical Constraints
- `Order` must remain **unaware** of the concrete service types
- Each service must expose a `lastStatus` property for testing
- Subscribing and unsubscribing must work at any point during runtime
- Services must read the status **from the `Order` itself**, not as a direct parameter