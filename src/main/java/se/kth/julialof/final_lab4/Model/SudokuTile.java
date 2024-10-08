package se.kth.julialof.final_lab4.Model;
/**
 * Klassen SudokuTile representerar en enskild ruta i ett Sudoku-spel.
 * Den hanterar lagring av rätt och fasta värden för varje ruta.
 */
public class SudokuTile {

    private final int[][] correctTiles = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
    private final int[][] fixedTiles = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
    private int[][] gamePlan = new int[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
    /**
     * Konstruktor som skapar en SudokuTile med givna värden.
     *
     * @param tiles En 3-dimensionell array som representerar Sudoku-brädet,
     *              där tiles[row][col][0] är de initiala värdena (fasta värden)
     *              och tiles[row][col][1] är de korrekta värdena för varje ruta.
     */
    public SudokuTile(int[][][] tiles){
        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                fixedTiles[row][col] = tiles[row][col][0];
            }
        }
        for(int row = 0; row<9; row++){
            for(int col = 0; col<9; col++){
                correctTiles[row][col] = tiles[row][col][1];
            }
        }
    }
    /**
     * Hämtar det korrekta värdet för en specifik ruta.
     *
     * @param row Radindex för rutan.
     * @param col Kolumnindex för rutan.
     * @return Det korrekta värdet för den angivna rutan.
     */
    public int getCorrectTile(int row, int col) {
        int copy = correctTiles[row][col];
        return copy;
    }
    /**
     * Hämtar det fasta värdet för en specifik ruta.
     * Fasta värden är de värden som är givna från början och kan inte ändras under spelets gång.
     *
     * @param row Radindex för rutan.
     * @param col Kolumnindex för rutan.
     * @return Det fasta värdet för den angivna rutan, om det finns; annars 0.
     */
    public int getFixedTile(int row, int col) {
        int copy = fixedTiles[row][col];
        return copy;
    }

    public int getTileFromGamePlan(int row, int col) {
        int copy = gamePlan[row][col];
        return copy;
    }

    public void setTileOnGamePlan(int row, int col, int input) {
        gamePlan[row][col] = input;
    }
}
