package main.java.entities;

import java.util.Scanner;

public class characterMenu {

    // Inner class to define what a Character looks like
    public static class GameCharacter {
        String name;
        String color;
        String speed;
        String handling;
        String description;
        double lives;
        int discs;
        int experiencePoints;
        int level;

        public GameCharacter(String name, String color, String speed, String handling, String description, int lives, int discs, int experiencePoints) {
            this.name = name;
            this.color = color;
            this.speed = speed;
            this.handling = handling;
            this.description = description;
            this.lives = (double) lives; //default lives
            this.discs = discs; //default discs
            this.experiencePoints = experiencePoints; //default experience points
            this.level = 1; //start at level 1
        }
    }

    // Pre-defined list of characters
    private static final GameCharacter[] ROSTER = {
        new GameCharacter(
            "Tron", 
            "Blue", 
            "Moderate", 
            "Balanced",
            "The original defender of the Grid",
            3,
            1,
            0
        ),
        new GameCharacter(
            "Kevin", 
            "White", 
            "Very High", 
            "Smooth",
            "The creator - stable and resilient",
            3,
            1,
            0
        ),
    };

    /**
     * Handles the loop for selecting a character.
     * Displays info for each character when selected.
     */
    public GameCharacter runCharacterSelection(Scanner scanner) {
        System.out.println("=========================================");
        System.out.println("          CHARACTER SELECTION            ");
        System.out.println("=========================================");

        while (true) {
            System.out.println("\nAvailable Programs:");
            for (int i = 0; i < ROSTER.length; i++) {
                System.out.println((i + 1) + ". " + ROSTER[i].name);
            }
            System.out.println("0. View Character Details (Info Mode)");
            
            System.out.print("\nSelect a character to lock in (1-" + ROSTER.length + ") or '0' to inspect: ");
            
            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next(); // Clear bad input
                continue;
            }

            // Info Mode
            if (choice == 0) {
                inspectCharacters(scanner);
                continue; // Return to selection after inspection
            }

            // Selection Mode
            if (choice > 0 && choice <= ROSTER.length) {
                GameCharacter selected = ROSTER[choice - 1];
                System.out.println("\n>>> IDENTITY CONFIRMED: " + selected.name);
                System.out.println(">>> LOADING GRID...");
                return selected;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Allows user to cycle through descriptions without locking in.
     */
    private void inspectCharacters(Scanner scanner) {
        System.out.println("\n--- DATABASE ARCHIVE ---");
        for (int i = 0; i < ROSTER.length; i++) {
            System.out.println("ID [" + (i+1) + "]: " + ROSTER[i].name);
        }
        
        System.out.print("Enter ID to view details (or -1 to go back): ");
        int id = -1;
        if (scanner.hasNextInt()) id = scanner.nextInt();

        if (id > 0 && id <= ROSTER.length) {
            displayCharacterInfo(ROSTER[id - 1]);
        } else if (id != -1) {
            System.out.println("Record not found.");
        }
    }

    /**
     * Displays the specific stats/info of a character.
     */
    public void displayCharacterInfo(GameCharacter c) {
        System.out.println("\n+---------------------------------------+");
        System.out.println("| PROGRAM IDENTITY: " + c.name);
        System.out.println("+---------------------------------------+");
        System.out.println("| Description: " + c.color);
        System.out.println("| Ability:     " + c.speed);
        System.out.println("| Difficulty:  " + c.handling);
        System.out.println("| Description: " + c.description);
        System.out.println("+---------------------------------------+");
    }

}
