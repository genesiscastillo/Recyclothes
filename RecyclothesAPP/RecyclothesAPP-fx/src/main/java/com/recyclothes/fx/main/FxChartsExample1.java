package com.recyclothes.fx.main;

import com.recyclothes.fx.utils.FxChartUtil;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxChartsExample1 extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Create the PieChart
        PieChart chart = new PieChart();
        // Set the Title of the Chart
        chart.setTitle("Population in Year 2000");
        // Place the legend on the left side
        chart.setLegendSide(Side.LEFT);
        // Set the Data for the Chart
        ObservableList<PieChart.Data> chartData = FxChartUtil.getChartData();
        chart.setData(chartData);

        // Create a Stackpane
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
        //scene.getStylesheets().add(getClass().getResource("piechart.css").toExternalForm());
        scene.getStylesheets().add("css/chart/piechart.css");
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("A Pie Chart Example");
        // Display the Stage
        stage.show();
    }
}