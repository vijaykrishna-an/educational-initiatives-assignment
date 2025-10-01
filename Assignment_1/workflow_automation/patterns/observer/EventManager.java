package patterns.observer;


import core.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A centralized, decoupled event dispatcher.
 * Tasks no longer manage observers themselves.
 */
public class EventManager {
    private final Map<String, List<TaskObserver>> listeners = new HashMap<>();

    public void subscribe(String eventTopic, TaskObserver listener) {
        listeners.computeIfAbsent(eventTopic, k -> new ArrayList<>()).add(listener);
    }

    public void unsubscribe(String eventTopic, TaskObserver listener) {
        if (listeners.containsKey(eventTopic)) {
            listeners.get(eventTopic).remove(listener);
        }
    }

    public void notify(String eventTopic, Task task) {
        if (listeners.containsKey(eventTopic)) {
            for (TaskObserver listener : new ArrayList<>(listeners.get(eventTopic))) {
                listener.update(task);
            }
        }
    }
}
