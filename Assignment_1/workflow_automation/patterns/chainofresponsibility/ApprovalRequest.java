package patterns.chainofresponsibility;

import core.Task;
import core.User;

/*
 * An object that carries the context of the approval through the chain.
 */
public class ApprovalRequest {
    private final Task task;
    private final User approver;
    private String comments;

    public ApprovalRequest(Task task, User approver) {
        this.task = task;
        this.approver = approver;
    }

    public Task getTask() { return task; }
    public User getApprover() { return approver; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}
