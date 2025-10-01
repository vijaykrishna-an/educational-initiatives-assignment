package patterns.chainofresponsibility;

import core.TaskStatus;
import core.UserRole;

/**
 * Final handler: Provides the ultimate approval for tasks pending a Senior's review.
 */
public class SeniorApprovalHandler extends TaskProcessorHandler {
    @Override
    public void handleRequest(TaskActionRequest request) {
        if (request.getTask().getStatus() == TaskStatus.PENDING_SENIOR_APPROVAL) {
            if (request.getUser().getRole().level >= UserRole.SENIOR.level) {
                System.out.println("-> [CoR] SeniorApprovalHandler: Processing task...");
                System.out.println("-> [CoR] ...Senior provides final approval.");
                request.getTask().setStatus(TaskStatus.APPROVED);
            }
        }
        // This is the end of the line, no nextHandler call.
    }
}
