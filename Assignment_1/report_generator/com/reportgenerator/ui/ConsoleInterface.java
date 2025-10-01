package com.reportgenerator.ui;

import com.reportgenerator.formatters.HtmlFormatter;
import com.reportgenerator.formatters.PlainTextFormatter;
import com.reportgenerator.formatters.ReportFormatter;
import com.reportgenerator.reports.PerformanceReport;
import com.reportgenerator.reports.Report;
import com.reportgenerator.reports.SalesReport;

import java.util.Scanner;

/**
 * Provides a user-driven console interface to demonstrate the Bridge pattern.
 * Users can mix and match any report with any format.
 */
public class ConsoleInterface {
    private final Scanner scanner;

    public ConsoleInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    handleGenerateReport();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println(); // Add a blank line for readability
        }
        System.out.println("Exiting Report Generator. Goodbye!");
    }

    private void displayMainMenu() {
        System.out.println("===== Report Generator =====");
        System.out.println("1. Generate a New Report");
        System.out.println("0. Exit");
        System.out.println("============================");
    }

    private void handleGenerateReport() {
        // Step 1: Choose the abstraction (the type of report)
        Report report = promptForReportType();
        if (report == null) return; // User cancelled

        // The report object itself doesn't know or care how it will be formatted.
        // It simply contains the logic to gather its own data.

        // Step 2: Display the generated report. The generate() method will use the bridge.
        System.out.println("\n--- Generated Report ---");
        System.out.println(report.generate());
        System.out.println("------------------------");
    }

    /**
     * Prompts the user to select a report type AND a format type,
     * then uses the Bridge pattern to construct the correct object.
     * @return A fully configured Report object, or null if the user cancels.
     */
    private Report promptForReportType() {
        System.out.println("\n--- Select Report Type ---");
        System.out.println("1. Sales Report");
        System.out.println("2. Employee Performance Report");
        System.out.println("0. Cancel");
        System.out.print("Choose report type: ");
        String reportChoice = scanner.nextLine().trim();

        // Step 2: Choose the implementation (the format)
        ReportFormatter formatter = null;
        if (!reportChoice.equals("0")) {
            formatter = promptForFormatType();
            if (formatter == null) return null; // User cancelled
        }

        // Step 3: Bridge the selected Abstraction with the selected Implementation
        switch (reportChoice) {
            case "1":
                return new SalesReport(formatter);
            case "2":
                return new PerformanceReport(formatter);
            case "0":
                return null;
            default:
                System.out.println("Invalid report type.");
                return null;
        }
    }

    /**
     * Prompts the user to select an output format.
     * @return A ReportFormatter instance, or null if the user cancels.
     */
    private ReportFormatter promptForFormatType() {
        System.out.println("\n--- Select Export Format ---");
        System.out.println("1. Plain Text");
        System.out.println("2. HTML");
        System.out.println("0. Cancel");
        System.out.print("Choose format type: ");
        String formatChoice = scanner.nextLine().trim();

        switch (formatChoice) {
            case "1":
                return new PlainTextFormatter();
            case "2":
                return new HtmlFormatter();
            case "0":
            default:
                System.out.println("Invalid format type or action cancelled.");
                return null;
        }
    }
}
