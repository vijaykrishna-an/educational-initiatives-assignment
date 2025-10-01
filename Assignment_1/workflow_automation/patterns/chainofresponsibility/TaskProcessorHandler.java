package patterns.chainofresponsibility;

/**
 * The abstract handler for the new, more powerful state-transition chain.
 */
public abstract class TaskProcessorHandler {
    protected TaskProcessorHandler nextHandler;

    public void setNext(TaskProcessorHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(TaskActionRequest request);
}