package com.example.marsrover.domain.direction;

import com.example.marsrover.domain.Position;

public class North implements Direction {

    @Override
    public Direction turnLeft() {
        return new West();
    }

    @Override
    public Direction turnRight() {
        return new East();
    }

    @Override
    public Position move(Position p) {
        return p.move(-1,0);
    }

    @Override
    public String getName() {
        return "North";
    }
}