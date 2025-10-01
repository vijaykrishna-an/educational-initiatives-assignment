package ui;

import core.*;
import services.AuthenticationService;
import services.NotificationService;
import services.WorkflowService;

import java.util.List;
import java.util.Scanner;

/**
 * The UI, simplified to use the new, powerful workflow service methods.
 */
public class ConsoleInterface {
    private final Scanner scanner;
    private final AuthenticationService authService;
    private final WorkflowService workflowService;
    private User currentUser;

    public ConsoleInterface(AuthenticationService authService, WorkflowService workflowService, NotificationService notificationService) {
        this.scanner = new Scanner(System.in);
        this.authService = authService;
        this.workflowService = workflowService;
        this.currentUser = null;
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (!isLoggedIn()) {
                switch (choice) {
                    case "1": handleRegister(); break;
                    case "2": handleLogin(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            } else {
                 switch (choice) {
                    case "3": handleListTasks(); break;
                    case "4": handleCreateTask(); break;
                    case "5": handleViewTask(); break;
                    case "6": handleUpdateTask(); break; // Simplified name
                    case "7": handleLogout(); break;
                    case "0": running = false; break;
                    default: System.out.println("Invalid choice.");
                }
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
    }

    private void displayMainMenu() {
        System.out.println("===== Workflow Automation System =====");
        if (currentUser == null) {
            System.out.println("1. Register");
            System.out.println("2. Login");
        } else {
            System.out.println("Welcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
            System.out.println("--------------------------------------");
            System.out.println("3. List Tasks");
            System.out.println("4. Create Task");
            System.out.println("5. View Task by ID");
            System.out.println("6. Process Task by ID");
            System.out.println("7. Logout");
        }
        System.out.println("0. Exit");
        System.out.println("======================================");
    }
    
    private void handleListTasks() {
        List<Task> tasks = workflowService.getTasksForUser(currentUser);
        if (tasks.isEmpty()) {
            System.out.println("No tasks found matching your authority level.");
            return;
        }
        System.out.println("--- Tasks You Can See/Process ---");
        System.out.println(String.format("%-10s | %-25s | %-10s | %-15s | %-30s", "ID", "STATUS", "PRIORITY", "OWNER", "DESCRIPTION"));
        System.out.println(String.format("%s", "-".repeat(95)));
        for (Task task : tasks) {
            System.out.println(String.format("%-10s | %-25s | %-10s | %-15s | %-30s",
                task.getId(), task.getStatus(), task.getPriority(), task.getOwner().getUsername(),
                task.getDescription().length() > 28 ? task.getDescription().substring(0, 27) + "..." : task.getDescription()
            ));
        }
    }

    private void handleUpdateTask() {
        Task task = promptForTask();
        if (task == null) return;
        System.out.println("Current task status: " + task.getStatus());
        System.out.println("--- Available Actions ---");
        System.out.println("1. Advance Task (Start/Submit/Approve)");
        System.out.println("2. Reassign Task (Managers/Seniors)");
        System.out.println("3. Reject Task (Final)");
        System.out.println("4. Undo Last Action");
        System.out.println("0. Cancel");
        System.out.print("Choose action: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1": workflowService.processTaskAction(task, currentUser); break;
            case "2": handleReassignTask(task); break;
            case "3": workflowService.rejectTask(task, currentUser); break;
            case "4": workflowService.undoLastAction(task); break;
            case "0": break;
            default: System.out.println("Invalid action.");
        }
    }
    
    // Other handle methods (register, login, etc.) remain largely the same...
    private void handleRegister() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password (min 4 chars): ");
        String password = scanner.nextLine().trim();
        System.out.print("Enter role (JUNIOR, MANAGER, SENIOR): ");
        try {
            UserRole role = UserRole.valueOf(scanner.nextLine().trim().toUpperCase());
            authService.register(username, password, role);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid role.");
        }
    }

    private void handleLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        currentUser = authService.login(username, password);
    }

    private void handleLogout() {
        System.out.println("User " + currentUser.getUsername() + " logged out.");
        currentUser = null;
    }

    private void handleCreateTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();
        PriorityLevel priority = promptForPriority();
        if (priority == null) return;
        workflowService.createTask(description, priority, currentUser);
    }
    
    private void handleViewTask() {
        Task task = promptForTask();
        if (task != null) {
            System.out.println("--- Task Details ---");
            System.out.println("ID: " + task.getId());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Status: " + task.getStatus());
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Owner: " + task.getOwner().getUsername());
            System.out.print("Involved Users: ");
            task.getInvolvedUsers().forEach(u -> System.out.print(u.getUsername() + " "));
            System.out.println("\n--------------------");
        }
    }

    private void handleReassignTask(Task task) {
        System.out.print("Enter username of the new owner: ");
        String newOwnerUsername = scanner.nextLine().trim();
        User newOwner = workflowService.findUserByUsername(newOwnerUsername);
        if (newOwner != null) {
            workflowService.reassignTask(task, newOwner, currentUser);
        } else {
            System.out.println("Error: User not found.");
        }
    }

    private boolean isLoggedIn() { return currentUser != null; }

    private Task promptForTask() {
        System.out.print("Enter Task ID: ");
        String taskId = scanner.nextLine().trim();
        Task task = workflowService.getTask(taskId);
        if (task == null) {
            System.out.println("Error: Task not found.");
        }
        return task;
    }

    private PriorityLevel promptForPriority() {
        while (true) {
            System.out.print("Enter Priority (1=Low, 2=Medium, 3=High): ");
            try {
                int priorityVal = Integer.parseInt(scanner.nextLine().trim());
                for (PriorityLevel p : PriorityLevel.values()) {
                    if (p.value == priorityVal) return p;
                }
                System.out.println("Error: Invalid priority number.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a number.");
            }
        }
    }
}

