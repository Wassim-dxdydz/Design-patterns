# 💡 Exam: SmartHome - Ambient Lighting Control

The **Command Pattern** is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. This transformation lets you pass requests as a method arguments, delay or queue a request's execution, and support undoable operations.

In this scenario, we focus on a **Dimmable Light** system. Unlike a simple switch, a dimmable light has a range of brightness. The system must allow users to set specific brightness levels and provide an "Undo" feature to revert to the previous lighting state if a change is unsatisfactory.

### 🏗️ Participants:
*   **Command (Interface):** Defines the interface for executing an operation.
*   **Concrete Command (DimCommand):** Defines a binding between a Receiver and an action.
*   **Receiver (Light):** Knows how to perform the operations associated with carrying out a request.
*   **Invoker (RemoteControl):** Asks the command to carry out the request.

---

### 🚧 Your Tasks:

1.  **UML Class Diagram:**
    * Identify the participants in a class diagram using PlantUML.
    * Ensure you show the relationship between the `RemoteControl`, the `Command` interface, and the `Light` receiver.

2.  **Implementation (Kotlin):**
    * Implement the `Light` receiver with a `brightness` attribute (an integer from 0 to 100).
    * Implement the `DimCommand`. **Challenge:** The `undo()` method must restore the light to the *exact* brightness level it had before this specific command was executed.
    * Create a `RemoteControl` (Invoker) that manages a history of commands to support the undo functionality.

3.  **Scenario Logic (SmartHomeDemo):**
    * Create a light starting at 10% brightness.
    * Use the Remote to set it to 50%.
    * Use the Remote to set it to 100%.
    * Trigger an `undo()` to return to 50%.
    * Print the state of the light at each step to verify the logic.

4.  **Sequence Diagram:**
    * Write the PlantUML sequence diagram showing the flow of the "Set to 50%" execution followed by the "Undo" action.

---

### 🎓 Professor's Note:
Be careful with the **state management**. A common mistake is to have the `undo()` simply "decrease" brightness. In the Command Pattern, the command object itself must "remember" the specific state of the receiver at the moment of execution to ensure a perfect reversal.