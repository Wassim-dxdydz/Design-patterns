# 🏦 Exam: SecureBank - Transactional Integrity

In high-stakes financial systems, every action must be traceable and reversible to ensure data integrity. A banking application requires a robust way to handle customer transactions, such as deposits and transfers, while allowing for immediate corrections (reversals) if an error occurs or a customer changes their mind.

For this system, you are tasked with managing a **Savings Account** that tracks a balance. The system must support multiple types of operations that can be triggered by a **Banking App** interface.

---

### 🚧 Your Tasks:

1.  **UML Class Diagram:**
    *   Design a class diagram in PlantUML that models the Command Pattern for this banking scenario.
    *   Clearly show the relationships between the components.

2.  **Implementation (Kotlin):**
    *   Create a `BankAccount` that starts with a balance of **$1000**.
    *   Implement a `DepositCommand` and a `WithdrawCommand`.
    *   Implement an "Undo" mechanism. If a withdrawal of $200 is "undone," the account must return to its exact state prior to that specific withdrawal.
    *   **Logic Constraint:** A withdrawal should not be executed (or added to history) if the account has insufficient funds.

3.  **Demonstration (BankDemo):**
    *   Perform a deposit of $500.
    *   Perform a withdrawal of $200.
    *   Attempt an "Undo" of the withdrawal.
    *   Attempt a withdrawal of $5000 (should fail/be handled).
    *   Print the balance after every operation.

4.  **Sequence Diagram:**
    *   Generate a PlantUML sequence diagram showing the lifecycle of a **Withdrawal** that is successfully executed and then subsequently **Undone**.

---

### 📝 Important:
Ensure your implementation strictly adheres to the Command Pattern principles. The logic for "how" to revert a transaction must reside within the specific command objects, not the account itself.