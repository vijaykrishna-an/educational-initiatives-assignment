package com.example.marsrover.command;

import com.example.marsrover.domain.Rover;

public class TurnRightCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}