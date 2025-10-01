package patterns.memento;

import core.Task;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * Manages the history of Mementos for each Task.
 */
public class MementoCaretaker {
    private final Map<String, Stack<TaskMemento>> history = new HashMap<>();

    public void saveState(Task task) {
        history.computeIfAbsent(task.getId(), k -> new Stack<>()).push(new TaskMemento(task));
    }

    public void undo(Task task) {
        String taskId = task.getId();
        if (history.containsKey(taskId) && !history.get(taskId).isEmpty()) {
            TaskMemento lastState = history.get(taskId).pop();
            lastState.restore(task);
        } else {
            System.out.println("No history available to undo for task: " + taskId);
        }
    }

    public void clearHistory(String taskId) {
        history.remove(taskId);
    }
}
