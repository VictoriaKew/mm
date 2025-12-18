package main.java.entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnemyLoader {

    /**
     * Reads a CSV-style text file and creates a list of Enemy objects.
     * Expected format: Name,Color,Difficulty,XP,Speed,Handling,Aggression,Lives,Discs
     */
    public static List<Enemy> loadEnemies(String filename) {
        List<Enemy> enemies = new ArrayList<>();
        String line;
        int lineNumber = 0; // Helpful for debugging bad lines in the text file

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            // Skip the CSV header line
            br.readLine(); 
            lineNumber++;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                // Skip empty lines or comments starting with //
                if (line.trim().isEmpty() || line.trim().startsWith("//")) {
                    continue;
                }

                String[] parts = line.split(",");

                // Ensure we have exactly 9 columns of data
                if (parts.length != 9) {
                    System.err.println("Skipping malformed enemy data at line " + lineNumber + " (expected 9 parts): " + line);
                    continue;
                }

                try {
                    // Extract String values
                    String name = parts[0].trim();
                    String color = parts[1].trim(); // Changed 'colour' to 'color' to match factory
                    String difficulty = parts[2].trim();

                    // Extract and parse numeric values
                    int xp = Integer.parseInt(parts[3].trim());
                    double speed = Double.parseDouble(parts[4].trim());
                    double handling = Double.parseDouble(parts[5].trim());
                    double aggression = Double.parseDouble(parts[6].trim());
                    int lives = Integer.parseInt(parts[7].trim());
                    int discs = Integer.parseInt(parts[8].trim());

                    // Use the Factory to create the specific enemy instance
                    enemies.add(EnemyFactory.createEnemy(
                        name, color, difficulty, xp, speed, handling, aggression, lives, discs
                    ));
                    
                } catch (NumberFormatException e) {
                    System.err.println("Error parsing number at line " + lineNumber + ": " + e.getMessage());
                    // We catch this inside the loop so one bad number doesn't crash the whole loader
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The enemy file '" + filename + "' was not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading enemy file: " + e.getMessage());
            e.printStackTrace();
        }

        return enemies;
    }
}

