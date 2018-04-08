package main.java.plots.model;
import javafx.scene.chart.XYChart;
import main.java.plots.view.ErrorAlert;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Data {
    private XYChart.Data[] xYData;
    private XYChart.Data[] getData(File file) throws IOException {

        List<String> lines = FileUtils.readLines(file);
        xYData = new XYChart.Data[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            String[] values = lines.get(i).split(";");
            try {
                xYData[i] = new XYChart.Data<>(new Double(values[0]), new Double(values[1]));
            }
            catch (Exception a){
                new ErrorAlert("Wrong input format in line " + Integer.toString(i+1));
                throw new RuntimeException();
            }
        }
        return xYData;
    }

    public Data(File file) throws IOException {
        xYData = getData(file);
    }

    public XYChart.Data[] getXYData() {
        return xYData;
    }
}
