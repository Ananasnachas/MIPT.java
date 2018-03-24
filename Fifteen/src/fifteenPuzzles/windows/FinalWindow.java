package fifteenPuzzles.windows;

import fifteenPuzzles.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class FinalWindow extends Alert{

    public FinalWindow() {
        super(AlertType.CONFIRMATION);
        makeFinalWindow();
    }

    private void makeFinalWindow(){
        setTitle("You won!");
        setHeaderText("Want to play again?");

        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");

        getButtonTypes().clear();
        getButtonTypes().addAll(yes,no);

        Main main = new Main();
        Optional<ButtonType> option = showAndWait();

        if (option.get() == yes) {
            main.newMainScene();
            close();
        } else if (option.get() == no) {
            main.closePrimaryStage();
            close();
        }
    }
}
