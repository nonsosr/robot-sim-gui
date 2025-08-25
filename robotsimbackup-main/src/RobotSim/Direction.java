package RobotSim;

import java.util.Random;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    private static final Random RANDOM = new Random();

    public static Direction getRandomDirection() {
        Direction[] directions = values();
        return directions[RANDOM.nextInt(directions.length)];
    }

    public Direction nextDirection() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}
