# 📡 Scenario 1 — Smart Home Notification System

## Context

You are building a **smart home application**. The house has a central **sensor hub** that monitors the home environment. When the hub detects a change (e.g., temperature shift, motion detected, door opened), several **independent systems** in the house need to react.

The current implementation is a mess — the hub directly calls each system one by one in a giant method, making it impossible to add or remove systems without touching the hub's code.

Your task is to **refactor it using the Observer Pattern**.

### Current broken code:

```kotlin
class SensorHub {
    var lastEvent: String = ""

    fun triggerEvent(event: String) {
        lastEvent = event

        // Notify alarm system
        println("🚨 Alarm: reacting to $event")

        // Notify lights
        println("💡 Lights: adjusting to $event")

        // Notify thermostat
        println("🌡️ Thermostat: recalibrating after $event")
    }
}
```

### Events the hub can emit:
- `"motion_detected"`
- `"door_opened"`
- `"temperature_drop"`

---

## 🚧 What You Must Produce

### A — PlantUML Class Diagram *(0.5 pt)*
Design a class diagram that models the Observer Pattern applied to this scenario. Make sure the relationships between the hub and the reacting systems are clearly visible.

### B — Kotlin Implementation *(2.5 pts)*
Refactor the code so that:
- The hub no longer knows about the systems that react to it
- Systems can be attached or detached from the hub at runtime
- Each reacting system handles the event in its own way
- The hub notifies all attached systems automatically when an event is triggered
- There must be **at least three** distinct reacting systems

### C — Test Scenario *(1 pt)*
Write a JUnit test that:
1. Triggers an event with no systems attached and verifies nothing crashes
2. Attaches all three systems and triggers an event, verifying each reacted
3. Detaches one system and triggers a new event, verifying only the remaining ones reacted
4. Re-attaches the detached system and confirms it reacts again

---

## 📝 Technical Constraints
- The hub must remain **unaware** of concrete system types
- Subscribing and unsubscribing must work at any point during runtime
- Systems must read the event data **from the hub itself**, not as a direct parameter
- No system should share logic with another — each reacts independently