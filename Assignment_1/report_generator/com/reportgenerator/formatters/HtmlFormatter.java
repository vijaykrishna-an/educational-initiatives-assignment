package com.reportgenerator.formatters;

import java.util.Map;

/**
 * A Concrete Implementor.
 * Formats the report data into an HTML string.
 */
public class HtmlFormatter implements ReportFormatter {
    @Override
    public String format(String title, Map<String, String> data) {
        StringBuilder builder = new StringBuilder();
        builder.append("<!DOCTYPE html>\n");
        builder.append("<html>\n<head><title>").append(title).append("</title></head>\n<body>\n");
        builder.append("  <h1>").append(title).append("</h1>\n");
        builder.append("  <ul>\n");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            builder.append("    <li><b>").append(entry.getKey()).append(":</b> ").append(entry.getValue()).append("</li>\n");
        }
        builder.append("  </ul>\n");
        builder.append("</body>\n</html>");
        return builder.toString();
    }
}
