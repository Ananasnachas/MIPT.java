package main.java.puzzles;

import main.java.puzzles.windows.gameWindow.GameField;
import main.java.puzzles.windows.gameWindow.InformationField;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    private static final int SIZE = 80;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Пятнашки");
        primaryStage.setResizable(false);

        Group root = new Group();
        root.getChildren().add(new InformationField(SIZE));
        root.getChildren().add(new GameField(SIZE));

        primaryStage.setScene(new Scene(root, 6*SIZE, 4*SIZE, Color.WHITE));
        primaryStage.show();
    }

    public static int getSIZE() {
        return SIZE;
    }
}