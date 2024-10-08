package se.kth.julialof.final_lab4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.julialof.final_lab4.Model.SudokuMain;
import se.kth.julialof.final_lab4.View.SudokuView;
/**
 * Huvudklassen för Sudoku-applikationen.
 * Denna klass är ansvarig för att starta och visa det grafiska gränssnittet för spelet.
 */
public class Main extends Application {
    /**
     * Huvudmetoden som startar applikationen.
     *
     * @param args Argument som kan skickas till programmet (används inte i detta fall).
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Startar den grafiska delen av applikationen.
     * Skapar modellen, vyn och kopplar ihop dessa med en kontroller. Sätter även upp scenen och visar den.
     *
     * @param stage Huvudscenen för applikationen där allt grafiskt innehåll visas.
     */
    @Override
    public void start(Stage stage) {
        SudokuMain model = new SudokuMain();
        SudokuView view = new SudokuView(model); // Skapar vyn för Sudoku-spelet och även kontrollern inuti vyn.
        MenuBar menuBar = view.getTheMenuBar(); // Hämtar menyraden från vyn och lägger den i en VBox tillsammans med vyn.
        VBox root = new VBox(menuBar, view);

        // Skapar och sätter scenen för applikationen, inklusive storlek och titel.
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setTitle("Sudoku");
        stage.show();
    }
}