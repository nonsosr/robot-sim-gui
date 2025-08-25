package src.RobotSim;

import java.util.Scanner;
import java.io.*;

public class RobotInterface {
    private Scanner s;
    private RobotArena myArena;

    public RobotInterface() {
        s = new Scanner(System.in);
        myArena = new RobotArena(20, 10);

        char ch;
        do {
            System.out.print("Enter (A)dd Robot, get (I)nformation, (D)isplay, (M)ove Robots, (S)imulate, (N)ew Arena, save (F)ile, (L)oad file, or e(X)it > ");
            ch = s.next().toUpperCase().charAt(0);
            switch (ch) {
                case 'A': myArena.addRobot();
                break;
                case 'I': System.out.println(myArena);
                break;
                case 'D': doDisplay();
                break;
                case 'M': simulate(1);
                break;
                case 'S': simulate(10);
                break;
                case 'N': createNewArena();
                break;
                case 'F': saveArenaToFile();
                break;
                case 'L': loadArenaFromFile();
                break;
                case 'X': System.out.println("Exiting...");
                break;
                default: System.out.println("Invalid option!");
            }
        } while (ch != 'X');
    }

    private void doDisplay() {
        int width = myArena.getWidth();
        int height = myArena.getHeight();
        ConsoleCanvas canvas = new ConsoleCanvas(width, height, "30806962");
        myArena.showRobots(canvas);
        System.out.println(canvas.toString());
    }

    private void simulate(int nTimes) {
        for (int i = 0; i < nTimes; i++) {
            myArena.moveAllRobots();
            doDisplay();
            try { Thread.sleep(200); } catch (InterruptedException e) { }
        }
    }

    private void createNewArena() {
        System.out.print("Enter the new arena width: ");
        int width = s.nextInt();
        System.out.print("Enter the new arena height: ");
        int height = s.nextInt();
        myArena = new RobotArena(width, height);
    }

    private void saveArenaToFile() {
        try (PrintWriter writer = new PrintWriter("arena.txt")) {
            writer.print(myArena);
            System.out.println("Arena saved to arena.txt.");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    private void loadArenaFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("arena.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            myArena = RobotArena.fromString(sb.toString());
            System.out.println("Arena loaded.");
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RobotInterface();
    }
}
