package src.RobotSim;

public class Robot {
    private int x, y;
    private final int RobotID;
    private static int idCounter = 0; // Static counter for unique ID assignment
    private Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.RobotID = idCounter++; // Assign unique ID and increment the counter
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Robot "+ RobotID + " is at " + x + " " + y + " facing " + direction;
    }

    public boolean isHere(int sx, int sy) {
        return this.x == sx && this.y == sy;
    }

    public void displayRobot(ConsoleCanvas c) {
        c.showIt(x, y, 'R');
    }

    public void tryToMove(RobotArena arena) {
        int newX = x, newY = y;

        switch (direction) {
            case NORTH: newY--; break;
            case EAST: newX++; break;
            case SOUTH: newY++; break;
            case WEST: newX--; break;
        }

        if (arena.canMoveHere(newX, newY)) {
            x = newX;
            y = newY;
        } else {
            direction = direction.nextDirection();
        }
    }
}
