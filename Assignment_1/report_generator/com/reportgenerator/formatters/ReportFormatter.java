package com.reportgenerator.formatters;

import java.util.Map;

/**
 * The Implementor Interface.
 * It defines the interface for all concrete formatter classes. The Bridge pattern
 * allows this interface and its implementations to evolve independently of the reports.
 */
public interface ReportFormatter {
    String format(String title, Map<String, String> data);
}
