package com.recyclothes.fx.main;

import com.recyclothes.fx.utils.FxChartUtil;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxChartsExample6 extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Create the X-Axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Year");
        // Customize the X-Axis, so points are scattered uniformly
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1900);
        xAxis.setUpperBound(2300);
        xAxis.setTickUnit(50);

        // Create the Y-Axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Population (in millions)");

        // Create the BubbleCHart
        BubbleChart<Number,Number> chart = new BubbleChart<>(xAxis, yAxis);
        // Set the Title for the Chart
        chart.setTitle("Population by Year and Country");

        // Set the Data for the Chart
        ObservableList<XYChart.Series<Number,Number>> chartData = FxChartUtil.getCountrySeries();

        // Set the Bubble Radius
        setBubbleRadius(chartData);

        // Set the Data for the Chart
        chart.setData(chartData);

        // Create the StackPane
        StackPane root = new StackPane();
        root.getChildren().add(chart);
        // Set the Style-properties of the Pane
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Stylesheet to the Scene
        scene.getStylesheets().add("css/chart/bubblechart.css");
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("A Bubble Chart Example");
        // Display the Stage
        stage.show();
    }

    // Calculate the Bubble Radius
    private void setBubbleRadius(ObservableList<XYChart.Series<Number,Number>> 	chartData)
    {
        for(XYChart.Series<Number,Number> series: chartData)
        {
            for(XYChart.Data<Number,Number> data : series.getData())
            {
                data.setExtraValue(20); // set Bubble radius
            }
        }
    }
}