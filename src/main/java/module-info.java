module se.kth.julialof.final_lab4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.julialof.final_lab4 to javafx.fxml;
    exports se.kth.julialof.final_lab4;
}