package main.java.plots.view;

import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.plots.viewModel.charts.ChartChooser;


public class View {
    private RadioMenuItem lineChartItem;
    private RadioMenuItem areaChartItem;
    private RadioMenuItem scatterChartItem;
    private Menu fileMenu;
    private Menu viewMenu;
    private boolean isFirstChart = true;

    public void getMenuBar(Stage stage, Group group) {
        makeRadioButtons();
        makeMenus(group, stage);
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        menuBar.getMenus().addAll(fileMenu, viewMenu);
        group.getChildren().add(menuBar);
    }

    private void makeMenus(Group group, Stage stage){
        fileMenu = new Menu("File");
        MenuItem plotItem = new MenuItem("New plot");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(actionEvent -> stage.close());
        plotItem.setOnAction(actionEvent -> getFileChooser(group));
        fileMenu.getItems().addAll(plotItem,exitItem);

        viewMenu = new Menu("View");
        viewMenu.getItems().addAll(lineChartItem, areaChartItem, scatterChartItem);
    }

    private void makeRadioButtons(){
        lineChartItem = new RadioMenuItem("Line Chart");
        areaChartItem = new RadioMenuItem("Area Chart");
        scatterChartItem = new RadioMenuItem("Scatter Chart");
        ToggleGroup toggleGroup = new ToggleGroup();
        lineChartItem.setToggleGroup(toggleGroup);
        areaChartItem.setToggleGroup(toggleGroup);
        scatterChartItem.setToggleGroup(toggleGroup);
        lineChartItem.setSelected(true);
    }

    private String whichRadioButtonSelected(){
        if(lineChartItem.isSelected())
            return "line";
        else if(areaChartItem.isSelected())
            return "area";
        else
            return "scatter";
    }

    private void getFileChooser(Group group){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose file");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Csv file","*.csv"));
        Stage stage = new Stage();
        stage.setResizable(false);
        ChartChooser chartChooser= new ChartChooser(chooser.showOpenDialog(stage));
        chartChooser.makeChart(group,whichRadioButtonSelected(),isFirstChart);
        isFirstChart = false;

        lineChartItem.setOnAction(actionEvent ->  chartChooser.makeChart(group,"line",isFirstChart));
        areaChartItem.setOnAction(actionEvent ->  chartChooser.makeChart(group,"area",isFirstChart));
        scatterChartItem.setOnAction(actionEvent ->  chartChooser.makeChart(group,"scatter",isFirstChart));

    }
}
