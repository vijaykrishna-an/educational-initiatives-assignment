package patterns.chainofresponsibility;

import core.Task;
import core.User;

/**
 * A request object that carries the task and the user performing the action through the chain.
 */
public class TaskActionRequest {
    private final Task task;
    private final User user;

    public TaskActionRequest(Task task, User user) {
        this.task = task;
        this.user = user;
    }

    public Task getTask() { return task; }
    public User getUser() { return user; }
}
