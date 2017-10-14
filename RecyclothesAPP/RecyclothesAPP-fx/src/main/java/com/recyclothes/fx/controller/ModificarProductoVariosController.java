package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.DetalleProductoDTO;
import com.recyclothes.common.dto.FotoProductoDTO;
import com.recyclothes.common.dto.ProductoDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.fx.service.HTTP_post;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


/**
 * Created by Cesar on 29-08-2016.
 */
public class ModificarProductoVariosController implements Initializable, ControlledScreen {
    static final Logger LOGGER = Logger.getLogger(ModificarProductoVariosController.class);

    ScreensController screensController;

    @FXML
    ComboBox<CatalogoEnum> comboBoxCatalogoEnum;
    @FXML
    ComboBox<TallaEnum> comboBoxTallaEnum;
    @FXML
    ComboBox<EstadoProductoEnum> comboBoxEstadoProductoEnum;
    @FXML
    Label labelTotalPrendas;
    @FXML
    ScrollPane scrollPane;

    Long numeroPaginaSeleccionada = 1L;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            comboBoxCatalogoEnum.getItems().addAll(CatalogoEnum.values());
            comboBoxTallaEnum.getItems().addAll(TallaEnum.values());
            comboBoxEstadoProductoEnum.getItems().addAll( getEstadoProductoEnums() );
            comboBoxCatalogoEnum.getSelectionModel().selectFirst();
            comboBoxTallaEnum.getSelectionModel().selectFirst();

            comboBoxCatalogoEnum.valueProperty().addListener(new ComboListener<CatalogoEnum>());
            comboBoxTallaEnum.valueProperty().addListener(new ComboListener<TallaEnum>());
            comboBoxEstadoProductoEnum.valueProperty().addListener(new ComboListener<EstadoProductoEnum>());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ComboListener<T> implements ChangeListener<T>{
        @Override
        public void changed(ObservableValue<? extends T> observableValue, T t, T t1) {
            addProductosPanel();
        }
    }

    public class PanelDetalleProducto extends GridPane {
        Stage dialog = new Stage();
        CatalogoEnum catalogoEnum ;
        VBox vBox = new VBox(3);
        public PanelDetalleProducto(CatalogoEnum catalogoEnum , DetalleProductoDTO detalleProductoDTO) {
            super();
            this.catalogoEnum = catalogoEnum;
            build(detalleProductoDTO);
        }

        public String getParColor() {
            return CatalogoEnum.NINAS.equals(catalogoEnum) ? "-fx-background-color: lightpink;" : "-fx-background-color: burlywood;";
        }

