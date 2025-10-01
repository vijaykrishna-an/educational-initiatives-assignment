package patterns.chainofresponsibility;

import core.TaskStatus;
import core.UserRole;
import core.PriorityLevel;

/**
 * Third handler: Approves tasks if the priority is low enough, otherwise escalates.
 */
public class ManagerApprovalHandler extends TaskProcessorHandler {
    @Override
    public void handleRequest(TaskActionRequest request) {
        if (request.getTask().getStatus() == TaskStatus.PENDING_MANAGER_APPROVAL) {
            if (request.getUser().getRole().level >= UserRole.MANAGER.level) {
                 System.out.println("-> [CoR] ManagerApprovalHandler: Processing task...");
                // If priority is HIGH, escalate to Senior. Otherwise, a Manager can approve it.
                if (request.getTask().getPriority() == PriorityLevel.HIGH) {
                    System.out.println("-> [CoR] ...Task requires Senior approval. Escalating.");
                    request.getTask().setStatus(TaskStatus.PENDING_SENIOR_APPROVAL);
                } else {
                    System.out.println("-> [CoR] ...Manager provides final approval.");
                    request.getTask().setStatus(TaskStatus.APPROVED);
                }
            }
        }
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}