package fifteenPuzzles;

import fifteenPuzzles.windows.gameWindow.GameField;
import fifteenPuzzles.windows.gameWindow.InformationField;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    private static Stage primaryStage;
    private final int SIZE = 80;

    public void closePrimaryStage(){
        primaryStage.close();
    }

    public boolean isPrimaryStageShowing() {
        return primaryStage != null && primaryStage.isShowing();
    }

    public void newMainScene(){
        Group root = newGame();
        primaryStage.setScene(new Scene(root, 6*SIZE, 4*SIZE, Color.WHITE));
    }

    private Group newGame(){
        Group root = new Group();
        root.getChildren().add(new InformationField(SIZE));
        root.getChildren().add(new GameField(SIZE));
        return root;
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Пятнашки");
        primaryStage.setResizable(false);
        newMainScene();
        primaryStage.show();
    }
}