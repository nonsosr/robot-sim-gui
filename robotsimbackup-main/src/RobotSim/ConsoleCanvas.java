package RobotSim;

public class ConsoleCanvas {
    private char[][] canvas; // 2D array representing the arena
    private int width, height; // Dimensions of the arena with border included
    private String studentNumber; // Student number for display in the top border

    // Constructor
    public ConsoleCanvas(int arenaWidth, int arenaHeight, String studentNumber) {
        this.width = arenaWidth + 2; // Include border on left and right
        this.height = arenaHeight + 2; // Include border on top and bottom
        this.studentNumber = studentNumber;

        // Initialize and fill the canvas
        canvas = new char[height][width];

        // Set up borders and fill with spaces inside
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
                    canvas[row][col] = '#'; // Border character
                } else {
                    canvas[row][col] = ' '; // Empty space within the arena
                }
            }
        }

        // Place the student number in the top border's center
        int startPos = (width - studentNumber.length()) / 2;
        for (int i = 0; i < studentNumber.length(); i++) {
            canvas[0][startPos + i] = studentNumber.charAt(i);
        }
    }

    public void showIt(int x, int y, char symbol) {
        if (x >= 1 && x <= width - 2 && y >= 1 && y <= height - 2) {
            canvas[y + 1][x + 1] = symbol; // Adjust for 1-based border offset
        }
    }

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                display.append(canvas[row][col]);
            }
            display.append('\n');
        }
        return display.toString();
    }
}
