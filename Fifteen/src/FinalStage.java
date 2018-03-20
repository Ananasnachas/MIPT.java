import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FinalStage{
    private static Stage primaryStage = Main.primaryStage;

    static Parent finalContent(Stage stage){

        Button buttonY = new Button();
        buttonY.setText("Yes");
        buttonY.setLayoutX(50);
        buttonY.setLayoutY(40);
        buttonY.setOnMouseClicked(ey -> {
            primaryStage.close();
            Main.wasConfigurationSolved = false;
            try {
                new Main().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            ey.consume();
            stage.close();

        });

        Button buttonN = new Button();
        buttonN.setText("No");
        buttonN.setLayoutX(150);
        buttonN.setLayoutY(40);
        buttonN.setOnMouseClicked(en -> {
            primaryStage.close();
            stage.close();
        });

        return new Pane(buttonY,buttonN);
    }
}
