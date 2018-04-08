package main.java.plots.viewModel.charts;

import javafx.scene.Group;
import javafx.scene.chart.NumberAxis;
import main.java.plots.model.Data;
import main.java.plots.view.ErrorAlert;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

public class ChartChooser {
    private Data data;

    public ChartChooser(File file){
        try {
            data = new Data(file);
        } catch (IOException e) {
            new ErrorAlert(e.toString());
            throw new UncheckedIOException(e);
        }
    }

    private NumberAxis getNumberAxis(){
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setForceZeroInRange(false);
        return numberAxis;
    }

    public void makeChart(Group group, String chartType, boolean isFirstChart){
        if(!isFirstChart){
            group.getChildren().remove(group.getChildren().size()-1);
        }
        if(data!=null) {
            if (chartType != null) {
                switch (chartType) {
                    case "line":
                        group.getChildren().add(new NumberLineChart(data.getXYData(), getNumberAxis(), getNumberAxis()));
                        break;
                    case "area":
                        group.getChildren().add(new NumberAreaChart(data.getXYData(), getNumberAxis(), getNumberAxis()));
                        break;
                    case "scatter":
                        group.getChildren().add(new NumberScatterChart(data.getXYData(), getNumberAxis(), getNumberAxis()));
                        break;
                }
            } else
                throw new NullPointerException();
        }
    }
}
