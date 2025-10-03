# Workflow Automation System

A console-based Java application that simulates a modern, flexible workflow automation system. This project allows users with different roles (Junior, Manager, Senior) to create, manage, and process tasks through a realistic lifecycle. The system is built on a foundation of powerful design patterns to ensure the code is decoupled, scalable, and easy to maintain.

It demonstrates advanced concepts like role-based permissions, task delegation, multi-user notifications, and intelligent, authority-based task processing.

## Key Features

* **Role-Based Access Control** **:** Users are assigned roles (Junior, Manager, Senior) that determine their permissions.
* **Dynamic Task Lifecycle** **:** Tasks move through a realistic lifecycle (`IN_PROGRESS`, `PENDING_APPROVAL`, `APPROVED`, `REJECTED`, `NEEDS_REWORK`).
* **Intelligent Workflow Processing** **:** High-authority users can "fast-forward" tasks through multiple approval stages in a single action.
* **Task Delegation** **:** Managers and Seniors can reassign tasks to other users.
* **Collaborative Notifications** **:** All users involved in a task's lifecycle are automatically notified of status changes.
* **Safe Undo Functionality** **:** Users can revert their most recent action, preventing mistakes before they move up the chain.

## Architectural Design & Patterns

The application's logic is driven by three core behavioral design patterns, implemented in an advanced and decoupled manner.

### 1. Chain of Responsibility (CoR)

This pattern is the engine of the workflow. The application uses a **Task Processing Chain** that intelligently advances a task's status based on the user's authority and the desired action. For example, a Senior user can approve a task that is still in `DRAFT`, and the chain will correctly execute all intermediate steps (`Start`, `Submit`, `Manager Approval`, `Senior Approval`) behind the scenes. This demonstrates the pattern's true power in handling complex, stateful operations.

### 2. Observer

The notification system is completely decoupled using a central **`EventManager`**. The `Task` object is a clean data object and does not manage observers itself. When a task's state changes, the `WorkflowService` informs the `EventManager`, which then notifies  **all users who have ever interacted with the task** **, creating a realistic, collaborative notification model.**

### 3. Memento

This pattern provides a **step-specific "Undo"** feature. A snapshot of a task's state is saved *before* any major action (like submitting or approving). This allows the user who performed the action to revert their own mistake before the task moves to the next stage. Once a task receives final approval, its undo history is cleared to ensure process finality.

## Task Lifecycle

The journey of a task through the system follows a clear and logical path:

1. **`DRAFT`** **:** Any user can create a task. It starts in this initial state.
2. **`IN_PROGRESS`** **:** A user with appropriate authority (based on task priority) starts work.
3. **`PENDING_MANAGER_APPROVAL`** **:** The task is submitted for review.
4. **`PENDING_SENIOR_APPROVAL`** **:** After a Manager's approval, it awaits final sign-off.
5. **`APPROVED`** **:** The Senior gives final approval, completing the workflow.

* **`NEEDS_REWORK`** **:** A task can be sent back by a Senior for corrections.
* **`REJECTED`** **:** A Manager or Senior can terminate the workflow permanently.

## Project Structure

```
workflow_automation/
├── core/
│   ├── PriorityLevel.java
│   ├── Task.java
│   ├── TaskStatus.java
│   ├── User.java
│   └── UserRole.java
├── patterns/
│   ├── chainofresponsibility/
│   │   ├── ManagerApprovalHandler.java
│   │   ├── SeniorApprovalHandler.java
│   │   ├── StartTaskHandler.java
│   │   ├── SubmitTaskHandler.java
│   │   ├── TaskActionRequest.java
│   │   └── TaskProcessorHandler.java
│   ├── memento/
│   │   ├── MementoCaretaker.java
│   │   └── TaskMemento.java
│   └── observer/
│       ├── EventManager.java
│       ├── LoggingObserver.java
│       ├── TaskObserver.java
│       └── UserObserver.java
├── services/
│   ├── AuthenticationService.java
│   ├── NotificationService.java
│   └── WorkflowService.java
├── ui/
│   └── ConsoleInterface.java
├── Main.java
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

* **Java Development Kit (JDK)** **:** Version 8 or higher.
* **Apache Maven** **:** For dependency management and building.

### Compilation & Execution

1. **Clone the repository** to your local machine.
2. **Open a terminal** or command prompt and navigate to the project's root directory (`workflow_automation/`).
3. **Clean and Compile** the project using Maven:

   ```
   mvn clean compile
   ```
4. **Run** the application:

   ```
   mvn exec:java -Dexec.mainClass="com.workflow.Main"
   ```

The application will start, presenting you with the main menu.

## Usage Scenarios

### Standard Workflow

1. A **Senior** logs in and creates a **LOW** priority task.
2. The Senior **reassigns** this task to a **Junior** user.
3. The **Junior** logs in, sees the task in their list, and **starts** it (`IN_PROGRESS`).
4. The Junior **submits** the task (`PENDING_MANAGER_APPROVAL`).
5. A **Manager** logs in and **approves** the task (`PENDING_SENIOR_APPROVAL`).
6. The **Senior** logs in and gives the **final approval** (`APPROVED`).

### "Fast-Forward" Workflow (CoR in Action)

1. A **Junior** creates and submits a **LOW** priority task (`PENDING_MANAGER_APPROVAL`).
2. A **Senior** logs in, sees the task, and chooses the "Approve" action.
3. The Chain of Responsibility intelligently executes both the Manager and Senior approval steps in sequence. The task status jumps directly to `APPROVED`, and all involved users are notified of the rapid progress.
