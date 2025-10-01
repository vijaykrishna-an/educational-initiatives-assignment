package patterns.memento;

import core.Task;
import core.TaskStatus;
import core.User;

/**
 * Stores a snapshot of a Task's mutable state (status and owner).
 * It is immutable.
 */
public class TaskMemento {
    // We only need to store the fields that can actually change during the workflow.
    private final TaskStatus status;
    private final User owner;

    public TaskMemento(Task task) {
        this.status = task.getStatus();
        this.owner = task.getOwner();
    }

    /**
     * Restore method is used by the Caretaker to apply the state back to the Task.
     * The description is not restored as it's considered a fixed property after creation.
     */
    public void restore(Task task) {
        task.setStatus(this.status);
        task.setOwner(this.owner);
    }
}

