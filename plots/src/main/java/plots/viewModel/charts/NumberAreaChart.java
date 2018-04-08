package main.java.plots.viewModel.charts;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class NumberAreaChart extends AreaChart {
    NumberAreaChart(XYChart.Data[] data, NumberAxis axisX, NumberAxis axisY) {
        super(axisX,axisY);
        XYChart.Series series = new XYChart.Series();
        series.getData().addAll(data);
        getData().add(series);
        setLayoutY(30);
    }
}
