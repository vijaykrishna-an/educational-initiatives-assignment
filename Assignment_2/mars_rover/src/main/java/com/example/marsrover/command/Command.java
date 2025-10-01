package com.example.marsrover.command;

import com.example.marsrover.domain.Rover;

public interface Command {

    void execute(Rover rover);
}