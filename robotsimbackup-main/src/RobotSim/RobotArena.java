package src.RobotSim;

import java.util.ArrayList;
import java.util.Random;

public class RobotArena {
    private final int width;
    private final int height;
    private final ArrayList<Robot> robots;
    private final Random randomGenerator;

    public RobotArena(int width, int height) {
        this.width = width;
        this.height = height;
        this.robots = new ArrayList<>();
        this.randomGenerator = new Random();
    }

    public void addRobot() {
        int randomX, randomY;
        do {
            randomX = randomGenerator.nextInt(width);
            randomY = randomGenerator.nextInt(height);
        } while (getRobotAt(randomX, randomY) != null);

        Direction randomDirection = Direction.getRandomDirection();
        Robot newRobot = new Robot(randomX, randomY, randomDirection);
        robots.add(newRobot);
    }

    public Robot getRobotAt(int x, int y) {
        for (Robot robot : robots) {
            if (robot.isHere(x, y)) return robot;
        }
        return null;
    }

    public void showRobots(ConsoleCanvas c) {
        for (Robot robot : robots) {
            robot.displayRobot(c);
        }
    }

    public boolean canMoveHere(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && getRobotAt(x, y) == null;
    }

    public void moveAllRobots() {
        for (Robot robot : robots) {
            robot.tryToMove(this);
        }
    }

    // Getter methods for arena dimensions
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static RobotArena fromString(String arenaData) throws Exception {
        String[] lines = arenaData.split("\\n");

        // Validate the first line for arena dimensions
        if (!lines[0].matches("Arena \\d+ by \\d+")) {
            throw new Exception("Invalid arena format: " + lines[0]);
        }

        // Parse arena dimensions
        String[] dimensions = lines[0].split(" ");
        int width = Integer.parseInt(dimensions[1]);
        int height = Integer.parseInt(dimensions[3]);
        RobotArena arena = new RobotArena(width, height);

        // Process robot lines
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim(); // Remove leading/trailing whitespace
            System.out.println("Processing line: " + line); // Debugging line

            // Check if the line matches the expected robot format
            if (line.matches("Robot \\d+ is at \\d+ \\d+ facing (NORTH|EAST|SOUTH|WEST)")) {
                try {
                    // Split the line and extract data
                    String[] parts = line.split("\\s+");
                    System.out.println("Split parts: " + java.util.Arrays.toString(parts)); // Debugging

                    // Validate part count (adjusted for correct indices)
                    if (parts.length < 8) {
                        throw new Exception("Line does not contain enough parts: " + line);
                    }

                    // Robot ID
                    int x = Integer.parseInt(parts[4]);      // X-coordinate
                    int y = Integer.parseInt(parts[5]);      // Y-coordinate
                    Direction direction = Direction.valueOf(parts[7].toUpperCase()); // Direction

                    // Add robot to the arena
                    arena.robots.add(new Robot(x, y, direction));
                } catch (IllegalArgumentException e) {
                    throw new Exception("Error parsing robot data: " + line, e);
                }
            } else {
                throw new Exception("Unexpected format in robot line: " + line);
            }
        }
        return arena;
    }




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Arena " + width + " by " + height + "\n");
        for (Robot robot : robots) {
            sb.append(robot.toString()).append("\n");
        }
        return sb.toString();
    }
}
