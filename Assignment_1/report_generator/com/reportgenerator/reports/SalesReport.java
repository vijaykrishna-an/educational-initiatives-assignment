package com.reportgenerator.reports;

import com.reportgenerator.formatters.ReportFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A Refined Abstraction.
 * Provides a specific type of report.
 */
public class SalesReport extends Report {
    public SalesReport(ReportFormatter formatter) {
        super(formatter);
    }

    @Override
    public String generate() {
        // In a real app, this data would come from a database.
        Map<String, String> data = new LinkedHashMap<>();
        data.put("Total Sales", "$1,500,000");
        data.put("Units Sold", "12,345");
        data.put("Top Performing Region", "North America");

        // The report generates its data and then delegates formatting to the implementor.
        return formatter.format("Quarterly Sales Report", data);
    }
}
