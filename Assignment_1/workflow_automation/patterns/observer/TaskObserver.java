package patterns.observer;


import core.Task;

/*
 * The Observer interface. Observers receive the full Task object for context.
 */
public interface TaskObserver {
    void update(Task task);
}
