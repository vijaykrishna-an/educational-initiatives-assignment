package patterns.chainofresponsibility;

import core.TaskStatus;

/**
 * Second handler: Transitions a task from IN_PROGRESS to PENDING_MANAGER_APPROVAL.
 */
public class SubmitTaskHandler extends TaskProcessorHandler {
    @Override
    public void handleRequest(TaskActionRequest request) {
        if (request.getTask().getStatus() == TaskStatus.IN_PROGRESS) {
            System.out.println("-> [CoR] SubmitTaskHandler: Changing status from IN_PROGRESS to PENDING_MANAGER_APPROVAL.");
            request.getTask().setStatus(TaskStatus.PENDING_MANAGER_APPROVAL);
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
