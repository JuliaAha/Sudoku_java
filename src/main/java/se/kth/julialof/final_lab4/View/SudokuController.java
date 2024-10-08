package se.kth.julialof.final_lab4.View;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import se.kth.julialof.final_lab4.Model.SudokuMain;
import java.io.File;
import java.io.IOException;

import static se.kth.julialof.final_lab4.Model.SudokuUtilities.GRID_SIZE;
/**
 * Kontrollerklass för Sudoku-spelet.
 * Hanterar interaktioner mellan användaren och spelet, inklusive spellogik och uppdatering av vyn.
 */
public class SudokuController {
    private SudokuMain model;
    private SudokuView view;
    /**
     * Konstruktor som skapar en Sudoku-kontroller.
     *
     * @param model Modellen för Sudoku-spelet.
     * @param view Vyn för Sudoku-spelet.
     */
    public SudokuController(SudokuMain model, SudokuView view){
        this.model = model;
        this.view = view;
    }
    /**
     * Hanterar händelsen när en tile i Sudoku-brädet trycks på.
     *
     * @param row Raden där tile är belägen.
     * @param col Kolumnen där tile är belägen.
     * @param buttonValue Värdet från knappen som tryckts på.
     */
    void handlePressedTile(int row, int col, char buttonValue){
        if (buttonValue == 'C') {
            model.removeTile(row,col);
            view.removePressedTile(row,col);
        } else {
            model.setTile(row, col, Character.getNumericValue(buttonValue));
            view.updatePressedTile(row, col);
        }
        checkIfWon();
    }
    /**
     * Kontrollerar om spelaren har vunnit och uppdaterar vyn därefter.
     */
    void checkIfWon(){
        if (model.nbOfFreeTiles() == 0){
            if (model.checkTiles()){
                view.showWinAlert();
            } else {
                view.showLoseAlert();
            }
        }
    }
    /**
     * Hanterar begäran om en ledtråd. Uppdaterar modellen och vyn.
     */
    void handleHint(){
        model.hint();
        view.updateAllTiles();
        checkIfWon();
    }
    /**
     * Kontrollerar om det aktuella Sudoku-brädet är korrekt ifyllt.
     *
     * @return True om alla tiles är korrekt ifyllda, annars false.
     */
    boolean handleCheck(){
        return model.checkTiles();
    }
    /**
     * Hanterar händelsen när en knapp trycks på.
     *
     * @param source Objektet som genererade händelsen.
     * @return Karaktären på knappen som trycktes på, eller ' ' om källan inte är en känd knapp.
     */
    char handlePressedButton(Object source){
        if (source instanceof Button button) {
            String buttonText = button.getText();
            return buttonText.charAt(0); //return value if recognised as button
        }
        return ' '; //Default return value
    }
    /**
     * Hanterar händelsen att starta om spelet. Återställer både modellen och vyn. Används även när man ändrar difficulty.
     *
     * @param difficulty Den svårighetsgrad som spelet ska startas om med.
     */
    void handleRestart(int difficulty){
        model.restartGame(difficulty);
        model.setDifficulty(difficulty);
        view.gridView();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                view.removePressedTile(row,col);
            }
        }
    }
    /**
     * Återställer spelet till sin ursprungliga tillstånd.
     */
    void handleClear(){
        model.clearGame();
        view.gridView();
    }
    /**
     * Hanterar laddning av ett sparad spel.
     */
    void handleLoadGame(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Sudoku Game File");
        File file = fileChooser.showOpenDialog(view.getScene().getWindow());
        if (file != null) {
            model.loadGame(file.getPath());
            view.updateAllTiles();
        }
        view.gridView();
        view.updateAllTiles();
    }
    /**
     * Hanterar sparande av det aktuella spelet.
     */
    void handleSaveGame(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Sudoku Game File");
        File file = fileChooser.showSaveDialog(view.getScene().getWindow());
        if (file != null) {
            try {
                model.saveGame(file.getPath());
            } catch (IOException e) {
                showAlert("Error", "Could not save the game: " + e.getMessage());
            }
        }
    }
    /**
     * Visar en dialogruta med specifik information.
     *
     * @param title Titeln på dialogrutan.
     * @param content Innehållet i dialogrutan.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
