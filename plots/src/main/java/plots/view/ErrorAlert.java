package main.java.plots.view;

import javafx.scene.control.Alert;

public class ErrorAlert extends Alert {
    public ErrorAlert(String text) {
        super(AlertType.ERROR);
        setTitle("Error Alert");
        setContentText(text +"\n"+"File was not opened.");
        showAndWait();
    }
}
