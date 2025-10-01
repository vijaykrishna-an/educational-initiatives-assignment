package com.example.marsrover.domain;

import com.example.marsrover.domain.direction.Direction;

public class Rover {

    private Position position;
    private Direction direction;
    private final Grid grid;

    public Rover(Position start, Direction dir, Grid grid) {
        this.position = start;
        this.direction = dir;
        this.grid = grid;
    }

    /**
     * Attempts to move the rover one step forward and reports the outcome.
     * The rover's position is only changed on a successful move.
     *
     * @return A MoveAttemptResult indicating success, obstacle blockage, or boundary blockage.
     */
    public MoveAttemptResult moveForward() {
        // Calculate the potential next position
        Position newPos = direction.move(position);

        // 1. First, check if the command would move the rover off the grid.
        if (!grid.isInside(newPos)) {
            return MoveAttemptResult.OUTSIDE_GRID_BOUNDARY;
        }

        // 2. Second, check if there is an obstacle in the path.
        if (grid.isObstacle(newPos)) {
            return MoveAttemptResult.BLOCKED_BY_OBSTACLE;
        }

        // 3. If all checks pass, execute the move and report success.
        this.position = newPos;
        return MoveAttemptResult.SUCCESS;
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public String getStatus() {
        return "Rover at " + position + " facing " + direction.getName();
    }
}