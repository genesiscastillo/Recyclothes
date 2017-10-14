package com.recyclothes.fx.main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxChartsExample2 extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Create the Axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Country");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Population (in millions)");

        // Create the BarChart
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        // Set the Title for the Chart
        chart.setTitle("Population by Country and Year");
        // Set the Data for the Chart
        ObservableList<XYChart.Series<String,Number>> chartData = null;
        chart.setData(chartData);

        // Create the Stackpane
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
        scene.getStylesheets().add("css/chart/barchart.css");
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("A Vertical Bar Chart Example");
        // Display the Stage
        stage.show();
    }
}