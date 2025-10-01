package com.reportgenerator.reports;

import com.reportgenerator.formatters.ReportFormatter;

/**
 * The Abstraction.
 * It holds a reference to an Implementor object (formatter). It defines the high-level
 * logic, delegating the actual formatting work to the implementor.
 */
public abstract class Report {
    // This is the "bridge" to the implementation.
    protected final ReportFormatter formatter;

    protected Report(ReportFormatter formatter) {
        this.formatter = formatter;
    }

    /**
     * A high-level operation that relies on the implementation.
     * The Abstraction and Implementor can vary independently.
     * @return A fully formatted report string.
     */
    public abstract String generate();
}
