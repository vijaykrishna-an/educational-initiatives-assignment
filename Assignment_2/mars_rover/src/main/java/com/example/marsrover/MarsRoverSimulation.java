package com.example.marsrover;

import com.example.marsrover.command.Command;
import com.example.marsrover.command.CommandFactory;
import com.example.marsrover.domain.Grid;
import com.example.marsrover.domain.Position;
import com.example.marsrover.domain.Rover;
import com.example.marsrover.domain.direction.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MarsRoverSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to the Mars Rover Console Simulation ---");

        // 1. Setup the Grid
        Grid grid = setupGrid(scanner);

        // 2. Setup the Rover
        Rover rover = setupRover(scanner, grid);
        System.out.println("\n-------------------------------------------");
        System.out.println("Rover successfully deployed!");
        System.out.println(rover.getStatus());
        System.out.println("-------------------------------------------");


        // 3. Start Command Loop
        while (true) {
            System.out.print("Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                continue;
            }

            if ("Q".equals(input)) {
                System.out.println("Simulation ended. Shutting down rover.");
                break;
            }

            char commandChar = input.charAt(0);
            try {
                Command command = CommandFactory.getCommand(commandChar);
                command.execute(rover);
                System.out.println("Status: " + rover.getStatus());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Invalid command '" + commandChar + "'. Please use M, L, or R.");
            }
        }

        scanner.close();
    }

    private static Grid setupGrid(Scanner scanner) {
        int width, height;
        while (true) {
            try {
                System.out.print("Enter grid size (width height): ");
                String[] dimensions = scanner.nextLine().trim().split("\\s+");
                width = Integer.parseInt(dimensions[0]);
                height = Integer.parseInt(dimensions[1]);
                if (width > 0 && height > 0) {
                    break;
                }
                System.out.println("Error: Width and height must be positive numbers.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers separated by a space (e.g., '10 10').");
            }
        }

        Set<Position> obstacles = new HashSet<>();
        System.out.println("Enter obstacle coordinates (x y). Type 'done' when finished.");
        while (true) {
            System.out.print("Obstacle: ");
            String input = scanner.nextLine().trim();
            if ("done".equalsIgnoreCase(input)) {
                break;
            }
            try {
                String[] coords = input.split("\\s+");
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);

                if (x >= 0 && x < width && y >= 0 && y < height) {
                    obstacles.add(new Position(x, y));
                    System.out.println("Added obstacle at (" + x + ", " + y + ")");
                } else {
                    System.out.println("Error: Obstacle is outside the grid boundaries. Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers separated by a space (e.g., '2 3').");
            }
        }
        return new Grid(width, height, obstacles);
    }

    private static Rover setupRover(Scanner scanner, Grid grid) {
        Position startPosition;
        Direction startDirection;

        while (true) {
            try {
                System.out.print("Enter Rover's starting position (x y): ");
                String[] posCoords = scanner.nextLine().trim().split("\\s+");
                int x = Integer.parseInt(posCoords[0]);
                int y = Integer.parseInt(posCoords[1]);
                startPosition = new Position(x, y);

                if (!grid.isInside(startPosition)) {
                    System.out.println("Error: Starting position is outside the grid. Please try again.");
                    continue;
                }
                if (grid.isObstacle(startPosition)) {
                    System.out.println("Error: Starting position is on an obstacle. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers separated by a space (e.g., '0 0').");
            }
        }

        while (true) {
            try {
                System.out.print("Enter Rover's starting direction (N, S, E, W): ");
                char dirChar = scanner.nextLine().trim().toUpperCase().charAt(0);
                startDirection = getDirectionFromChar(dirChar);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter N, S, E, or W.");
            }
        }
        return new Rover(startPosition, startDirection, grid);
    }

    private static Direction getDirectionFromChar(char d) {
        return switch (d) {
            case 'N' -> new North();
            case 'S' -> new South();
            case 'E' -> new East();
            case 'W' -> new West();
            default -> throw new IllegalArgumentException("Invalid direction character");
        };
    }
}