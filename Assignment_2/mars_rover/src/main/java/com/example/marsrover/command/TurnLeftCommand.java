package com.example.marsrover.command;

import com.example.marsrover.domain.Rover;

public class TurnLeftCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}