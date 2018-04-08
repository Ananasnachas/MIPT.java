package main.java.plots.viewModel.charts;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class NumberLineChart extends LineChart {
    NumberLineChart(XYChart.Data[] data, NumberAxis axisX, NumberAxis axisY) {
        super(axisX,axisY);
        XYChart.Series series = new XYChart.Series();
        series.getData().addAll(data);
        getData().add(series);
        this.setLayoutY(30);
    }
}
