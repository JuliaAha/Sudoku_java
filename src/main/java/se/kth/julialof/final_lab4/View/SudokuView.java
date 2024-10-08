package se.kth.julialof.final_lab4.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import se.kth.julialof.final_lab4.Model.SudokuMain;
import static se.kth.julialof.final_lab4.Model.SudokuUtilities.*;
/**
 * Vy-klassen för Sudoku-spelet.
 * Hanterar grafisk representation av spelet och användarinteraktioner.
 */
public class SudokuView extends BorderPane {

    private Label[][] numberTiles; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private char buttonValue;
    private SudokuMain model;
    private SudokuController controller;
    private MenuBar menuBar;
    /**
     * Konstruktor för SudokuView.
     * Skapar användargränssnittet och kopplar ihop det med den givna modellen.
     *
     * @param model Modellen som användargränssnittet ska interagera med.
     */
    public SudokuView(SudokuMain model){
        this.model = model;
        controller = new SudokuController(model,this);

        gridView();
    }
    /**
     * Initialiserar och skapar rutnätet för Sudoku-brädet.
     */
    public void gridView() {
        numberTiles = new Label[GRID_SIZE][GRID_SIZE];
        initNumberTiles();
        numberPane = makeNumberPane();
        this.setCenter(numberPane);
        this.setLeft(leftSide());
        this.setRight(rightSide());
        setMenuBar();
    }
    /**
     * Skapar och sätter upp menyraden för spelet.
     */
    public void setMenuBar(){
        Menu fileMenu = new Menu("File");
        MenuItem loadGameItem = new MenuItem("Load Game");
        loadGameItem.addEventHandler(ActionEvent.ACTION,LoadHandler);
        MenuItem saveGameItem = new MenuItem("Save Game");
        saveGameItem.addEventHandler(ActionEvent.ACTION,SaveHandler);
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addEventHandler(ActionEvent.ACTION, ExitHandler);
        fileMenu.getItems().addAll(loadGameItem,saveGameItem,exitItem);

        Menu GameMenu = new Menu("Game");
        MenuItem RestartGame = new MenuItem("Restart");
        RestartGame.addEventHandler(ActionEvent.ACTION, RestartHandler);
        MenuItem GameLevel = new MenuItem("Choose Level");
        GameLevel.addEventHandler(ActionEvent.ACTION, LevelHandler);
        GameMenu.getItems().addAll(RestartGame,GameLevel);

        Menu HelpMenu = new Menu("Help");
        MenuItem clear = new MenuItem("Clear");
        clear.addEventHandler(ActionEvent.ACTION, clearHandler);
        MenuItem checkGame = new MenuItem("Check game");
        checkGame.addEventHandler(ActionEvent.ACTION,CheckHandler);
        MenuItem GamesRules = new MenuItem("Rules");
        GamesRules.addEventHandler(ActionEvent.ACTION,RulesHandler);
        HelpMenu.getItems().addAll(clear,checkGame,GamesRules);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu,GameMenu,HelpMenu);
    }
    public MenuBar getTheMenuBar(){
        return this.menuBar;
    }
    /**
     * Skapar vänstra sidan av användargränssnittet med kontroller som 'Check' och 'Hint'.
     * @return VBox innehållande vänstra sidans kontroller.
     */
    private VBox leftSide(){
        VBox v1 = new VBox();

        Button check = new Button("Check");
        Button hint = new Button("Hint");
        v1.setAlignment(Pos.CENTER);
        v1.getChildren().add(check);
        v1.getChildren().add(hint);
        v1.setPadding(new Insets(10));
        v1.setSpacing(10);
        this.setLeft(v1);
        hint.addEventHandler(ActionEvent.ACTION, HintHandler);
        check.addEventHandler(ActionEvent.ACTION, CheckHandler);
        return v1;
    }
    /**
     * Skapar högra sidan av användargränssnittet med sifferknappar och en 'Clear'-knapp.
     * @return VBox innehållande högra sidans kontroller.
     */
    private VBox rightSide(){
        VBox v2 = new VBox();

        v2.setAlignment(Pos.CENTER);
        Button one = new Button("1");
        one.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button two = new Button("2");
        two.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button three = new Button("3");
        three.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button four = new Button("4");
        four.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button five = new Button("5");
        five.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button six = new Button("6");
        six.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button seven = new Button("7");
        seven.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button eight = new Button("8");
        eight.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button nine = new Button("9");
        nine.addEventHandler(ActionEvent.ACTION, buttonHandler);
        Button clear = new Button("C");
        clear.addEventHandler(ActionEvent.ACTION, buttonHandler);
        v2.getChildren().addAll(one,two,three,four,five,six,seven,eight,nine,clear);
        v2.setPadding(new Insets(10));
        v2.setSpacing(1);

        return v2;
    }

    /**
     * Returnerar huvudrutnätet för Sudoku-brädet.
     *
     * @return TilePane som representerar rutnätet.
     */
    public TilePane getNumberPane() {
        return numberPane;
    }
    /**
     * Initierar och skapar varje rutor på Sudoku-brädet. Kallas endast på av konstruktorm
     */
    private final void initNumberTiles() {

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Label tile = new Label("");
                Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);
                if(model.checkIfFixedTile(row,col)) { //if fixed tile
                    tile = new Label(/* add number, or "", to display */Integer.toString(model.getTile(row, col))); // data from model
                    font = Font.font("Monospaced", FontWeight.EXTRA_BOLD, 20);
                }
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                numberTiles[row][col] = tile;
            }
        }
    }
    /**
     * Skapar och konfigurerar TilePane som håller rutnätet för Sudoku.
     *
     * @return TilePane som innehåller hela Sudoku-brädet.
     */
    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SECTIONS_PER_ROW);
        root.setPrefRows(SECTIONS_PER_ROW);
        root.setStyle("-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SECTIONS_PER_ROW][SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SECTION_SIZE);
                section.setPrefRows(SECTION_SIZE);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < SECTION_SIZE; row++) {
                    for (int col = 0; col < SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(
                                numberTiles[srow * SECTION_SIZE + row][scol * SECTION_SIZE + col]);
                    }
                }

                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }

        return root;
    }
    /**
     * Uppdaterar texten på en tryckt tile.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     */
    public void updatePressedTile(int row, int col){
        if (!model.checkIfFixedTile(row,col)) { //if not a fixed Tile
            numberTiles[row][col].setText(Integer.toString(model.getTile(row, col)));
        }

    }
    /**
     * Tar bort text från en tryckt tile.
     *
     * @param row Radindex för tile.
     * @param col Kolumnindex för tile.
     */
    public void removePressedTile(int row, int col){
        if (!model.checkIfFixedTile(row,col)) { //if not a fixed Tile
            numberTiles[row][col].setText("");
        }
    }
    /**
     * Uppdaterar alla tiles på brädet.
     */
    public void updateAllTiles(){
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (model.getTile(row,col) != 0){
                    numberTiles[row][col].setText(Integer.toString(model.getTile(row,col)));
                }
            }
        }
    }

    public void showWinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations");
        alert.setHeaderText(null);
        alert.setContentText("You have won!");
        alert.showAndWait();
    }

    public void showLoseAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sorry");
        alert.setHeaderText(null);
        alert.setContentText("Some of the tiles that you have placed are incorrect!");
        alert.showAndWait();
    }


    /**
     * Hanterar kontrollen av brädet när användaren klickar på 'Check'-knappen.
     * Visar en dialogruta som informerar om statusen för de placerade tiles.
     */
    private EventHandler<ActionEvent> CheckHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            if (controller.handleCheck()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("All placed tiles are currently in their right position!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("One or some of the placed tiles is not in their right position!");
                alert.showAndWait();
            }

        }
    };
    /**
     * Hanterar 'Hint'-händelsen. Ber modellen om en ledtråd och uppdaterar sedan brädet.
     */
    private EventHandler<ActionEvent> HintHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            controller.handleHint();
        }
    };

    /**
     * Hanterar klickhändelser på varje tile på brädet.
     * Bestämmer vilken tile som klickats och vidarebefordrar informationen till kontrollern.
     */
    private EventHandler<MouseEvent> tileClickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            for(int row = 0; row < GRID_SIZE; row++) {
                for(int col = 0; col < GRID_SIZE; col++) {
                    if(event.getSource() == numberTiles[row][col]) {
                        // we got the row and column - now call the appropriate controller method, e.g.
                        controller.handlePressedTile(row, col, buttonValue); //buttonvalue
                        return;
                    }
                }
            }
        }
    };
    /**
     * Hanterar händelser när någon av sifferknapparna eller 'Clear'-knappen trycks.
     * Bestämmer vilken knapp som tryckts och skickar dess värde till kontrollern.
     */
    private EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            buttonValue=controller.handlePressedButton(actionEvent.getSource());
        }
    };
    /**
     * Hanterar laddning av ett sparad spel när användaren väljer 'Load Game'.
     * Laddar spelet från en fil och uppdaterar brädet.
     */
    private EventHandler<ActionEvent> LoadHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            controller.handleLoadGame();

        }
    };
    /**
     * Hanterar sparandet av det aktuella spelet när användaren väljer 'Save Game'.
     * Sparar spelet till en fil.
     */
    private EventHandler<ActionEvent> SaveHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            controller.handleSaveGame();
        }
    };
    /**
     * Hanterar visning av spelregler när användaren väljer 'Rules' i hjälpmenyn.
     * Visar en dialogruta med information om Sudoku-reglerna.
     */
    private EventHandler<ActionEvent> RulesHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Sudoku Rules");
            alert.setContentText("Hello there SudokuPlayer! :) \n" +
                    "Every square has to contain a single number\n" +
                    "Only the numbers 1-9 can be used\n" +
                    "Each 3×3 box shall contain each number from 1 to 9 once\n" +
                    "Each vertical column shall contain each number from 1 to 9 once\n" +
                    "Each horizontal row shall contain each number from 1 to 9 once\n\n\n");
            alert.showAndWait();
        }
    };
    /**
     * Hanterar avslutning av spelet när användaren väljer 'Exit'.
     * Avslutar programmet.
     */
    private EventHandler<ActionEvent> ExitHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            System.exit(0);
        }
    };
    /**
     * Hanterar omstart av spelet när användaren väljer 'Restart'.
     * Återställer spelet till sin initiala status.
     */
    private EventHandler<ActionEvent> RestartHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            controller.handleRestart(model.getDifficulty());
        }
    };

    private EventHandler<ActionEvent> clearHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            controller.handleClear();
        }
    };


    /**
     * Hanterar ändring av svårighetsgrad när användaren väljer 'Choose Level'.
     * Låter användaren välja en ny svårighetsgrad och startar om spelet.
     */
    private EventHandler<ActionEvent> LevelHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Select Level");
            alert.setHeaderText("Choose a difficulty level");

            // Customizing the buttons
            ButtonType buttonEasy = new ButtonType("Easy");
            ButtonType buttonMedium = new ButtonType("Medium");
            ButtonType buttonHard = new ButtonType("Hard");

            // Setting the button types
            alert.getButtonTypes().setAll(buttonEasy, buttonMedium, buttonHard);

            // Showing the alert and waiting for a response
            ButtonType response = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (response == buttonEasy) {
                controller.handleRestart(1);
            } else if (response == buttonMedium) {
                controller.handleRestart(2);
            } else if (response == buttonHard) {
                controller.handleRestart(3);
            }
        }
    };
}
