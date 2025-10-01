package patterns.observer;

import core.Task;
import core.User;

/*
 * A concrete observer that notifies a user about a task update.
 */
public class UserObserver implements TaskObserver {
    private final User user;

    public UserObserver(User user) {
        this.user = user;
    }

    @Override
    public void update(Task task) {
        System.out.println(
            "--- NOTIFICATION for " + user.getUsername() + " ---" +
            "\n  Task '" + task.getDescription() + "' (ID: " + task.getId() + ")" +
            "\n  New Status: " + task.getStatus() +
            "\n----------------------------------------"
        );
    }
}