package com.example.marsrover.domain.direction;

import com.example.marsrover.domain.Position;

public class East implements Direction {

    @Override
    public Direction turnLeft() {
        return new North();
    }

    @Override
    public Direction turnRight() {
        return new South();
    }

    @Override
    public Position move(Position p) {
        return p.move(0,1);
    }

    @Override
    public String getName() {
        return "East";
    }
}