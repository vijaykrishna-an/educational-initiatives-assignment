package com.example.marsrover.domain.direction;

import com.example.marsrover.domain.Position;

public interface Direction {

    Direction turnLeft();

    Direction turnRight();

    Position move(Position p);

    String getName();
}