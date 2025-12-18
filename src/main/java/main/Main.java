package main.java.main;

import java.util.Scanner;
import main.java.arena.Arena.ArenaType;
import main.java.arena.LightCycleGame;
import main.java.entities.characterMenu;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("      JAVA LIGHT CYCLE (TRON) GAME       ");
        System.out.println("=========================================");

        // 1. Select Arena Type
        // Matches the Enums defined in Arena.java 
        System.out.println("\nSelect Arena Map:");
        System.out.println("1. Predesigned A (Box Obstacle)");
        System.out.println("2. Predesigned B (Cross Obstacle)");
        System.out.println("3. Predesigned C (Complex)");
        System.out.println("4. Randomly Generated");
        System.out.print("Enter choice (1-4): ");
        
        int mapChoice = 0;
        if (scanner.hasNextInt()) {
            mapChoice = scanner.nextInt();
        } else {
            scanner.next(); // Clear invalid input
        }

        ArenaType selectedType;
        switch (mapChoice) {
            case 1:
                selectedType = ArenaType.PREDESIGNED_A;
                break;
            case 2:
                selectedType = ArenaType.PREDESIGNED_B;
                break;
            case 3:
                selectedType = ArenaType.PREDESIGNED_C;
                break;
            case 4:
                selectedType = ArenaType.RANDOMLY_GENERATED;
                break;
            default:
                System.out.println("Invalid selection. Defaulting to Predesigned A.");
                selectedType = ArenaType.PREDESIGNED_A;
        }

        // 2. Select Arena Mode (Open vs Closed)
        // This is passed to the Arena constructor to determine boundary walls [cite: 20]
        System.out.println("\nSelect Arena Mode:");
        System.out.println("1. Closed Arena (Solid walls on edges)");
        System.out.println("2. Open Arena (Wrap-around / Fall off)");
        System.out.print("Enter choice (1-2): ");

        int modeChoice = 0;
        if (scanner.hasNextInt()) {
            modeChoice = scanner.nextInt();
        }
        
        // If user chooses 2, isOpenType is true. Otherwise false.
        boolean isOpenType = (modeChoice == 2);

        System.out.println("\nInitializing Game...");

        // 3. Create and Start the Game
        // Uses the constructor defined in LightCycleGame.java 
        LightCycleGame game = new LightCycleGame(selectedType, isOpenType);
        
        // Starts the game loop defined in LightCycleGame.java 
        game.startGame(); 

        scanner.close();
        */
       characterMenu charMenu = new characterMenu();
       characterMenu.GameCharacter character = charMenu.runCharacterSelection(scanner);
       
       printArena();
    }

    public static void printArena(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("      JAVA LIGHT CYCLE (TRON) GAME       ");
        System.out.println("=========================================");

        // 1. Select Arena Type
        // Matches the Enums defined in Arena.java 
        System.out.println("\nSelect Arena Map:");
        System.out.println("1. Predesigned A (Box Obstacle)");
        System.out.println("2. Predesigned B (Cross Obstacle)");
        System.out.println("3. Predesigned C (Complex)");
        System.out.println("4. Randomly Generated");
        System.out.print("Enter choice (1-4): ");
        
        int mapChoice = 0;
        if (scanner.hasNextInt()) {
            mapChoice = scanner.nextInt();
        } else {
            scanner.next(); // Clear invalid input
        }

        ArenaType selectedType;
        switch (mapChoice) {
            case 1:
                selectedType = ArenaType.PREDESIGNED_A;
                break;
            case 2:
                selectedType = ArenaType.PREDESIGNED_B;
                break;
            case 3:
                selectedType = ArenaType.PREDESIGNED_C;
                break;
            case 4:
                selectedType = ArenaType.RANDOMLY_GENERATED;
                break;
            default:
                System.out.println("Invalid selection. Defaulting to Predesigned A.");
                selectedType = ArenaType.PREDESIGNED_A;
        }

        // 2. Select Arena Mode (Open vs Closed)
        // This is passed to the Arena constructor to determine boundary walls [cite: 20]
        System.out.println("\nSelect Arena Mode:");
        System.out.println("1. Closed Arena (Solid walls on edges)");
        System.out.println("2. Open Arena (Wrap-around / Fall off)");
        System.out.print("Enter choice (1-2): ");

        int modeChoice = 0;
        if (scanner.hasNextInt()) {
            modeChoice = scanner.nextInt();
        }
        
        // If user chooses 2, isOpenType is true. Otherwise false.
        boolean isOpenType = (modeChoice == 2);

        System.out.println("\nInitializing Game...");

        // 3. Create and Start the Game
        // Uses the constructor defined in LightCycleGame.java 
        LightCycleGame game = new LightCycleGame(selectedType, isOpenType);
        
        // Starts the game loop defined in LightCycleGame.java 
        game.startGame(); 

        scanner.close();
    }
}
