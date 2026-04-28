## 🚀 Exam: CI/CD Pipeline - Build Step Orchestration

In a DevOps automation platform, a deployment pipeline is composed of discrete, ordered steps: building artifacts, running tests, pushing Docker images, and deploying to an environment. Each step must be executable individually, chainable into a pipeline, and — critically — **rollback-capable** in case a later step fails.

You are tasked with modeling a **PipelineRunner** that executes build steps as command objects against a **DeploymentEnvironment** receiver, with support for rolling back a failed deployment sequence.

### 🚧 Your Tasks

#### UML Class Diagram
Design a PlantUML class diagram that models:
- A `PipelineCommand` interface with `execute(): Boolean` (returns success/failure) and `undo()`.
- Concrete commands: `BuildCommand`, `RunTestsCommand`, `PushImageCommand`, `DeployCommand`.
- A **`PipelineCommand` Composite** — a `StagedPipeline` that holds an ordered list of commands and halts + rolls back on first failure.
- The `PipelineRunner` (Invoker) and `DeploymentEnvironment` (Receiver).

#### Implementation (Kotlin)
- `DeploymentEnvironment` holds: `artifactReady: Boolean`, `testsPass: Boolean`, `imageTag: String?`, `deployedVersion: String?`.
- Implement each concrete command with realistic state mutations on `DeploymentEnvironment`.
- Implement `StagedPipeline`: it executes commands in sequence; if one returns `false`, it calls `undo()` in reverse order on all previously succeeded commands.
- **Logic Constraint:** `DeployCommand` must not execute if `imageTag` is null (i.e., `PushImageCommand` has not succeeded).

#### Demonstration (PipelineDemo)
1. Create a successful pipeline: Build → Test → Push → Deploy. Print environment state after completion.
2. Simulate a failing pipeline where `RunTestsCommand` is configured to fail. Verify that `BuildCommand`'s undo is called and `artifactReady` returns to `false`.
3. Print the full environment state after each pipeline attempt.

#### Sequence Diagram
Generate a PlantUML sequence diagram for the **failing pipeline scenario**, showing how `StagedPipeline` iterates forward, encounters the failure at `RunTestsCommand`, then iterates backward calling `undo()` on each previously executed command.

### 📝 Technical Constraints
- **Isolation:** `DeploymentEnvironment` must not know about the pipeline structure or history.
- **Atomicity:** A partial pipeline failure must leave the environment in its **exact pre-pipeline state**, not a partial state.
- **Extensibility:** Adding a new step (e.g., `NotifySlackCommand`) should require zero changes to `StagedPipeline` or `PipelineRunner`.