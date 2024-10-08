package se.kth.julialof.final_lab4.Model;

import java.util.Random;

public class SudokuUtilities {
    /**
     * Enum som definierar olika svårighetsgrader för Sudoku.
     */
    public enum SudokuLevel {EASY, MEDIUM, HARD}
    public static final int GRID_SIZE = 9;
    public static final int SECTIONS_PER_ROW = 3;
    public static final int SECTION_SIZE = 3;
    /**
     * Skapar en 3-dimensionell matris med initiala värden och lösning för ett Sudoku-spel.
     *
     * @param level Svårighetsgraden för det initiala spelet.
     * @return En 3-dimensionell heltalsmatris där [rad][kolumn][0] representerar initiala värden,
     * och [rad][kolumn][1] representerar lösningen.
     * @throws IllegalArgumentException om strängrepresentationen inte har längden 2*81 tecken
     * eller om den innehåller tecken som inte är '0'-'9'.
     */
    public static int[][][] generateSudokuMatrix(SudokuLevel level) {
        String representationString;
        Random rand = new Random();
        char randomNumber1 = (char) (rand.nextInt(9) + '1');
        char randomNumber2 = (char) (rand.nextInt(9) + '1');

        switch (level) {
            case EASY: representationString = swapCharacters(randomNumber1, randomNumber2, easy); break;
            case MEDIUM: representationString = swapCharacters(randomNumber1, randomNumber2, medium); break;
            case HARD: representationString = swapCharacters(randomNumber1, randomNumber2, hard); break;
            default: representationString = swapCharacters(randomNumber1, randomNumber2, medium);;
        }
        return convertStringToIntMatrix(representationString);
    }
    /**
     * Byter ut två slumpmässigt valda tecken i en sträng.
     *
     * @param randomNumber1 Det första tecknet som ska bytas ut.
     * @param randomNumber2 Det andra tecknet som ska bytas ut.
     * @param str Strängen där tecknen ska bytas ut.
     * @return Strängen med utbytta tecken.
     */
    public static String swapCharacters(char randomNumber1, char randomNumber2, String str){
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == randomNumber1) {
                chars[i] = randomNumber2;
            } else if (chars[i] == randomNumber2) {
                chars[i] = randomNumber1;
            }
        }
        return new String(chars);
    }
    /**
     * Konverterar en strängrepresentation till en 3-dimensionell matris för Sudoku.
     *
     * @param stringRepresentation En sträng med 2*81 tecken, 0-9, där de första 81 tecknen
     * representerar initiala värden och de följande 81 tecknen representerar lösningen.
     * @return En 3-dimensionell heltalsmatris som representerar Sudoku-brädet.
     * @throws IllegalArgumentException om strängrepresentationen inte har längden 2*81 tecken
     * eller om den innehåller tecken som inte är '0'-'9'.
     */
    /*package private*/
    static int[][][] convertStringToIntMatrix(String stringRepresentation) {
        if (stringRepresentation.length() != GRID_SIZE * GRID_SIZE * 2)
            throw new IllegalArgumentException("representation length " +
                    stringRepresentation.length());
        int[][][] values = new int[GRID_SIZE][GRID_SIZE][2];
        char[] charRepresentation = stringRepresentation.toCharArray();
        int charIndex = 0;
// initial values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col][0] =
                        convertCharToSudokuInt(charRepresentation[charIndex++]);
            }
        }
// solution values
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                values[row][col][1] =
                        convertCharToSudokuInt(charRepresentation[charIndex++]);
            }
        }
        return values;
    }
    /**
     * Hjälpmetod för att konvertera en char till ett heltal som används i Sudoku.
     *
     * @param ch Tecknet som ska konverteras.
     * @return Heltal som representerar tecknet.
     * @throws IllegalArgumentException om tecknet inte är mellan '0' och '9'.
     */
    private static int convertCharToSudokuInt(char ch) {
        if (ch < '0' || ch > '9') throw new IllegalArgumentException("character " +
                ch);
        return ch - '0';
    }


    private static final String easy =
            "000914070" +
                    "010000054" +
                    "040002000" +
                    "007569001" +
                    "401000500" +
                    "300100000" +
                    "039000408" +
                    "650800030" +
                    "000403260" + // solution values after this substring
                    "583914672" +
                    "712386954" +
                    "946752183" +
                    "827569341" +
                    "461238597" +
                    "395147826" +
                    "239675418" +
                    "654821739" +
                    "178493265";
    private static final String medium =
            "300000010" +
                    "000050906" +
                    "050401200" +
                    "030000080" +
                    "002069400" +
                    "000000002" +
                    "900610000" +
                    "200300058" +
                    "100800090" +
                    "324976815" +
                    "718253946" +
                    "659481273" +
                    "536142789" +
                    "872569431" +
                    "491738562" +
                    "985617324" +
                    "267394158" +
                    "143825697";
    private static final String hard =
            "030600000" +
                    "000010070" +
                    "080000000" +
                    "000020000" +
                    "340000800" +
                    "500030094" +
                    "000400000" +
                    "150800200" +
                    "700006050" +
                    "931687542" +
                    "465219378" +
                    "287345916" +
                    "876924135" +
                    "349561827" +
                    "512738694" +
                    "693452781" +
                    "154873269" +
                    "728196453";
}
