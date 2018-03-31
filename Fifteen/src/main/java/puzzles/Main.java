package main.java.puzzles;

import main.java.puzzles.windows.gameWindow.GameField;
import main.java.puzzles.windows.gameWindow.InformationField;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    private static Stage primaryStage;
    private static final int SIZE = 80;

    public static void closePrimaryStage(){
        primaryStage.close();
    }

    public static boolean isPrimaryStageShowing() {
        return primaryStage != null && primaryStage.isShowing();
    }

    public static void newMainScene(){
        Group root = newGame();
        primaryStage.setScene(new Scene(root, 6*SIZE, 4*SIZE, Color.WHITE));
    }

    private static Group newGame(){
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