        public void build(final DetalleProductoDTO detalleProductoDTO){
            final ProductoDTO productoDTO = detalleProductoDTO.getProductoDTO();
            final List<FotoProductoDTO> fotoProductoDTOs = detalleProductoDTO.getFotoProductoDTOs();
            try {
                this.setAlignment(Pos.CENTER);
                this.setStyle("-fx-border-color: black; " + getParColor());
                this.setHgap(5);
                this.setVgap(5);
                this.setPadding(new Insets(15, 15, 15, 15));
                vBox.setPrefWidth(250);

                HBox hBox = new HBox();

                Text textIdProducto = new Text();
                textIdProducto.setText(String.valueOf(productoDTO.getIdProducto()));
                textIdProducto.setTextAlignment(TextAlignment.LEFT);
                textIdProducto.setWrappingWidth(150);

                Text textFechaCreacion = new Text();
                textFechaCreacion.setText(Utils.getDiaFechaMes(productoDTO.getFecIngreso()));
                textFechaCreacion.setTextAlignment(TextAlignment.RIGHT);
                textFechaCreacion.setWrappingWidth(150);
                hBox.getChildren().addAll(textIdProducto, textFechaCreacion);

                vBox.getChildren().add(hBox);


                Image image = new Image(new ByteArrayInputStream(fotoProductoDTOs.get(0).getFoto()));
                final ImageView imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setFitHeight(300);
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            dialog.initModality(Modality.APPLICATION_MODAL);
                            PanelProducto panelProducto = new PanelProducto(dialog, detalleProductoDTO);
                            Scene dialogScene = new Scene(panelProducto);
                            dialog.setScene(dialogScene);
                            dialog.showAndWait();
                            TimeUnit.SECONDS.sleep(2);
                            addProductosPanel();
                        }catch(InterruptedException e){
                            LOGGER.error(e);
                        }
                    }
                });
                vBox.getChildren().add(imageView);

                HBox hBox1 = new HBox();
                ImageView[] imageViews = new ImageView[fotoProductoDTOs.size()];
                int i = 0 ;
                for(FotoProductoDTO fotoProductoDTO : fotoProductoDTOs){
                    Image image1 = new Image(new ByteArrayInputStream(fotoProductoDTO.getFoto()));
                    imageViews[i] = new ImageView(image1);
                    imageViews[i].setFitHeight(100);
                    imageViews[i].setFitWidth(100);
                    hBox1.getChildren().add(imageViews[i++]);
                }
                vBox.getChildren().add(hBox1);

                Text textDescripcion   = new Text(productoDTO.getDescripcion());
                Text textPrecio        = new Text( String.valueOf(productoDTO.getPrecioProducto()));

                textDescripcion.setFont(Font.font("Verdana", 20));
                textDescripcion.setFill(Color.RED);
                textDescripcion.setWrappingWidth(300);
                vBox.getChildren().add(textDescripcion);

                textPrecio.setTextAlignment(TextAlignment.RIGHT);
                textPrecio.setFont(Font.font("Verdana", 21));
                textPrecio.setFill(Color.BLUE);
                textPrecio.setWrappingWidth(300);
                vBox.getChildren().add(textPrecio);
                if(EstadoProductoEnum.PENDIENTE.equals(productoDTO.getEstadoProductoEnum())) {
                    final CheckBox checkBox = new CheckBox("Disponibilizar");
                    checkBox.setStyle("-fx-text-fill: red");
                    checkBox.setPrefWidth(300);
                    checkBox.setAlignment(Pos.BASELINE_CENTER);
                    checkBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            imageView.setOnMouseClicked(null);
                            checkBox.setDisable(Boolean.TRUE);
                            checkBox.setText("DISPONIBLE");
                            checkBox.setStyle("-fx-text-fill: blue");
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("action", AccionEnum.ADMIN_DISPONIBILIZAR_PRODUCTO.name());
                            jsonObject.put("idProducto", productoDTO.getIdProducto());
                            sendToServerWebSocket(jsonObject);
                        }
                    });
                    vBox.getChildren().add(checkBox);
                }
                this.add(vBox , 0 , 1);

            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class PanelProducto extends GridPane {
        ProductoDTO productoDTOTemp = new ProductoDTO();
        List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<>();
        Stage stage;

        ComboBox<CatalogoEnum> comboBoxCatalogoEnum = new ComboBox<>();
        ComboBox<TallaEnum> comboBoxTallaEnum = new ComboBox<>();
        TextArea textAreaDescripcion = new TextArea();
        TextField textFieldPrecio = new TextField();

        VBox vBox = new VBox();

        public PanelProducto(Stage stage , DetalleProductoDTO detalleProductoDTO) {
            super();
            this.productoDTOTemp = detalleProductoDTO.getProductoDTO();
            this.fotoProductoDTOs = detalleProductoDTO.getFotoProductoDTOs();
            this.stage = stage;
            build();
        }

        private void build() {
            try {
                this.setAlignment(Pos.CENTER);
                this.setStyle("-fx-border-color: purple; -fx-background-color: #395bae;");
                vBox.setPrefWidth(250);
                vBox.setPadding(new Insets(15, 15, 15, 15));
                vBox.setSpacing(5);

                comboBoxCatalogoEnum.getItems().addAll(CatalogoEnum.values());
                comboBoxCatalogoEnum.getSelectionModel().select(productoDTOTemp.getCatalogoEnum());
                comboBoxCatalogoEnum.setPrefWidth(250);
                comboBoxCatalogoEnum.setStyle("-fx-background-color: white");
                vBox.getChildren().add(comboBoxCatalogoEnum);

                comboBoxTallaEnum.getItems().addAll(TallaEnum.values());
                comboBoxTallaEnum.getSelectionModel().select(productoDTOTemp.getTallaEnum());
                comboBoxTallaEnum.setPrefWidth(250);
                comboBoxTallaEnum.setStyle("-fx-background-color: white");
                vBox.getChildren().add(comboBoxTallaEnum);

                Image image = new Image(new ByteArrayInputStream(fotoProductoDTOs.get(0).getFoto()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(300);
                imageView.setFitHeight(300);
                vBox.getChildren().addAll(imageView);

                HBox hBox = new HBox();
                ImageView[] imageViews = new ImageView[fotoProductoDTOs.size()];
                int j = 0;
                for (FotoProductoDTO fotoProductoDTO : fotoProductoDTOs) {
                    Image image2 = new Image(new ByteArrayInputStream(fotoProductoDTO.getFoto()));
                    imageViews[j] = new ImageView(image2);
                    imageViews[j].setFitWidth(100);
                    imageViews[j].setFitHeight(100);
                    hBox.getChildren().add(imageViews[j++]);
                }
                vBox.getChildren().addAll(hBox);

                textAreaDescripcion.setPrefWidth(250);
                textAreaDescripcion.setPrefHeight(60);
                textAreaDescripcion.setText(productoDTOTemp.getDescripcion());
                vBox.getChildren().add(textAreaDescripcion);

                textFieldPrecio.setPrefWidth(250);
                textFieldPrecio.setText(String.valueOf(productoDTOTemp.getPrecioProducto()));
                textFieldPrecio.setAlignment(Pos.BOTTOM_RIGHT);
                textFieldPrecio.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            textFieldPrecio.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });

                vBox.getChildren().add(textFieldPrecio);

                Button buttonGrabar = new Button("Grabar");
                buttonGrabar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        productoDTOTemp.setDescripcion(textAreaDescripcion.getText());
                        productoDTOTemp.setPrecioProducto(Long.valueOf(textFieldPrecio.getText()));

                        actualizarDatosProducto(productoDTOTemp);

                        stage.close();
                    }
                });
                HBox hBox1 = new HBox();
                hBox1.getChildren().add(buttonGrabar);
