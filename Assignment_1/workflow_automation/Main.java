import patterns.observer.EventManager;
import patterns.observer.LoggingObserver;
import services.AuthenticationService;
import services.NotificationService;
import services.WorkflowService;
import ui.ConsoleInterface;

public class Main {
    public static void main(String[] args) {
        // --- Setup Dependencies ---
        // 1. Create the central EventManager
        EventManager eventManager = new EventManager();

        // 2. Create services that depend on the event manager
        AuthenticationService authService = new AuthenticationService();
        NotificationService notificationService = new NotificationService(eventManager);
        WorkflowService workflowService = new WorkflowService(notificationService);

        authService.setWorkflowService(workflowService);

        // 3. Setup a global logger to see all task updates
        // This is a powerful feature: a single line to log every event.
        // It demonstrates the power of the decoupled observer pattern.
        eventManager.subscribe("TASK_UPDATE:.*", new LoggingObserver());

        System.out.println("Workflow Automation System Initialized.");

        // 4. Create the ConsoleInterface and pass all the services to it
        ConsoleInterface ui = new ConsoleInterface(authService, workflowService, notificationService);
        
        // 5. Start the application's main loop
        ui.start();
    }
}