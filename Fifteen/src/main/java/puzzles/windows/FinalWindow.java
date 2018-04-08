package main.java.puzzles.windows;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.java.puzzles.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.java.puzzles.windows.gameWindow.GameField;
import main.java.puzzles.windows.gameWindow.InformationField;

import java.util.Optional;

public class FinalWindow extends Alert{
    private ButtonType yes;
    private ButtonType no;
    private Window owner;

    public FinalWindow(Pane pane) {
        super(AlertType.CONFIRMATION);
        owner = pane.getScene().getWindow();
        makeFinalWindow();
    }

    private void makeFinalWindow(){
        setTitle("You won!");
        setHeaderText("Want to play again?");

        yes = new ButtonType("Yes");
        no = new ButtonType("No");

        getButtonTypes().clear();
        getButtonTypes().addAll(yes,no);

        initModality(Modality.APPLICATION_MODAL);
        initOwner(owner);

        Optional<ButtonType> option = showAndWait();

        if (option.get() == yes) {
            Stage primaryStage = (Stage) owner;
            Group root = new Group();
            int SIZE = Main.getSIZE();
            root.getChildren().add(new InformationField(SIZE));
            root.getChildren().add(new GameField(SIZE));
            primaryStage.setScene(new Scene(root, 6*SIZE, 4*SIZE, Color.WHITE));
            close();
        } else if (option.get() == no) {
            Stage primaryStage = (Stage) owner;
            primaryStage.close();
        }
    }
}
