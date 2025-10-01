package com.example.marsrover.command;

import com.example.marsrover.domain.Rover;
import com.example.marsrover.domain.MoveAttemptResult;

public class MoveForwardCommand implements Command {

    @Override
    public void execute(Rover rover) {
        MoveAttemptResult result = rover.moveForward();
        if (result == MoveAttemptResult.BLOCKED_BY_OBSTACLE) {
            System.out.println("Action blocked: Obstacle detected ahead.");
        } else if (result == MoveAttemptResult.OUTSIDE_GRID_BOUNDARY) {
            System.out.println("Action blocked: Movement would go outside grid boundaries.");
        }
    }
}