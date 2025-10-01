package com.reportgenerator.reports;

import com.reportgenerator.formatters.ReportFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Another Refined Abstraction.
 * Provides a different type of report.
 */
public class PerformanceReport extends Report {
    public PerformanceReport(ReportFormatter formatter) {
        super(formatter);
    }

    @Override
    public String generate() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("Top Performer", "Alice Johnson");
        data.put("Team Average Score", "92.5%");
        data.put("Areas for Improvement", "Client Follow-up Time");

        return formatter.format("Employee Performance Review", data);
    }
}
