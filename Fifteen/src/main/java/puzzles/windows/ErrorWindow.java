package main.java.puzzles.windows;

import javafx.scene.control.Alert;
import main.java.puzzles.time.MyDuration;

public class ErrorWindow extends Alert{
    public ErrorWindow(Exception e) {
        super(AlertType.ERROR);
        showError(e);
    }

    private void showError(Exception e) {
        setTitle("Error");
        setHeaderText(e.getMessage());
        MyDuration.stop();
        showAndWait();
    }
}
