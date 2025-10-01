package core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * Represents a single unit of work. Now tracks all users involved in its lifecycle.
 */
public class Task {
    private final String id;
    private String description;
    private final PriorityLevel priority;
    private TaskStatus status;
    private User owner;
    private final List<User> involvedUsers = new ArrayList<>(); // NEW: Tracks all observers

    public Task(String description, PriorityLevel priority, User owner) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty.");
        }
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.description = description;
        this.priority = priority;
        this.owner = owner;
        this.status = TaskStatus.DRAFT;
        this.addInvolvedUser(owner); // The owner is the first involved user
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getDescription() { return description; }
    public PriorityLevel getPriority() { return priority; }
    public TaskStatus getStatus() { return status; }
    public User getOwner() { return owner; }
    public List<User> getInvolvedUsers() { return involvedUsers; } // NEW

    // --- Setters / Modifiers ---
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setOwner(User owner) { this.owner = owner; }
    
    /**
     * Adds a user to the list of people who should receive notifications about this task.
     * Prevents duplicate entries.
     * @param user The user to add.
     */
    public void addInvolvedUser(User user) {
        if (!this.involvedUsers.contains(user)) {
            this.involvedUsers.add(user);
        }
    }
}

