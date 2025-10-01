Mars Rover Simulation

Overview

This is a Java-based console application that simulates the movement of a rover on a grid-based Martian surface. The application is fully interactive, allowing the user to define the grid size, place obstacles, and set the rover's initial position and orientation.

Once the simulation environment is configured, the user can issue a sequence of commands to control the rover's movement. The rover intelligently handles obstacle detection and boundary checks, providing clear feedback to the user if a command cannot be executed.

Design Patterns Used

The project is architected using several fundamental object-oriented design patterns to ensure the code is modular, flexible, and easy to maintain.

1. Strategy Pattern

   Purpose: To define a family of algorithms, encapsulate each one, and make them interchangeable.

   Implementation: The rover's direction is managed by the Direction interface, which acts as the Strategy. Concrete classes like North, South, East, and West implement this interface. The Rover class holds a Direction object and delegates the logic for moving and turning to it. This cleanly separates the rover's core logic from the specifics of how movement is calculated for each direction.

2. Command Pattern

   Purpose: To encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

   Implementation: The Command interface defines a simple execute method. Concrete classes like MoveForwardCommand, TurnLeftCommand, and TurnRightCommand implement this interface, each encapsulating a specific action to be performed on the Rover. The main application loop creates these command objects based on user input and executes them.

3. Factory Pattern (Simple Factory)

   Purpose: To create objects without exposing the instantiation logic to the client.

   Implementation: The CommandFactory class provides a static method getCommand(char c) that takes a character from the user input ('M', 'L', 'R') and returns the corresponding concrete Command object. This decouples the main application from the specific command classes.

Project Structure and Class Descriptions

The project is organized into logical packages to separate concerns.

    com.marsrover

        MarsRoverSimulation.java: The main entry point of the application. It contains the main method and is responsible for handling all user interaction, including setting up the grid, deploying the rover, and managing the command input loop.

    com.marsrover.domain (Core model classes)

        Position.java: An immutable value object representing a coordinate pair (x, y) on the grid.

        Grid.java: Represents the 2D surface. It stores the grid's dimensions and a set of obstacle Positions. It provides methods to check if a position is within its boundaries or if it's an obstacle.

        Rover.java: The central class of the simulation. It maintains its own state (current Position and Direction) and exposes methods to alter that state (moveForward, turnLeft, turnRight).

        MoveAttemptResult.java: An enum (SUCCESS, BLOCKED_BY_OBSTACLE, OUTSIDE_GRID_BOUNDARY) returned by the Rover.moveForward() method to provide clear, specific feedback on the outcome of a move command.

    com.marsrover.domain.direction (Strategy Pattern implementations)

        Direction.java: The strategy interface defining the contract for all directional behaviors (move, turnLeft, turnRight).

        North.java, South.java, East.java, West.java: Concrete strategy classes, each implementing the logic for movement and turning specific to that direction.

    com.marsrover.command (Command Pattern implementations)

        Command.java: The command interface, defining the execute(Rover rover) method.

        MoveForwardCommand.java, TurnLeftCommand.java, TurnRightCommand.java: Concrete command classes that call the corresponding methods on the Rover object when executed.

        CommandFactory.java: A simple factory class to create command objects from character inputs.

How to Compile and Run

Prerequisites

    Java Development Kit (JDK) 17 or later.

    Apache Maven 3.6 or later.

Compilation

    Open a terminal or command prompt.

    Navigate to the root directory of the project (where the pom.xml file is located).

    Run the following Maven command to compile the code and package it into a JAR file:
    Bash

    mvn clean package

    This will create a mars-rover-1.0-SNAPSHOT.jar file inside the target/ directory.

Running the Application

    From the same root directory, run the following command in your terminal:
    Bash

    java -cp target/mars-rover-1.0-SNAPSHOT.jar com.marsrover.MarsRoverSimulation

    The application will start, and you can begin interacting with it through the console.

Sample Interaction

Below is a sample session demonstrating how to set up the environment and control the rover.

```
--- Welcome to the Mars Rover Console Simulation ---
Enter grid size (width height): 5 5
Enter obstacle coordinates (x y). Type 'done' when finished.
Obstacle: 1 2
Added obstacle at (1, 2)
Obstacle: 3 3
Added obstacle at (3, 3)
Obstacle: done
Enter Rover's starting position (x y): 0 0
Enter Rover's starting direction (N, S, E, W): N

-------------------------------------------
Rover successfully deployed!
Rover at (0, 0) facing North
-------------------------------------------
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): M
Status: Rover at (0, 1) facing North
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): M
Status: Rover at (0, 2) facing North
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): R
Status: Rover at (0, 2) facing East
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): M
Action blocked: Obstacle detected ahead.
Status: Rover at (0, 2) facing East
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): L
Status: Rover at (0, 2) facing North
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): L
Status: Rover at (0, 2) facing West
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): M
Action blocked: Movement would go outside grid boundaries.
Status: Rover at (0, 2) facing West
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): X
Error: Invalid command 'X'. Please use M, L, or R.
Status: Rover at (0, 2) facing West
Enter command (M: Move, L: Turn Left, R: Turn Right, Q: Quit): Q
Simulation ended. Shutting down rover.
```

