package fifteenPuzzles.windows;

import javafx.scene.control.Alert;
import fifteenPuzzles.time.Timer;

public class ErrorWindow extends Alert{
    public ErrorWindow(Exception e) {
        super(AlertType.ERROR);
        showError(e);
    }

    private void showError(Exception e) {
        setTitle("Error");
        setHeaderText(e.getMessage());
        Timer.stop();
        showAndWait();
    }
}
