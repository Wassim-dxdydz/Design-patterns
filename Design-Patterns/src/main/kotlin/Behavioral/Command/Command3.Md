# 🎮 Exam: RTS Engine - Unit Tactical Commands

In a Real-Time Strategy (RTS) game, players manage hundreds of units simultaneously. To maintain performance and allow for features like "Action Queuing" (Shift-clicking commands) and "Global Undo" (reverting a tactical mistake), the game engine must treat every unit action as a discrete object.

The challenge in this scenario is managing **spatial state** and **grouped actions**. A unit exists at a 2D coordinate $(x, y)$ and can have its position modified or its state changed (e.g., entering "Guard Mode").

---

### 🚧 Your Tasks:

1.  **UML Class Diagram:**
    *   Design a PlantUML class diagram that can handle different types of unit actions.
    *   Model a "Macro Command" (a command that contains a list of other commands) to allow for "Patrol" sequences (Move to A, then Move to B, then Move to C).

2.  **Implementation (Kotlin):**
    *   **Receiver:** Create a `Soldier` class with `posX`, `posY`, and a `status` string (e.g., "Idle", "Guarding").
    *   **Concrete Commands:**
        *   `MoveCommand`: Takes `deltaX` and `deltaY`.
        *   `GuardCommand`: Changes status to "Guarding".
    *   **Complex Component:** Implement a `PatrolCommand` (Composite Pattern variation) which takes a list of `MoveCommands`.
    *   **Undo Logic:** The `undo()` for a `PatrolCommand` must reverse every move in the sequence in **reverse order** to bring the soldier back to the original starting point.

3.  **Demonstration (GameDemo):**
    *   Initialize a Soldier at (0, 0).
    *   Execute a `MoveCommand` to (5, 5).
    *   Execute a `PatrolCommand` that moves the soldier to (10, 10) and then to (20, 10).
    *   Execute a `GuardCommand`.
    *   Perform an `undo()` on the `GuardCommand`.
    *   Perform an `undo()` on the `PatrolCommand` and verify the soldier is back at (5, 5).

4.  **Sequence Diagram:**
    *   Generate a PlantUML sequence diagram specifically for the **PatrolCommand execution**. Show how the Macro Command iterates through its internal list of MoveCommands.

---

### 📝 Technical Constraints:
*   **Encapsulation:** The `Soldier` should not store its own history.
*   **Precision:** Ensure that the `MoveCommand` stores the *displacement* or the *previous coordinates* correctly so that multiple undos return the unit to the exact pixel it started on.
*   **Validation:** A `GuardCommand` should not be executable if the soldier is already in "Guarding" status.