package core;

/*
 * Defines the possible lifecycle states of a Task in the workflow.
 */
public enum TaskStatus {
    DRAFT,
    IN_PROGRESS,
    PENDING_MANAGER_APPROVAL,
    PENDING_SENIOR_APPROVAL,
    NEEDS_REWORK,
    APPROVED,
    REJECTED
}