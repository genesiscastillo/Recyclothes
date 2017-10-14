package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.EstadisticaDTO;
import com.recyclothes.common.dto.KeyValueDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class EstadisticaProductosController extends GridPane implements ControlledScreen  {

    static final Logger LOGGER = Logger.getLogger(EstadisticaProductosController.class);

//    Button button = new Button("RESERVADO NIÑO");
    ScreensController screensController;
    ObservableList<XYChart.Series<String, Number>> chartData[] = new ObservableList[CatalogoEnum.values().length];
    ObservableList<XYChart.Series<String, Number>> data[] = new ObservableList[CatalogoEnum.values().length];
    XYChart.Series dataSeries[][] = new XYChart.Series[CatalogoEnum.values().length][EstadoProductoEnum.values().length];

    XYChart.Data<String, Number> dato[][][] = new XYChart.Data[CatalogoEnum.values().length][EstadoProductoEnum.values().length][TallaEnum.values().length];

    public EstadisticaProductosController(ScreensController screensController) {
        this.screensController = screensController;
        for(CatalogoEnum catalogoEnum : CatalogoEnum.values()) {
            for (EstadoProductoEnum estadoProductoEnum : EstadoProductoEnum.values()) {
                dataSeries[catalogoEnum.ordinal()][estadoProductoEnum.ordinal()]=new XYChart.Series();
                dataSeries[catalogoEnum.ordinal()][estadoProductoEnum.ordinal()].setName(estadoProductoEnum.name());
            }
        }
        CategoryAxis xAxisCategoria[] = new CategoryAxis[CatalogoEnum.values().length];
        NumberAxis yAxisLeyenda[] = new NumberAxis[CatalogoEnum.values().length];



        for(CatalogoEnum catalogoEnum : CatalogoEnum.values()) {
            xAxisCategoria[catalogoEnum.ordinal()] = new CategoryAxis();
            // Set the Label for the Axis
            xAxisCategoria[catalogoEnum.ordinal()].setLabel("Tallas");
            // Add the Categories to the Axis
            for(TallaEnum tallaEnum :TallaEnum.values()) {
                xAxisCategoria[catalogoEnum.ordinal()].getCategories().add(tallaEnum.name());
            }
            // Create the Y-Axis
            yAxisLeyenda[catalogoEnum.ordinal()] = new NumberAxis();
            yAxisLeyenda[catalogoEnum.ordinal()].setLabel("Population (in millions)");
        }

        // Create the Chart
        OnListernerListChange onListernerListChange = new OnListernerListChange();
        StackedBarChart<String, Number> stackedBarChart[] = new StackedBarChart[CatalogoEnum.values().length];
        for(CatalogoEnum catalogoEnum : CatalogoEnum.values()){
            stackedBarChart[catalogoEnum.ordinal()] = new StackedBarChart<>(xAxisCategoria[catalogoEnum.ordinal()], yAxisLeyenda[catalogoEnum.ordinal()]);
            stackedBarChart[catalogoEnum.ordinal()].setTitle(catalogoEnum.name());
            chartData[catalogoEnum.ordinal()] = FXCollections.emptyObservableList();
            chartData[catalogoEnum.ordinal()].addListener(onListernerListChange);
            stackedBarChart[catalogoEnum.ordinal()].setData(chartData[catalogoEnum.ordinal()]);
            add(stackedBarChart[catalogoEnum.ordinal()], catalogoEnum.ordinal() , 0);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action" ,AccionEnum.ADMIN_OBTENER_ESTADISTICA_POR_ESTADO.name());
        sendToServerWebSocket(jsonObject);
        LOGGER.info("-------------- DISPARA ESTADISTICAS...!");
    }

    public class OnListernerListChange implements ListChangeListener<XYChart.Series<String, Number>>{

        @Override
        public void onChanged(Change<? extends XYChart.Series<String, Number>> change) {
        }
    }

    @Override
    public void setScreenParent(ScreensController screensController) {
        this.screensController = screensController;
    }

    @Override
    public void receiverByteBuffer(ByteBuffer byteBuffer) {

    }

    @Override
    public void updateData(final JSONObject jsonObject) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

        AccionEnum accionEnum = AccionEnum.valueOf((String) jsonObject.get("action"));
        try {
            switch(accionEnum){
                case ADMIN_OBTENER_ESTADISTICA_POR_ESTADO:
                    LOGGER.info("EstadisticaProductosController ADMIN_OBTENER_ESTADISTICA_POR_ESTADO "+jsonObject.toString());
                    List<EstadisticaDTO> estadisticaDTOs = new ArrayList<>();
                    String estadisticas = (String) jsonObject.get("estadisticas");
                    CatalogoEnum catalogoEnum2 = CatalogoEnum.valueOf((String)jsonObject.get("catalogoEnum"));
                    data[catalogoEnum2.ordinal()] = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
                    JSONArray jsonArray2 = (JSONArray) new JSONParser().parse(new StringReader(estadisticas));
                    for(int i = 0 ; i < jsonArray2.size() ; i++){
                        EstadisticaDTO estadisticaDTO = new EstadisticaDTO((JSONObject) jsonArray2.get(i));
                        estadisticaDTOs.add(estadisticaDTO);
                    }
                    data[catalogoEnum2.ordinal()] = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
                    for(EstadisticaDTO estadisticaDTO : estadisticaDTOs){
                        for(KeyValueDTO<TallaEnum , Long> keyValueDTO : estadisticaDTO.getKeyValueDTOs()) {
                            dataSeries[catalogoEnum2.ordinal()][estadisticaDTO.getEstadoProductoEnum().ordinal()].getData().add(new XYChart.Data<String, Number>(((TallaEnum) keyValueDTO.getKey()).name(), (Long) keyValueDTO.getValue()));
                        }
                        data[catalogoEnum2.ordinal()].add(dataSeries[catalogoEnum2.ordinal()][estadisticaDTO.getEstadoProductoEnum().ordinal()]);
                    }
                    chartData[catalogoEnum2.ordinal()] = data[catalogoEnum2.ordinal()];
                    break;
                default:

                String productos = (String) jsonObject.get("productos");
                JSONArray jsonArray = (JSONArray) new JSONParser().parse(new StringReader(productos));
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    JSONObject jsonObject2 = (JSONObject) jsonObject1.get("catalogoEnum");
                    CatalogoEnum catalogoEnum = CatalogoEnum.valueOf((String) jsonObject2.get("name"));
                    EstadoProductoEnum estadoProductoEnum = EstadoProductoEnum.valueOf((String) jsonObject1.get("estadoProductoEnum"));
                    JSONObject jsonObject3 = (JSONObject) jsonObject1.get("tallaEnum");
                    TallaEnum tallaEnum = TallaEnum.valueOf((String) jsonObject3.get("name"));
                    XYChart.Data<String, Number> algo = (XYChart.Data<String, Number>) dataSeries[catalogoEnum.ordinal()][estadoProductoEnum.ordinal()].getData().get(tallaEnum.ordinal());
                    algo.setYValue(algo.getYValue().intValue() + 1);
                    switch (estadoProductoEnum) {
                        case PENDIENTE:
                            break;
                        case DISPONIBLE:
                            XYChart.Data<String, Number> algo2 = (XYChart.Data<String, Number>) dataSeries[catalogoEnum.ordinal()][EstadoProductoEnum.PENDIENTE.ordinal()].getData().get(tallaEnum.ordinal());
                            algo2.setYValue(algo.getYValue().intValue() - 1);
                            break;
                        case RESERVADO:
                            XYChart.Data<String, Number> algo3 = (XYChart.Data<String, Number>) dataSeries[catalogoEnum.ordinal()][EstadoProductoEnum.DISPONIBLE.ordinal()].getData().get(tallaEnum.ordinal());
                            algo3.setYValue(algo.getYValue().intValue() - 1);
                            break;
                        case ENTREGADO:
                            XYChart.Data<String, Number> algo4 = (XYChart.Data<String, Number>) dataSeries[catalogoEnum.ordinal()][EstadoProductoEnum.RESERVADO.ordinal()].getData().get(tallaEnum.ordinal());
                            algo4.setYValue(algo.getYValue().intValue() - 1);
                            break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
            }
        });
    }

    @Override
    public void sendToServerWebSocket(final JSONObject jsonObject) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("sendToServerWebSocket "+(String)jsonObject.get("action"));
                try {
                    screensController.getSession().getBasicRemote().sendText(jsonObject.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public ObservableList<XYChart.Series<String, Number>> getYearSeries( CatalogoEnum catalogoEnum) {
        data[catalogoEnum.ordinal()] = FXCollections.<XYChart.Series<String, Number>>observableArrayList();
        try {
/*
            ProductosEJB productosEJB = (ProductosEJB) EJBLocator.locateEJB(ProductosEJB.class);
            List<EstadisticaDTO> estadisticaDTOs = productosEJB.obtenerEstaditicaPorEstado(catalogoEnum);
*/
            List<EstadisticaDTO> estadisticaDTOs = new ArrayList<>();
            for(EstadisticaDTO estadisticaDTO : estadisticaDTOs){
                for(KeyValueDTO<TallaEnum , Long> keyValueDTO : estadisticaDTO.getKeyValueDTOs()) {
                    dataSeries[catalogoEnum.ordinal()][estadisticaDTO.getEstadoProductoEnum().ordinal()].getData().add(new XYChart.Data<String, Number>(((TallaEnum) keyValueDTO.getKey()).name(), (Long) keyValueDTO.getValue()));
                }
                data[catalogoEnum.ordinal()].add(dataSeries[catalogoEnum.ordinal()][estadisticaDTO.getEstadoProductoEnum().ordinal()]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return data[catalogoEnum.ordinal()];
    }
}