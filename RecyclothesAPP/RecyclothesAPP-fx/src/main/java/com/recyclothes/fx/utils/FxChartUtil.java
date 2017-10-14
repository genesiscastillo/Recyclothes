package com.recyclothes.fx.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class FxChartUtil
{
    // Create PieChart Data
    public static ObservableList<PieChart.Data> getChartData()
    {
        ObservableList<PieChart.Data> data = FXCollections. observableArrayList();
        data.add(new PieChart.Data("China", 1275));
        data.add(new PieChart.Data("India", 1017));
        data.add(new PieChart.Data("Brazil", 172));
        data.add(new PieChart.Data("UK", 59));
        data.add(new PieChart.Data("USA", 285));
        return data;
    }

    // Create XYChart Country Data
    public static ObservableList<XYChart.Series<Number, Number>> getCountrySeries()
    {
        XYChart.Series<Number, Number> seriesChina = new XYChart.Series<Number, Number>();
        seriesChina.setName("China");
        seriesChina.getData().add(new XYChart.Data<Number, Number>(1950, 555));
        seriesChina.getData().add(new XYChart.Data<Number, Number>(2000, 1275));
        seriesChina.getData().add(new XYChart.Data<Number, Number>(2050, 1395));
        seriesChina.getData().add(new XYChart.Data<Number, Number>(2100, 1182));
        seriesChina.getData().add(new XYChart.Data<Number, Number>(2150, 1149));

        XYChart.Series<Number, Number> seriesIndia = new XYChart.Series<Number, Number>();
        seriesIndia.setName("India");
        seriesIndia.getData().add(new XYChart.Data<Number, Number>(1950, 358));
        seriesIndia.getData().add(new XYChart.Data<Number, Number>(2000, 1017));
        seriesIndia.getData().add(new XYChart.Data<Number, Number>(2050, 1531));
        seriesIndia.getData().add(new XYChart.Data<Number, Number>(2100, 1458));
        seriesIndia.getData().add(new XYChart.Data<Number, Number>(2150, 1308));

        XYChart.Series<Number, Number> seriesUSA = new XYChart.Series<Number, Number>();
        seriesUSA.setName("USA");
        seriesUSA.getData().add(new XYChart.Data<Number, Number>(1950, 158));
        seriesUSA.getData().add(new XYChart.Data<Number, Number>(2000, 285));
        seriesUSA.getData().add(new XYChart.Data<Number, Number>(2050, 409));
        seriesUSA.getData().add(new XYChart.Data<Number, Number>(2100, 437));
        seriesUSA.getData().add(new XYChart.Data<Number, Number>(2150, 453));

        ObservableList<XYChart.Series<Number, Number>> data =
                FXCollections.<XYChart.Series<Number, Number>>observableArrayList();
        data.add(seriesChina);
        data.add(seriesIndia);
        data.add(seriesUSA);
        return data;
    }

    // Create XYChart Year Data

}