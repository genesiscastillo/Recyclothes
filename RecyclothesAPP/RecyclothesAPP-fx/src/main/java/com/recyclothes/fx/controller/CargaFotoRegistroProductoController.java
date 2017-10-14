package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.FotoProductoDTO;
import com.recyclothes.common.dto.ProductoDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ResourceBundle;

/**
 * Created by Cesar on 18-08-2016.
 */
public class CargaFotoRegistroProductoController implements Initializable , ControlledScreen {

    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CargaFotoRegistroProductoController.class);

    ScreensController screensController;
    Deque<Long> dequeIdFotoProducto = new ArrayDeque<>();
    BigInteger paginaSeleccionada = BigInteger.ONE;
    @FXML
    Button buttonPagSiguiente;
    @FXML
    Button buttonPagAnterior;
    @FXML
    HBox hBox;
    @FXML
    ComboBox<CatalogoEnum> comboBoxCatalogoEnum;
    @FXML
    ComboBox<TallaEnum> comboBoxTallaEnum;
    @FXML
    TextArea textAreaDescripcion;
    @FXML
    TextField textFieldPrecio;
    @FXML
    Button buttonGrabar;
    @FXML
    Button buttonCargarFotos;
    @FXML
    FlowPane flowPane;
    @FXML
    FlowPane flowPaneFotos;
    Long totalPaginas = 1l;
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
                    screensController.getSession().getBasicRemote().sendText(jsonObject.toString());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void updateData(final JSONObject jsonMessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                String action = (String) jsonMessage.get("action");
                AccionEnum accionEnum = AccionEnum.valueOf(action);
                switch (accionEnum) {
                    case ADMIN_CARGAR_FOTOS_REGISTRO_PRODUCTO:
                        try {
                            totalPaginas = (Long)jsonMessage.get("totalPaginas");
                            String lisIdFotoProducto = (String) jsonMessage.get("lisIdFotoProducto");
                            JSONArray jsonArray = (JSONArray) new JSONParser().parse(new StringReader(lisIdFotoProducto));
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                                Long idFotoProducto = (Long) jsonObject1.get("idFotoProducto");
                                dequeIdFotoProducto.add(idFotoProducto);
                                JSONObject jsonObject2 = new JSONObject();
                                jsonObject2.put("action", AccionEnum.ADMIN_CARGAR_FOTO_PRODUCTO.name());
                                jsonObject2.put("idFotoProducto", idFotoProducto);
                                LOGGER.info("SEND -- >> " + jsonObject2);
                                sendToServerWebSocket(jsonObject2);
                            }
                            LOGGER.info("paginaActual "+paginaSeleccionada.intValue());
                            LOGGER.info("totalPaginas "+totalPaginas.intValue());
                            buttonPagAnterior.setDisable(Boolean.FALSE);
                            buttonPagSiguiente.setDisable(Boolean.FALSE);
                            if(paginaSeleccionada.intValue() == 1){
                                buttonPagAnterior.setDisable(Boolean.TRUE);
                            }else if(paginaSeleccionada.intValue() == totalPaginas.intValue()  )  {
                                buttonPagSiguiente.setDisable(Boolean.TRUE);
                            }
                        } catch (Exception e) {
                            LOGGER.error("updateData ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS", e);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void receiverByteBuffer(final ByteBuffer byteBuffer) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addImageFotoProducto(byteBuffer);
            }
        });
    }

    private void addImageFotoProducto(ByteBuffer byteBuffer)    {
        Image image = new Image(new ByteArrayInputStream(byteBuffer.array()));
        final ImageView imageView = new ImageView(image);
        imageView.setFitHeight(250);
        imageView.setFitWidth(250);
        final FotoProductoDTO fotoProductoDTO = new FotoProductoDTO();
        fotoProductoDTO.setIdFotoProducto(dequeIdFotoProducto.poll());
        fotoProductoDTO.setFoto(byteBuffer.array());
        imageView.setUserData(fotoProductoDTO);
        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(flowPaneFotos.getChildren().size() < 5) {
                    final ImageView imageView1 = new ImageView(new Image(new ByteArrayInputStream(fotoProductoDTO.getFoto())));
                    imageView1.setUserData(fotoProductoDTO);
                    imageView1.setFitWidth(200);
                    imageView1.setFitHeight(200);
                    imageView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            flowPane.getChildren().add(imageView);
                            flowPaneFotos.getChildren().remove(imageView1);
                        }
                    });
                    flowPaneFotos.getChildren().add(imageView1);
                    flowPane.getChildren().remove(imageView);
                }
            }
        });
        flowPane.getChildren().add(imageView);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flowPaneFotos.getChildren().clear();
        comboBoxCatalogoEnum.setStyle("-fx-background-color: aliceblue");
        comboBoxTallaEnum.setStyle("-fx-background-color: aliceblue");
        textAreaDescripcion.setStyle("-fx-font-size: 22pt;");
        textFieldPrecio.setStyle("-fx-font-size: 22pt;");
        textFieldPrecio.setAlignment(Pos.BASELINE_RIGHT);
        textFieldPrecio.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldPrecio.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if( !textFieldPrecio.getText().isEmpty() && !flowPaneFotos.getChildren().isEmpty() ) {
                    buttonGrabar.setDisable(Boolean.FALSE);
                }else{
                    buttonGrabar.setDisable(Boolean.TRUE);
                }
            }
        });
        comboBoxCatalogoEnum.getItems().addAll(CatalogoEnum.values());
        comboBoxCatalogoEnum.getSelectionModel().selectFirst();
        comboBoxTallaEnum.getItems().addAll(TallaEnum.values());
        comboBoxTallaEnum.getSelectionModel().selectFirst();
        buttonGrabar.setDisable(Boolean.TRUE);
        buttonGrabar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int size = flowPaneFotos.getChildren().size();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action", AccionEnum.GENERAR_PRODUCTO_NUEVO.name());
                ProductoDTO productoDTO = new ProductoDTO();
                productoDTO.setEstadoProductoEnum(EstadoProductoEnum.PENDIENTE);
                productoDTO.setCatalogoEnum(comboBoxCatalogoEnum.getValue());
                productoDTO.setTallaEnum(comboBoxTallaEnum.getValue());
                productoDTO.setDescripcion(textAreaDescripcion.getText());
                productoDTO.setPrecioProducto(Long.valueOf(textFieldPrecio.getText()));
                boolean status = true;
                JSONArray jsonArray = new JSONArray();
                for(int i = 0 ; i < size ; i++) {
                    ImageView imageView = (ImageView)flowPaneFotos.getChildren().get(i);
                    FotoProductoDTO fotoProductoDTO = (FotoProductoDTO)imageView.getUserData();
                    if(status){
                        productoDTO.setIdFotoProducto(fotoProductoDTO.getIdFotoProducto());
                        status = false;
                    }
                    jsonArray.add(fotoProductoDTO.toJSONObject());
                }
                jsonObject.put("producto", productoDTO.toJSONObject());
                jsonObject.put("fotoProductos" ,jsonArray.toJSONString());
                sendToServerWebSocket(jsonObject);
                flowPaneFotos.getChildren().clear();
                textAreaDescripcion.setText("");
                textFieldPrecio.setText("");
            }
        });
        ClickHandler clickHandler = new ClickHandler();
        buttonCargarFotos.setOnMouseClicked( clickHandler );
        buttonPagAnterior.setOnMouseClicked(clickHandler);
        buttonPagSiguiente.setOnMouseClicked(clickHandler);
    }

    public void iniciarCargaFotosProductos( Long paginaSeleccionada)    {
        textAreaDescripcion.setText("");
        textFieldPrecio.setText("");
        flowPaneFotos.getChildren().clear();
        flowPane.getChildren().clear();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action" , AccionEnum.ADMIN_CARGAR_FOTOS_REGISTRO_PRODUCTO.name());
        jsonObject.put("paginaSeleccionada" , paginaSeleccionada);
        sendToServerWebSocket( jsonObject );
    }

    public class ClickHandler implements EventHandler<MouseEvent>    {

        @Override
        public void handle (MouseEvent mouseEvent){
            Button button = (Button) mouseEvent.getSource();
            if (button.equals(buttonCargarFotos)) {
                iniciarCargaFotosProductos( paginaSeleccionada.longValue() );
            }else if(button.equals(buttonPagAnterior))  {
                paginaSeleccionada = paginaSeleccionada.subtract(BigInteger.ONE);
                iniciarCargaFotosProductos(paginaSeleccionada.longValue());
            }else if(button.equals(buttonPagSiguiente)) {
                paginaSeleccionada = paginaSeleccionada.add(BigInteger.ONE);
                iniciarCargaFotosProductos(paginaSeleccionada.longValue());
            }
        }
    }
}
