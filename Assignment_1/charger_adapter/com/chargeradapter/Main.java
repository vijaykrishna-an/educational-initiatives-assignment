package com.chargeradapter;

import com.chargeradapter.ui.ConsoleInterface;

/**
 * The entry point for the Charger Adapter application.
 * Its sole responsibility is to launch the user interface.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleInterface console = new ConsoleInterface();
        console.start();
    }
}

