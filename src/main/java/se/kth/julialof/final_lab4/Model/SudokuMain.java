package se.kth.julialof.final_lab4.Model;

import java.io.IOException;
import java.util.Random;

import static se.kth.julialof.final_lab4.Model.SudokuUtilities.GRID_SIZE;
/**
 * Huvudklassen för att hantera ett Sudoku-spel.
 * Denna klass innehåller logiken för att spela spelet, inklusive starta ett nytt spel,
 * hantera användarinteraktioner och spara/ladda spel.
 */
public class SudokuMain {
    private int[][][] allTiles;
    SudokuTile Tile;
    private int difficulty;
    /**
     * Konstruktor som initierar ett nytt Sudoku-spel med standardinställningar.
     */
    public SudokuMain(){
        restartGame(0);
    }
    /**
     * Hämtar svårighetsgraden för det nuvarande spelet.
     *
     * @return Nuvarande svårighetsgrad.
     */

    public int getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(int difficulty){
        this.difficulty = difficulty;
    }

    /**
     * Startar om spelet med en given svårighetsgrad.
     *
     * @param difficulty Svårighetsgrad för det nya spelet.
     */
    public void restartGame(int difficulty){
        switch(difficulty){
            case 1: allTiles = SudokuUtilities.generateSudokuMatrix(SudokuUtilities.SudokuLevel.EASY); break;
            case 2: allTiles = SudokuUtilities.generateSudokuMatrix(SudokuUtilities.SudokuLevel.MEDIUM); break;
            case 3: allTiles = SudokuUtilities.generateSudokuMatrix(SudokuUtilities.SudokuLevel.HARD); break;
            default: allTiles = SudokuUtilities.generateSudokuMatrix(SudokuUtilities.SudokuLevel.MEDIUM);
        }
        Tile = new SudokuTile(allTiles); //skapar ett Tile objekt och skickar med all matrisinformatino
        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                Tile.setTileOnGamePlan(row,col,allTiles[row][col][0]); // sätter alla fixed tiles på gameplan
            }
        }
    }
    /**
     * Uppdaterar en tile på spelplanen om den inte är en fast tile.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     * @param input Värdet som ska placeras i tile.
     */
    public void setTile(int row, int col, int input){
        if (Tile.getFixedTile(row, col) == 0){ // Check so it is not a fixed tile
            Tile.setTileOnGamePlan(row,col,input);
        }
    }
    /**
     * Tar bort ett värde från en tile i spelet.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     */
    public void removeTile(int row, int col) {
        if (Tile.getFixedTile(row, col) == 0) { // Check so it is not a fixed tile
            Tile.setTileOnGamePlan(row, col, 0);
        }
    }
    /**
     * Kontrollerar om en tile är fast och inte kan ändras.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     * @return True om det är en fast tile, annars false.
     */
    public boolean checkIfFixedTile(int row, int col){
        if (Tile.getFixedTile(row,col) == 0){
            return false;
        } else return true;
    }
    /**
     * Hämtar värdet av en tile på spelplanen.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     * @return Värdet av tile.
     */
    public int getTile(int row, int col){
        return Tile.getTileFromGamePlan(row,col);
    }
    /**
     * Kontrollerar om alla icke-fasta tiles har rätt värde.
     *
     * @return True om alla tiles är korrekta, annars false.
     */

    public boolean checkTiles(){
        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                if (Tile.getTileFromGamePlan(row,col) != Tile.getFixedTile(row,col)){
                    if (Tile.getTileFromGamePlan(row,col) != 0) {
                        if (Tile.getTileFromGamePlan(row,col) != Tile.getCorrectTile(row,col)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    /**
     * Ger en ledtråd genom att korrigera en slumpmässigt vald icke-korrekt tile.
     */
    public void hint(){
        Random rand = new Random();

        int randomNumber = rand.nextInt(nbOfFreeTiles()) + 1; //get random number between 1 - number of not correct tiles
        int counter = 0;

        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                if (Tile.getTileFromGamePlan(row,col) != Tile.getCorrectTile(row,col)){
                    counter++;
                    if (randomNumber == counter) {
                        setTile(row,col,Tile.getCorrectTile(row,col));
                    }
                }
            }
        }
    }
    /**
     * Beräknar antalet lediga rutor.
     *
     * @return Antalet lediga rutor.
     */
    public int nbOfFreeTiles(){
        int count = 0;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if(Tile.getTileFromGamePlan(row,col) == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    /**
     * Återställer spelet till sin startkonfiguration.
     */
    public void clearGame(){
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if(!checkIfFixedTile(row,col)){
                    Tile.setTileOnGamePlan(row,col,0);
                }
            }
        }
    }
    /**
     * Skriver ut värdet av en tile för felsökning.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     * @return Värdet av tile.
     */
    public int printSquare(int row, int col){ //only for debug in Main Tester, never used in controller/view
//        return gamePlan[row][col];
        return 0;
    }
    /**
     * Sparar det aktuella spelplanen till en fil.
     *
     * @param filePath Sökvägen till filen där spelet ska sparas.
     * @throws IOException Om ett I/O-fel uppstår.
     */
    public void saveGame(String filePath) throws IOException {
        int[][] temp = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) { //make copy of gameplan into temp
            for (int col = 0; col < GRID_SIZE; col++) {
                temp[row][col] = Tile.getTileFromGamePlan(row,col);
            }
        }
        SudokuFile.saveGame(temp,allTiles,filePath);
    }
    /**
     * Laddar ett spel från en fil.
     *
     * @param filePath Sökvägen till filen där spelet är sparad.
     */
    public void loadGame(String filePath) {
        int[][] temp = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        try {
            SudokuFile.loadGame(filePath);
            Tile = new SudokuTile(SudokuFile.getNewAllTiles());
            temp = SudokuFile.getNewGamePlan();

            for (int row = 0; row < GRID_SIZE; row++) { //set gameplan from temp
                for (int col = 0; col < GRID_SIZE; col++) {
                    Tile.setTileOnGamePlan(row,col,temp[row][col]);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the game.");
            e.printStackTrace();
        }
    }
}
