package com.reportgenerator;

import com.reportgenerator.ui.ConsoleInterface;

/**
 * The entry point for the Report Generator application.
 * Its sole responsibility is to launch the user interface.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleInterface console = new ConsoleInterface();
        console.start();
    }
}

