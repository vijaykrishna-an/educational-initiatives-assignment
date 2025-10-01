package patterns.chainofresponsibility;

import core.TaskStatus;

/**
 * First handler: Responsible for transitioning a task from DRAFT or NEEDS_REWORK to IN_PROGRESS.
 */
public class StartTaskHandler extends TaskProcessorHandler {
    @Override
    public void handleRequest(TaskActionRequest request) {
        if (request.getTask().getStatus() == TaskStatus.DRAFT || request.getTask().getStatus() == TaskStatus.NEEDS_REWORK) {
            System.out.println("-> [CoR] StartTaskHandler: Changing status from " + request.getTask().getStatus() + " to IN_PROGRESS.");
            request.getTask().setStatus(TaskStatus.IN_PROGRESS);
        }
        // Always pass to the next handler to continue the state transition
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
