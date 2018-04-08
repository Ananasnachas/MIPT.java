package main.java.plots.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage){
        stage.setResizable(false);
        Group group = new Group();
        View view = new View();
        view.getMenuBar(stage, group);
        stage.setScene(new Scene(group,500,400));
        stage.show();
    }
}
