import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    static Cell[] cells = new Cell[16];
    static Stage primaryStage = new Stage();
    static int size = 80;
    static boolean wasConfigurationSolved = false;

    private Group root = new Group();

    @Override
    public void start(Stage stage) {
        root.getChildren().add(new InformationField().makeFill());
        root.getChildren().add(new InformationField().makeText());
        root.getChildren().add(new GameField().gameContent());

        primaryStage.setTitle("Пятнашки");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 6*size, 4*size, Color.WHITE));
        primaryStage.show();
    }
}