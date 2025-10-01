package services;

import core.Task;
import core.User;
import patterns.observer.EventManager;
import patterns.observer.UserObserver;

/*
 * Manages notifications using the EventManager.
 */
public class NotificationService {
    private final EventManager eventManager;

    public NotificationService(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * Notifies ALL involved users of an update to the task.
     * @param task The task that was updated.
     */
    public void notifyTaskUpdate(Task task) {
        String eventTopic = "TASK_UPDATE:" + task.getId();
        eventManager.notify(eventTopic, task);
    }
    
    /**
     * Subscribes a user to a task's updates.
     * This is now primarily used to add the initial creator.
     */
    public void subscribeUserToTask(User user, Task task) {
        String eventTopic = "TASK_UPDATE:" + task.getId();
        eventManager.subscribe(eventTopic, new UserObserver(user));
        System.out.println("User '" + user.getUsername() + "' is now subscribed to task " + task.getId());
    }
}

