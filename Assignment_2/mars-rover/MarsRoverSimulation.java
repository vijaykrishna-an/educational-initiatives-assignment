
import java.util.*;

// ----------------- Position Class -----------------
class Position {

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

// ----------------- Direction Strategy -----------------
interface Direction {

    Direction turnLeft();

    Direction turnRight();

    Position move(Position p);

    String getName();
}

class North implements Direction {

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
        return p.move(0, 1);
    }

    @Override
    public String getName() {
        return "North";
    }
}

class South implements Direction {

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
        return p.move(0, -1);
    }

    @Override
    public String getName() {
        return "South";
    }
}

class East implements Direction {

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
        return p.move(1, 0);
    }

    @Override
    public String getName() {
        return "East";
    }
}

class West implements Direction {

    @Override
    public Direction turnLeft() {
        return new South();
    }

    @Override
    public Direction turnRight() {
        return new North();
    }

    @Override
    public Position move(Position p) {
        return p.move(-1, 0);
    }

    @Override
    public String getName() {
        return "West";
    }
}

// ----------------- Grid Class -----------------
class Grid {

    private final int width, height;
    private final Set<Position> obstacles;

    public Grid(int width, int height, Set<Position> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = obstacles;
    }

    public boolean isInside(Position p) {
        return p.getX() >= 0 && p.getX() < width && p.getY() >= 0 && p.getY() < height;
    }

    public boolean isObstacle(Position p) {
        return obstacles.contains(p);
    }
}

// ----------------- Rover Class -----------------
class Rover {

    private Position position;
    private Direction direction;
    private final Grid grid;

    public Rover(Position start, Direction dir, Grid grid) {
        this.position = start;
        this.direction = dir;
        this.grid = grid;
    }

    public void moveForward() {
        Position newPos = direction.move(position);
        if (grid.isInside(newPos) && !grid.isObstacle(newPos)) {
            position = newPos;
        }
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

// ----------------- Command Pattern -----------------
interface Command {

    void execute(Rover rover);
}

class MoveForwardCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.moveForward();
    }
}

class TurnLeftCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.turnLeft();
    }
}

class TurnRightCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.turnRight();
    }
}

// Factory for Commands
class CommandFactory {

    public static Command getCommand(char c) {
        return switch (c) {
            case 'M' -> new MoveForwardCommand();
            case 'L' -> new TurnLeftCommand();
            case 'R' -> new TurnRightCommand();
            default -> throw new IllegalArgumentException("Invalid command: " + c);
        };
    }
}

// ----------------- Main Simulation -----------------
public class MarsRoverSimulation {

    public static void main(String[] args) {
        // Define obstacles
        Set<Position> obstacles = new HashSet<>();
        obstacles.add(new Position(2, 2));
        obstacles.add(new Position(1, 3));

        // Create grid
        Grid grid = new Grid(5, 5, obstacles);

        // Initialize rover at (0,0) facing North
        Rover rover = new Rover(new Position(0, 0), new North(), grid);

        System.out.println(rover.getStatus());

        // Command sequence: Move, Move, Right, Move, Left, Move
        String commands = "MMRMLM";
        for (char c : commands.toCharArray()) {
            Command command = CommandFactory.getCommand(c);
            command.execute(rover);
            System.out.println(rover.getStatus());
        }
    }
}
