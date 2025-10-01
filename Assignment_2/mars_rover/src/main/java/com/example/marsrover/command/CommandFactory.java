package com.example.marsrover.command;

public class CommandFactory {

    public static Command getCommand(char c) {
        return switch (c) {
            case 'M' -> new MoveForwardCommand();
            case 'L' -> new TurnLeftCommand();
            case 'R' -> new TurnRightCommand();
            default -> throw new IllegalArgumentException("Invalid command: " + c);
        };
    }
}