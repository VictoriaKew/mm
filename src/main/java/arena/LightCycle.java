package main.java.arena;

import java.util.LinkedList;
import java.util.List;

public class LightCycle {
    // Enum for directions
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private int x, y; 
    private Direction direction;
    private double lives;
    private final List<int[]> jetwall; 
    
    // CONSTANT: Defined grid size to prevent magic numbers
    private static final int GRID_SIZE = 40; 

    // Constructor
    public LightCycle(int startX, int startY, Direction startDir) {
        // We call the reset method here to avoid writing duplicate code
        this.jetwall = new LinkedList<>();
        reset(startX, startY, startDir);
        this.lives = 3.0; 
    }

    // --- Movement and Wall Logic ---

    public void move() {
        // 1. Calculate the POTENTIAL next position
        int nextX = x;
        int nextY = y;

        switch (direction) {
            case UP:    nextY--; break;
            case DOWN:  nextY++; break;
            case LEFT:  nextX--; break;
            case RIGHT: nextX++; break;
        }

        // 2. CHECK: Did we hit a wall? (Boundary Check)
        if (nextX < 0 || nextX >= GRID_SIZE || nextY < 0 || nextY >= GRID_SIZE) {
            loseHalfLife();
            System.out.println("Crashed into a Wall!");
            return; // Stop moving, we crashed
        }

        // 3. CHECK: Did we hit our own tail? (Self-Collision Check)
        if (isCoordinateVisited(nextX, nextY)) {
            loseHalfLife();
            System.out.println("Crashed into self!");
            return; // Stop moving
        }

        // 4. If safe, commit the move
        x = nextX;
        y = nextY;
        jetwall.add(new int[]{x, y});
    }

    // Helper to check if a coordinate exists in our trail
    // (Java arrays don't support .contains() nicely, so we loop manually)
    private boolean isCoordinateVisited(int targetX, int targetY) {
        for (int[] pos : jetwall) {
            if (pos[0] == targetX && pos[1] == targetY) {
                return true;
            }
        }
        return false;
    }

    // Set a new direction, preventing immediate 180-degree turns
    public void setDirection(Direction newDir) {
        if ((direction == Direction.UP && newDir != Direction.DOWN) ||
            (direction == Direction.DOWN && newDir != Direction.UP) ||
            (direction == Direction.LEFT && newDir != Direction.RIGHT) ||
            (direction == Direction.RIGHT && newDir != Direction.LEFT)) {
            this.direction = newDir;
        }
    }

    // --- Game State Management ---

    // Reset position and trail (used after a crash or new round)
    public void reset(int startX, int startY, Direction startDir) {
        this.x = startX;
        this.y = startY;
        this.direction = startDir;
        this.jetwall.clear();
        this.jetwall.add(new int[]{startX, startY});
    }

    public void loseHalfLife() {
        this.lives -= 0.5;
        if (this.lives < 0) this.lives = 0;
    }

    public void loseAllLives() {
        this.lives = 0;
    }

    // --- Getters ---

    public int getX() { return x; }
    public int getY() { return y; }
    public Direction getDirection() { return direction; }
    public double getLives() { return lives; }
    public List<int[]> getJetwall() { return jetwall; }
    public boolean isAlive() { return lives > 0; }
}