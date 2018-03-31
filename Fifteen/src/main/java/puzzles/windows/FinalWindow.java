package main.java.puzzles.windows;

import main.java.puzzles.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class FinalWindow extends Alert{
    ButtonType yes;
    ButtonType no;

    public FinalWindow() {
        super(AlertType.CONFIRMATION);
        makeFinalWindow();
    }

    private void makeFinalWindow(){
        setTitle("You won!");
        setHeaderText("Want to play again?");

        yes = new ButtonType("Yes");
        no = new ButtonType("No");

        getButtonTypes().clear();
        getButtonTypes().addAll(yes,no);

        Optional<ButtonType> option = showAndWait();

        if (option.get() == yes) {
            Main.newMainScene();
            close();
        } else if (option.get() == no) {
            Main.closePrimaryStage();
            close();
        }
    }
}
