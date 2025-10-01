package com.example.marsrover.domain.direction;

import com.example.marsrover.domain.Position;

public class South implements Direction {

    @Override
    public Direction turnLeft() {
        return new East();
    }

    @Override
    public Direction turnRight() {
        return new West();
    }

    @Override
    public Position move(Position p) {
        return p.move(1,0);
    }

    @Override
    public String getName() {
        return "South";
    }
}