package com.example.marsrover.domain;

import java.util.Objects;

public class Position {

    private final int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position move(int dx, int dy) {
        return new Position(x + dx, y + dy);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position p)) {
            return false;
        }
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}