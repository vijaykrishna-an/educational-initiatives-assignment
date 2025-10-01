package com.reportgenerator.formatters;

import java.util.Map;

/**
 * Another Concrete Implementor.
 * Formats the report data into a simple plain text string.
 */
public class PlainTextFormatter implements ReportFormatter {
    @Override
    public String format(String title, Map<String, String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("***** ").append(title.toUpperCase()).append(" *****\n");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append(String.format("%-20s: %s\n", entry.getKey(), entry.getValue()));
        }
        builder.append("************************************\n");
        return builder.toString();
    }
}
