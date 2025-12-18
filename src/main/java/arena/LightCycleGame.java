package main.java.arena;

import java.util.Scanner;
import main.java.arena.Arena.ArenaType;
import main.java.arena.LightCycle.Direction;

public class LightCycleGame {
    // FIX 1: Distinct Constants to avoid logic errors
    private static final int TILE_OUT_OF_BOUNDS = -1; 
    private static final int TILE_EMPTY = 0;          
    private static final int TILE_STATIC_WALL = 1;    

    private final Arena arena;
    private final LightCycle player1;
    private boolean gameOver = false;
    private Scanner scanner;

    public LightCycleGame(ArenaType type, boolean isOpenType) {
        // 1. Initialize Arena
        this.arena = new Arena(type, isOpenType);
        
        // 2. Initialize Light Cycle (Starting near the center)
        int startPos = arena.getSize() / 4;
        this.player1 = new LightCycle(startPos, startPos, Direction.RIGHT);
        
        // 3. Setup Input
        this.scanner = new Scanner(System.in);
        
        // Mark the starting position as a jetwall so the tail starts immediately
            arena.setCell(startPos, startPos, Arena.TILE_PLAYER_1_JETWALL);
    }

    // --- Input Handling ---
    
    private void handleInput() {
        // Prompt for user input
        System.out.print("Move (W/A/S/D) [Enter]: ");
        
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().toUpperCase().trim();
            if (!input.isEmpty()) {
                char key = input.charAt(0);
                switch (key) {
                    case 'W': player1.setDirection(Direction.UP); break;
                    case 'S': player1.setDirection(Direction.DOWN); break;
                    case 'A': player1.setDirection(Direction.LEFT); break;
                    case 'D': player1.setDirection(Direction.RIGHT); break;
                    case 'Q': gameOver = true; break; 
                }
            }
        }
    }

    // --- Game Logic ---
    
    private void update() {
        if (gameOver) return;

        // 1. Move the cycle
        // Note: This updates the player's internal X/Y coordinates
        player1.move();

        int newX = player1.getX();
        int newY = player1.getY();
        
        // 2. Collision Check
        // We assume arena.getCell() returns TILE_OUT_OF_BOUNDS (-1) if x/y are invalid
        int cellValue = arena.getCell(newX, newY);
        
        boolean crashed = false;

        // CHECK 1: Out of Bounds
        if (cellValue == TILE_OUT_OF_BOUNDS) { 
            if (arena.isOpenType()) {
                player1.loseAllLives();
                System.out.println(">>> FELL OFF THE GRID! Instant Derez.");
                crashed = true;
            } else {
                player1.loseHalfLife();
                System.out.println(">>> HIT BOUNDARY WALL! (-0.5 lives)");
                crashed = true;
            }
        } 
        // CHECK 2: Hitting Obstacles (Static walls or Jetwalls)
        else if (cellValue == TILE_STATIC_WALL || cellValue == Arena.TILE_PLAYER_1_JETWALL) { 
            player1.loseHalfLife();
            System.out.println(">>> CRASH! You hit an obstacle. (-0.5 lives)");
            crashed = true;
        }

        // 3. Update Game State based on collision
        if (!player1.isAlive()) {
            gameOver = true;
            System.out.println("GAME OVER! Your cycle has been derezzed.");
            return;
        }

        // FIX 2: Only place a trail if we landed on an EMPTY spot.
        // If we crashed into a wall, we do NOT want to replace the wall with our trail.
        if (!crashed && cellValue == TILE_EMPTY) {
            arena.setCell(newX, newY, Arena.TILE_PLAYER_1_JETWALL);
        }
    }

    // --- Rendering ---

    private void render() {
        // "Clear" screen by printing newlines
        System.out.print("\n\n\n\n\n\n"); 
        
        System.out.println("--- THE GRID (" + arena.getSize() + "x" + arena.getSize() + ") ---");
        System.out.println("Lives: " + player1.getLives());
        
        for (int y = 0; y < arena.getSize(); y++) {
            for (int x = 0; x < arena.getSize(); x++) {
                
                // Prioritize drawing the Player Head (@)
                if (x == player1.getX() && y == player1.getY()) {
                    System.out.print("@ "); 
                } 
                else {
                    int cell = arena.getCell(x, y);
                    char symbol = '.'; // Default for empty floor
                    
                    if (cell == TILE_STATIC_WALL) {
                        symbol = '#'; 
                    } else if (cell == Arena.TILE_PLAYER_1_JETWALL) {
                        symbol = '*'; 
                    }
                    System.out.print(symbol + " ");
                }
            }
            System.out.println();
        }
    }

    // --- Game Loop ---

    public void startGame() {
        System.out.println("Welcome to the Light Cycle Grid!");
        render(); // Render initial state

        while (!gameOver) {
            handleInput(); // Wait for user to type W/A/S/D and press Enter
            update();      // Move player and check collisions
            render();      // Draw the result
        }
        
        System.out.println("\nFinal Result: " + (player1.getLives() > 0 ? "SURVIVOR" : "DEREZZED"));
        scanner.close();
    }
}