package services;

import core.*;
import patterns.chainofresponsibility.*;
import patterns.memento.MementoCaretaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The main orchestrator of the application with the new, flexible logic.
 */
public class WorkflowService {
    private final Map<String, Task> tasks = new HashMap<>();
    private final Map<String, User> usersByName = new HashMap<>();
    private final MementoCaretaker caretaker = new MementoCaretaker();
    private final NotificationService notificationService;
    private final TaskProcessorHandler taskChain;

    public WorkflowService(NotificationService notificationService) {
        this.notificationService = notificationService;
        // Build the full, powerful chain of responsibility ONCE.
        this.taskChain = buildFullProcessingChain();
    }
    
    private TaskProcessorHandler buildFullProcessingChain() {
        TaskProcessorHandler start = new StartTaskHandler();
        TaskProcessorHandler submit = new SubmitTaskHandler();
        TaskProcessorHandler manager = new ManagerApprovalHandler();
        TaskProcessorHandler senior = new SeniorApprovalHandler();
        
        start.setNext(submit);
        submit.setNext(manager);
        manager.setNext(senior);
        return start;
    }

    public void registerUser(User user) {
        usersByName.put(user.getUsername(), user);
    }

    public Task createTask(String description, PriorityLevel priority, User owner) {
        Task task = new Task(description, priority, owner);
        tasks.put(task.getId(), task);
        System.out.println("Task '" + description + "' created with ID: " + task.getId());
        notificationService.subscribeUserToTask(owner, task); // Initial subscription
        return task;
    }

    /**
     * NEW LOGIC: Visibility is now based on user's authority level vs task priority.
     */
    public List<Task> getTasksForUser(User user) {
        return tasks.values().stream()
            .filter(task -> user.getRole().level >= task.getPriority().value)
            .collect(Collectors.toList());
    }

    /**
     * A single, powerful method to process any action on a task.
     * It uses the CoR to intelligently advance the task state.
     */
    public void processTaskAction(Task task, User user) {
        // PERMISSION CHECK: User must have authority for this task's priority level.
        if (user.getRole().level < task.getPriority().value) {
            System.out.println("Error: Your role (" + user.getRole() + ") is not high enough to process this " + task.getPriority() + " priority task.");
            return;
        }

        // Add the current user to the list of observers
        task.addInvolvedUser(user);
        
        TaskStatus originalStatus = task.getStatus();
        Task taskBeforeChange = new Task(task.getDescription(), task.getPriority(), task.getOwner());
        taskBeforeChange.setStatus(originalStatus);
        
        System.out.println("User '" + user.getUsername() + "' is processing task " + task.getId() + ". Starting CoR...");
        TaskActionRequest request = new TaskActionRequest(task, user);
        taskChain.handleRequest(request);

        // If the state actually changed, save the previous state and notify.
        if (originalStatus != task.getStatus()) {
            caretaker.saveState(taskBeforeChange);
            notificationService.notifyTaskUpdate(task);
            if (task.getStatus() == TaskStatus.APPROVED || task.getStatus() == TaskStatus.REJECTED) {
                caretaker.clearHistory(task.getId());
            }
        } else {
            System.out.println("No state change occurred for the task.");
        }
    }
    
    // Simplified action methods now delegate to the main processTaskAction
    public void startTask(Task task, User user) { processTaskAction(task, user); }
    public void submitForApproval(Task task, User user) { processTaskAction(task, user); }
    public void approveTask(Task task, User user) { processTaskAction(task, user); }

    public void reassignTask(Task task, User newOwner, User currentUser) {
        if (currentUser.getRole().level >= UserRole.MANAGER.level) {
            caretaker.saveState(task);
            task.setOwner(newOwner);
            task.addInvolvedUser(newOwner); // The new owner is now an observer
            System.out.println("Task " + task.getId() + " reassigned to " + newOwner.getUsername());
            notificationService.notifyTaskUpdate(task);
        } else {
            System.out.println("Error: Only Managers or Seniors can reassign tasks.");
        }
    }
    
    public void rejectTask(Task task, User approver) {
        if (approver.getRole().level >= task.getPriority().value) {
            caretaker.saveState(task);
            task.setStatus(TaskStatus.REJECTED);
            System.out.println("Task " + task.getId() + " has been REJECTED by " + approver.getUsername());
            notificationService.notifyTaskUpdate(task);
            caretaker.clearHistory(task.getId());
        } else {
             System.out.println("Error: Your role is not high enough to reject this task.");
        }
    }

    public void undoLastAction(Task task) {
        caretaker.undo(task);
        System.out.println("Last action for task " + task.getId() + " has been undone.");
        notificationService.notifyTaskUpdate(task);
    }

    public Task getTask(String taskId) { return tasks.get(taskId); }
    public User findUserByUsername(String username) { return usersByName.get(username); }
}

