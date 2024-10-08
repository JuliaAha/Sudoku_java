package se.kth.julialof.final_lab4.Model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static se.kth.julialof.final_lab4.Model.SudokuUtilities.GRID_SIZE;

/**
 * En klass som hanterar sparande och inläsning av Sudoku-spel till och från filer.
 */
public class SudokuFile {
    private static int[][] newGamePlan = new int[GRID_SIZE][GRID_SIZE];
    private static int[][][] newAllTiles = new int[GRID_SIZE][GRID_SIZE][2];
    /**
     * Sparar ett Sudoku-spel till en fil.
     *
     * @param gamePlan  En matris som representerar Sudoku-spelet.
     * @param filePath  Sökväg till filen där spelet ska sparas.
     * @throws IOException Om det uppstår problem med filhantering.
     */
    public static void saveGame(int[][] gamePlan, int[][][] allTiles, String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (int row = 0; row < gamePlan.length; row++) {
                for (int col = 0; col < gamePlan[row].length; col++) {
                    writer.write(gamePlan[row][col] + " ");
                }
                writer.newLine();
            }
            for (int row = 0; row < allTiles.length; row++) {
                for (int col = 0; col < allTiles[row].length; col++) {
                    for (int z = 0; z < allTiles[row][col].length; z++) {
                        writer.write(allTiles[row][col][z] + " ");
                    }
                    writer.newLine();
                }
                writer.newLine();
            }
        }
    }
    public static void loadGame(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            // Load gamePlan

            for (int row = 0; row < newGamePlan.length; row++) {
                String[] line = reader.readLine().trim().split(" ");
                for (int col = 0; col < newGamePlan[row].length; col++) {
                    newGamePlan[row][col] = Integer.parseInt(line[col]);
                }
            }
            // Load allTiles
            for (int row = 0; row < newAllTiles.length; row++) {
                for (int col = 0; col < newAllTiles[row].length; col++) {
                    String[] line = reader.readLine().trim().split(" ");
                    for (int z = 0; z < newAllTiles[row][col].length; z++) {
                        newAllTiles[row][col][z] = Integer.parseInt(line[z]);
                    }
                }
                reader.readLine(); // Skip the extra newline between rows
            }
        }
    }
    public static int[][] getNewGamePlan() {
        return newGamePlan;
    }
    public static int[][][] getNewAllTiles() {
        return newAllTiles;
    }
}