/*
                if( EstadoProductoEnum.PENDIENTE.equals(productoDTOTemp.getEstadoProductoEnum() )) {
                    Button buttonDisponibilizar = new Button("Disponibilizar");
                    buttonDisponibilizar.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {

                            cambiarEstadoProductoCatalogo(productoDTOTemp.getIdProducto() , EstadoProductoEnum.DISPONIBLE);

                            stage.close();
                        }
                    });
                    hBox1.getChildren().add(buttonDisponibilizar);
                }else if( EstadoProductoEnum.DISPONIBLE.equals(productoDTOTemp.getEstadoProductoEnum())){
                    Button buttonNODisponible = new Button("No DISPONIBLE");
                    buttonNODisponible.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {

                            cambiarEstadoProductoCatalogo(productoDTOTemp.getIdProducto() , EstadoProductoEnum.PENDIENTE);

                            stage.close();
                        }
                    });
                    hBox1.getChildren().add(buttonNODisponible);
                }
*/

                vBox.getChildren().add(hBox1);

                this.add(vBox, 0, 1);

            }
            catch (Exception e)            {
                e.printStackTrace();
            }
        }
    }
    private void actualizarDatosProducto(ProductoDTO productoDTOTemp){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action",AccionEnum.ADMIN_ACTUALIZAR_PRODUCTO.name());
        jsonObject.put("producto", productoDTOTemp.toJSONObject());
        sendToServerWebSocket(jsonObject);
    }

    private List<EstadoProductoEnum> getEstadoProductoEnums()   {
        List<EstadoProductoEnum> estadoProductoEnums = new ArrayList<>();
        estadoProductoEnums.add(EstadoProductoEnum.PENDIENTE);
        estadoProductoEnums.add(EstadoProductoEnum.DISPONIBLE);
        return estadoProductoEnums;
    }

    @Override
    public void setScreenParent(ScreensController screensController) {
        this.screensController = screensController;
    }

    @Override
    public void sendToServerWebSocket(final JSONObject jsonObject) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("sendToServerWebSocket "+(String)jsonObject.get("action"));
                try {
                    LOGGER.info("-->sendToServerWebSocket" + jsonObject);
                    screensController.getSession().getBasicRemote().sendText(jsonObject.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void addProductosPanel() {
        scrollPane.setContent(null);
        CatalogoEnum catalogoEnum = comboBoxCatalogoEnum.getSelectionModel().getSelectedItem();
        TallaEnum tallaEnum = comboBoxTallaEnum.getSelectionModel().getSelectedItem();
        EstadoProductoEnum estadoProductoEnum = comboBoxEstadoProductoEnum.getSelectionModel().getSelectedItem();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action" , AccionEnum.ADMIN_AGREGAR_PRODUCTOS.name());
        jsonObject.put("catalogo",catalogoEnum.name());
        jsonObject.put("talla", tallaEnum.name());
        jsonObject.put("estadoProducto" , estadoProductoEnum.name());
        jsonObject.put("numeroPagina" , numeroPaginaSeleccionada);
        sendToServerWebSocket(jsonObject);
    }

    @Override
    public void updateData(final JSONObject jsonMessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONParser jsonParser = new JSONParser();
                    CatalogoEnum catalogoEnum = comboBoxCatalogoEnum.getValue();
                    JSONArray jsonArrayB = (JSONArray) jsonParser.parse((String) jsonMessage.get("productos"));
                    Long totalPrendas = (Long) jsonMessage.get("totalPrendas");
                    Long totalPaginacion = (Long) jsonMessage.get("totalPaginacion");
                    List<DetalleProductoDTO> detalleProductoDTOs = new ArrayList<>();
                    for (int i = 0; i < jsonArrayB.size(); i++) {
                        DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
                        JSONObject jsonObject = (JSONObject) jsonArrayB.get(i);
                        JSONObject jsonObjectProducto = (JSONObject) jsonObject.get("producto");
                        LOGGER.info("\n--->" + jsonObjectProducto.toString());
                        ProductoDTO productoDTO = new ProductoDTO(jsonObjectProducto);
                        detalleProductoDTO.setProductoDTO(productoDTO);
                        JSONArray jsonArray1 = (JSONArray) jsonParser.parse((String) jsonObject.get("fotoProductos"));
                        for (int j = 0; j < jsonArray1.size(); j++) {
                            JSONObject jsonObject1 = (JSONObject) jsonArray1.get(j);
                            JSONObject jsonObjectFotoProducto = (JSONObject) jsonObject1.get("fotoProducto");
                            LOGGER.info("\n--->>>>>" + jsonObjectFotoProducto.toString());
                            FotoProductoDTO fotoProductoDTO = new FotoProductoDTO(jsonObjectFotoProducto);
                            byte[] bytes = HTTP_post.getImage(fotoProductoDTO.getIdFotoProducto());
                            fotoProductoDTO.setFoto(bytes);
                            detalleProductoDTO.addFotoProductoDTO(fotoProductoDTO);
                        }
                        detalleProductoDTOs.add(detalleProductoDTO);
                    }
                    FlowPane flowPane = new FlowPane();
                    flowPane.setPrefWidth(1024);
                    flowPane.setAlignment(Pos.CENTER);
                    for (DetalleProductoDTO detalleProductoDTO : detalleProductoDTOs) {
                        if (detalleProductoDTO.getFotoProductoDTOs().size() > 0) {
                            PanelDetalleProducto panelDetalleProducto = new PanelDetalleProducto(catalogoEnum, detalleProductoDTO);
                            flowPane.getChildren().add(panelDetalleProducto);
                        }
                    }
                    scrollPane.setContent(flowPane);
                    Long totalMuestra = numeroPaginaSeleccionada * flowPane.getChildren().size();
                    labelTotalPrendas.setText(String.valueOf(totalMuestra) + "/" +String.valueOf(totalPrendas));

                } catch (Exception e) {
                    LOGGER.error("updateData", e);
                }
            }
        });
    }


    @Override
    public void receiverByteBuffer(final ByteBuffer byteBuffer) {
    }
}
