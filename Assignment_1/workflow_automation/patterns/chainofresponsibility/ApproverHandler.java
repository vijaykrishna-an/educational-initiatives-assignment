package patterns.chainofresponsibility;

/*
 * The abstract handler that defines the contract for the chain.
 * The process method now returns a boolean to indicate if the state was changed.
 */
public abstract class ApproverHandler {
    protected ApproverHandler nextHandler;

    public void setNext(ApproverHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean process(ApprovalRequest request);
}