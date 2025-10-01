package patterns.observer;

import core.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * A concrete observer that logs any task change to the console.
 */
public class LoggingObserver implements TaskObserver {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void update(Task task) {
        System.out.println(
            "[LOG - " + LocalDateTime.now().format(formatter) + "]: " +
            "Task " + task.getId() + " was updated. New status: " + task.getStatus()
        );
    }
}
