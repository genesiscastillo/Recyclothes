package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.ClienteDTO;
import com.recyclothes.common.dto.DetalleReservaDTO;
import com.recyclothes.common.dto.ProductoDTO;
import com.recyclothes.common.dto.ReservaDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.enums.EstadoReservaEnum;
import com.recyclothes.common.enums.HoraEntregaEnum;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.fx.property.ReservaProperty;
import com.recyclothes.fx.service.HTTP_post;
import extfx.scene.control.DatePicker;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Cesar on 25-07-2016.
 */
public class AgendarReservaProductoController implements Initializable , ControlledScreen {

    static final Logger LOGGER = Logger.getLogger(AgendarReservaProductoController.class);

    ScreensController screensController;

    @FXML
    AnchorPane anchorPane;

    @FXML
    TextField textFieldCodigoReserva;

    @FXML
    Button buttonBuscar;

    @FXML
    Button buttonRecargar;

    Button buttonAnular = new Button("ANULAR");
    Button buttonGrabar = new Button("GRABAR");

    @FXML
    TableView<ReservaProperty> tableViewReserva = new TableView<>();

    DetalleReservaPane detalleReservaPane = new DetalleReservaPane();
    ProductosReservaPane productosReservaPane;
    ComboBox<EstadoReservaEnum> comboBoxEstadoReserva = new ComboBox<>();
    TextArea textAreaDatosEntrega = new TextArea();
    ComboBox<HoraEntregaEnum> comboBoxHoraEntrega = new ComboBox<>();


    @Override
    public void receiverByteBuffer(ByteBuffer byteBuffer) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClickHandler clickHandler = new ClickHandler();
        buttonRecargar.setOnMouseClicked(clickHandler);
        buttonBuscar.setOnMouseClicked(clickHandler);
        tableViewReserva.setPrefWidth(990);
        TableColumn<ReservaProperty, String> codigoReservaCol = new TableColumn<>("codigoReserva");
        codigoReservaCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        codigoReservaCol.setCellValueFactory(new PropertyValueFactory("codigoReserva"));
        codigoReservaCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> clienteCol = new TableColumn<>("cliente");
        clienteCol.setCellValueFactory(new PropertyValueFactory("cliente"));
        clienteCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> idReservaCol = new TableColumn<>("correo");
        idReservaCol.setCellValueFactory(new PropertyValueFactory("correo"));
        idReservaCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> fechaReservaCol = new TableColumn<>("fechaReserva");
        fechaReservaCol.setEditable(true);
        fechaReservaCol.setCellValueFactory(new PropertyValueFactory("fechaReserva"));
        fechaReservaCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> estadoReservaEnumCol = new TableColumn<>("estadoReservaEnum");
        estadoReservaEnumCol.setCellValueFactory(new PropertyValueFactory("estadoReservaEnum"));
        estadoReservaEnumCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> fechaEntregaCol = new TableColumn<>("fechaEntrega");
        fechaEntregaCol.setCellValueFactory(new PropertyValueFactory("fechaEntrega"));
        fechaEntregaCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> horaEntregaCol = new TableColumn<>("horaEntregaEnum");
        horaEntregaCol.setCellValueFactory(new PropertyValueFactory("horaEntregaEnum"));
        horaEntregaCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);


        TableColumn<ReservaProperty, String> montoTotalCol = new TableColumn<>("montoTotal");
        montoTotalCol.setCellValueFactory(new PropertyValueFactory("montoTotal"));
        montoTotalCol.setPrefWidth(tableViewReserva.getPrefWidth() / 8);


        tableViewReserva.getColumns().setAll(codigoReservaCol, idReservaCol, clienteCol, fechaReservaCol, estadoReservaEnumCol, fechaEntregaCol, horaEntregaCol, montoTotalCol);

        tableViewReserva.setRowFactory(new Callback<TableView<ReservaProperty>, TableRow<ReservaProperty>>() {
            @Override
            public TableRow<ReservaProperty> call(TableView<ReservaProperty> tableView2) {
                final TableRow<ReservaProperty> row = new TableRow<ReservaProperty>() {
                    @Override
                    protected void updateItem(ReservaProperty reservaProperty, boolean empty) {
                        super.updateItem(reservaProperty, empty);
                        if (reservaProperty != null) {
                            if (reservaProperty.getReservaDTO().getFechaEntrega() == null && vencimientoFechaReserva(reservaProperty.getReservaDTO().getFechaReserva())) {
                                setStyle("-fx-text-fill: red");
                            }
                        }
                    }
                };
                row.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        final int index = row.getIndex();
                        anchorPane.getChildren().remove(productosReservaPane);
                        anchorPane.getChildren().remove(detalleReservaPane);
                        productosReservaPane = null;
                        detalleReservaPane = null;
                        if (row.getTableView().getSelectionModel().getSelectedIndex() == index) {
                            ReservaProperty reservaProperty = row.getTableView().getSelectionModel().getSelectedItem();
                            if (reservaProperty != null) {

                                detalleReservaPane = new DetalleReservaPane(reservaProperty.getReservaDTO());
                                detalleReservaPane.setLayoutX(15);
                                detalleReservaPane.setLayoutY(240);
                                anchorPane.getChildren().add(detalleReservaPane);

                                Long idReserva = reservaProperty.getReservaDTO().getIdReserva();
                                obtenerProductosReserva(idReserva);
                            }
                        }
                    }
                });
                return row;
            }
        });
        obtenerListaReservas();
    }

    private void obtenerProductosReserva(Long idReserva)    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action",AccionEnum.ADMIN_OBTENER_PRODUCTOS_RESERVA.name());
        jsonObject.put("idReserva", idReserva);
        sendToServerWebSocket(jsonObject);
    }
    private boolean vencimientoFechaReserva(Date date) {
        Boolean status = Boolean.FALSE;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        if (calendar.getTime().getTime() <= Calendar.getInstance().getTime().getTime()) {
            status = Boolean.TRUE;
        }
        return status;
    }

    @Override
    public void setScreenParent(ScreensController screensController) {
        this.screensController = screensController;
    }

    public class ProductosReservaPane extends GridPane {

        int totalProductos = 0;
        int indexSelected = 0;
        VBox vBox = new VBox();
        Label labelDescripcion = new Label();
        AnchorPane anchorPane = new AnchorPane();
        List<DetalleReservaDTO> detalleReservaDTOList = new ArrayList<>();
        Button buttonAnterior = new Button("Anterior");
        Button buttonSiguiente = new Button("Siguiente");

        public ProductosReservaPane(List<DetalleReservaDTO> detalleReservaDTOList) {
            super();
            this.detalleReservaDTOList = detalleReservaDTOList;
            build();
        }

        public void build() {
            totalProductos = detalleReservaDTOList.size();
            buttonAnterior.setDisable(Boolean.TRUE);
            if (totalProductos == 1) {
                buttonSiguiente.setDisable(Boolean.TRUE);
            }
            try {
                this.setAlignment(Pos.TOP_LEFT);
                this.setStyle("-fx-border-color: black; ");
                this.setHgap(5);
                this.setVgap(5);
                this.setPadding(new Insets(10, 10, 10, 10));
                this.setPrefWidth(548);
                this.setPrefHeight(360);
                vBox.setPrefWidth(200);
                DetalleReservaDTO detalleReservaDTO = detalleReservaDTOList.get(indexSelected);
                vBox.getChildren().add(labelDescripcion);
                actualizarImagen(detalleReservaDTO, indexSelected);
                anchorPane.setPrefWidth(450);
                anchorPane.setPrefHeight(345);
                this.add(vBox, 0, 0);
                this.add(anchorPane, 0, 1);
                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.getChildren().addAll(buttonAnterior, buttonSiguiente);
                this.add(hBox, 0, 2);
                buttonAnterior.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        indexSelected--;
                        buttonSiguiente.setDisable(Boolean.FALSE);
                        DetalleReservaDTO detalleReservaDTO1 = detalleReservaDTOList.get(indexSelected);
                        actualizarImagen(detalleReservaDTO1, indexSelected);
                        if (indexSelected == 0) {
                            buttonAnterior.setDisable(Boolean.TRUE);
                        }
                    }
                });
                buttonSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        indexSelected++;
                        buttonAnterior.setDisable(Boolean.FALSE);
                        DetalleReservaDTO detalleReservaDTO1 = detalleReservaDTOList.get(indexSelected);
                        actualizarImagen(detalleReservaDTO1, indexSelected);
                        if (indexSelected == totalProductos - 1) {
                            buttonSiguiente.setDisable(Boolean.TRUE);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void actualizarImagen(DetalleReservaDTO detalleReservaDTO, int indexSelected) {
            ProductoDTO productoDTO = detalleReservaDTO.getProductoDTO();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(productoDTO.getCatalogoEnum().name()).append(" ");
            stringBuilder.append(productoDTO.getTallaEnum().name()).append(" ");
            stringBuilder = new StringBuilder(StringUtils.rightPad(stringBuilder.toString(), 25));
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("[").append(productoDTO.getIdProducto()).append("] ");
            stringBuilder2.append(productoDTO.getDescripcion()).append(" ");
            stringBuilder2 = new StringBuilder(StringUtils.rightPad(stringBuilder2.toString(), 50));
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(Utils.getMonto(productoDTO.getPrecioProducto())).append(" ");
            stringBuilder3 = new StringBuilder(StringUtils.leftPad(stringBuilder3.toString(), 15));
            StringBuilder stringBuilder4 = new StringBuilder();
            stringBuilder4.append("(").append(indexSelected + 1).append("/").append(totalProductos).append(")");
            stringBuilder4 = new StringBuilder(StringUtils.leftPad(stringBuilder4.toString(), 6));
            StringBuilder stringBuilder5 = new StringBuilder();
            stringBuilder5.append(stringBuilder).append(stringBuilder2).append(stringBuilder3).append(stringBuilder4);
            labelDescripcion.setText(stringBuilder5.toString());
            Image image = new Image(new ByteArrayInputStream(HTTP_post.getImage(detalleReservaDTO.getProductoDTO().getIdFotoProducto())));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(340);
            imageView.setFitWidth(340);
            anchorPane.getChildren().add(imageView);
        }
    }

    public class DetalleReservaPane extends GridPane {

        ReservaDTO reservaDTO;
        TextField textFieldTelefono;
        DatePicker datePickerFechaEntrega;

        public DetalleReservaPane(ReservaDTO reservaDTO) {
            super();
            this.reservaDTO = reservaDTO;
            build();
        }

        public DetalleReservaPane() {
            super();
        }

        private void build() {
            this.setAlignment(Pos.CENTER);
            this.setStyle("-fx-border-color: black; ");
            this.setHgap(5);
            this.setVgap(5);
            this.setPadding(new Insets(10, 10, 10, 10));
            this.setPrefWidth(450);

            String sIdReserva = "Codigoo Reserva :   " + String.valueOf(reservaDTO.getIdReserva());
            Label labelIdReserva = new Label(sIdReserva);
            labelIdReserva.setPrefWidth(180);
            labelIdReserva.setAlignment(Pos.BASELINE_RIGHT);

            Label labelCodigoReserva = new Label(String.valueOf(reservaDTO.getCodigoReserva()));

            String sIdCliente = "Id Cliente :    " + String.valueOf(reservaDTO.getCliente().getId());
            Label labelIdCliente = new Label(sIdCliente);
            labelIdCliente.setPrefWidth(180);
            labelIdCliente.setAlignment(Pos.BASELINE_RIGHT);

            String nombre = reservaDTO.getCliente().getNombres();
            String apellidos = reservaDTO.getCliente().getApellidos() == null ? "" : reservaDTO.getCliente().getApellidos();
            TextField textFieldNombreCliente = new TextField(nombre + " " + apellidos);

            Label labelCorreo = new Label(reservaDTO.getCliente().getCorreo());

            textFieldTelefono = new TextField(reservaDTO.getCliente().getTelefono());
            textFieldTelefono.setPromptText("Celular WhatsApp");

            Label labelFechaReserva = new Label("Fecha Reserva : ");
            labelFechaReserva.setPrefWidth(180);
            labelFechaReserva.setAlignment(Pos.BASELINE_RIGHT);

            Label labelFechaReserva2 = new Label(Utils.getDiaFecha(reservaDTO.getFechaReserva()));

            Label labelTotalPagar = new Label("Total a Pagar : ");
            labelTotalPagar.setPrefWidth(180);
            labelTotalPagar.setAlignment(Pos.BASELINE_RIGHT);

            TextField textFieldTotalPagar = new TextField(Utils.getMonto(Long.valueOf(reservaDTO.getMontoTotal())));
            textFieldTotalPagar.setEditable(Boolean.FALSE);
            textFieldTotalPagar.setAlignment(Pos.BASELINE_RIGHT);

            Label labelComentarios = new Label("Comentarios : ");
            labelComentarios.setPrefWidth(180);
            labelComentarios.setAlignment(Pos.BASELINE_RIGHT);

            TextArea textAreaComentarios = new TextArea();
            textAreaComentarios.setPrefWidth(180);
            textAreaComentarios.setPrefHeight(60);
            textAreaComentarios.setText(reservaDTO.getComentarios());
            textAreaComentarios.setWrapText(Boolean.TRUE);
            textAreaComentarios.setEditable(Boolean.FALSE);

            Label labelFechaEntrega = new Label("Fecha Entrega : ");
            labelFechaEntrega.setPrefWidth(180);
            labelFechaEntrega.setAlignment(Pos.BASELINE_RIGHT);

            datePickerFechaEntrega = new DatePicker();
            DateFormat dateFormat = new SimpleDateFormat("EE dd/MM/yyyy");
            datePickerFechaEntrega.setDateFormat(dateFormat);
            if (reservaDTO.getFechaEntrega() != null) {
                datePickerFechaEntrega.setValue(reservaDTO.getFechaEntrega());
            }
            Label labelHoraEntrega = new Label("Hora Entrega : ");
            labelHoraEntrega.setPrefWidth(180);
            labelHoraEntrega.setAlignment(Pos.BASELINE_RIGHT);

            comboBoxHoraEntrega.getItems().addAll(HoraEntregaEnum.values());
            comboBoxHoraEntrega.getSelectionModel().select(reservaDTO.getHoraEntregaEnum());
            comboBoxHoraEntrega.setPrefWidth(180);

            Label labelDatosEntrega = new Label("Datos de Entrega");
            labelDatosEntrega.setPrefWidth(180);
            labelDatosEntrega.setAlignment(Pos.BASELINE_RIGHT);

            textAreaDatosEntrega = new TextArea();
            textAreaDatosEntrega.setPrefWidth(180);
            textAreaDatosEntrega.setPrefHeight(60);
            textAreaDatosEntrega.setWrapText(Boolean.TRUE);
            textAreaDatosEntrega.setText(reservaDTO.getDatosDeEntrega());

            Label labelEstadoReserva = new Label("Estado Reserva :");
            labelEstadoReserva.setPrefWidth(180);
            labelEstadoReserva.setAlignment(Pos.BASELINE_RIGHT);

            comboBoxEstadoReserva = new ComboBox<>();
            comboBoxEstadoReserva.getItems().addAll(getEstadoReservaEnumsCombobox());
            comboBoxEstadoReserva.getSelectionModel().select(reservaDTO.getEstadoReservaEnum());
            comboBoxEstadoReserva.setPrefWidth(180);

            ClickHandler clickEvent = new ClickHandler();
            buttonAnular.setStyle("-fx-background-color: red; -fx-text-fill: white");
            buttonAnular.setOnMouseClicked(clickEvent);
            buttonGrabar.setStyle("-fx-background-color: blue; -fx-text-fill: white");
            buttonGrabar.setOnMouseClicked(clickEvent);

            // COLUMNA - FILA
            this.add(labelIdReserva, 0, 0);
            this.add(labelCodigoReserva, 1, 0);
            this.add(labelIdCliente, 0, 1);
            this.add(textFieldNombreCliente, 1, 1);
            this.add(labelCorreo, 0, 2);
            this.add(textFieldTelefono, 1, 2);
            this.add(labelTotalPagar, 0, 3);
            this.add(textFieldTotalPagar, 1, 3);
            this.add(labelFechaReserva, 0, 4);
            this.add(labelFechaReserva2, 1, 4);
            this.add(labelComentarios, 0, 5);
            this.add(textAreaComentarios, 1, 5);
            this.add(labelFechaEntrega, 0, 6);
            this.add(datePickerFechaEntrega, 1, 6);
            this.add(labelHoraEntrega, 0, 7);
            this.add(comboBoxHoraEntrega, 1, 7);
            this.add(labelDatosEntrega, 0, 8);
            this.add(textAreaDatosEntrega, 1, 8);
            this.add(labelEstadoReserva, 0, 9);
            this.add(comboBoxEstadoReserva, 1, 9);
            this.add(buttonAnular, 0, 10);
            this.add(buttonGrabar, 1, 10);
        }

        public List<EstadoReservaEnum> getEstadoReservaEnumsCombobox() {
            List<EstadoReservaEnum> estadoReservaEnums = new ArrayList<>();
            for (EstadoReservaEnum estadoReservaEnum : EstadoReservaEnum.values()) {
                if (!EstadoReservaEnum.ANULADO.equals(estadoReservaEnum)
                        && !EstadoReservaEnum.ENTREGADO.equals(estadoReservaEnum)) {
                    estadoReservaEnums.add(estadoReservaEnum);
                }
            }
            return estadoReservaEnums;
        }
    }

    private ObservableList<ReservaProperty> getReservaPendientes(Long idReserva, EstadoReservaEnum... estadoReservaEnums) {
        ObservableList<ReservaProperty> reservaProperties = FXCollections.<ReservaProperty>observableArrayList();
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        //List<ReservaDTO> reservaDTOs =  reservaEJB.obtenerListaReservaPendientes(idReserva , estadoReservaEnums);

        for (ReservaDTO reservaDTO : reservaDTOs) {
            ReservaProperty reservaProperty = new ReservaProperty(reservaDTO);
            reservaProperties.add(reservaProperty);
        }
        return reservaProperties;
    }

    public class ClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent mouseEvent) {
            Button button = (Button) mouseEvent.getSource();
            if (buttonBuscar.equals(button)) {
                if (!textFieldCodigoReserva.getText().isEmpty()) {
                    String idReserva = textFieldCodigoReserva.getText();
                    tableViewReserva.setItems(getReservaPendientes(Long.valueOf(idReserva), null));
                }
            } else if (buttonRecargar.equals(button)) {
                obtenerListaReservas();
            } else if (buttonAnular.equals(button)) {
                ReservaDTO reservaDTO = detalleReservaPane.reservaDTO;
                LOGGER.info("ID RESERVA = "+reservaDTO.getIdReserva()+".");
                for(int i = 0 ; i < tableViewReserva.getItems().size() ; i++){
                    ReservaProperty reservaProperty = tableViewReserva.getItems().get(i);
                    LOGGER.info("CODIGO RESERVA = "+reservaProperty.getCodigoReserva()+".");
                    if(reservaProperty.getCodigoReserva().trim().startsWith( String.valueOf(reservaDTO.getIdReserva()).trim() )) {
                        tableViewReserva.getItems().remove(i);
                        LOGGER.info("ELIMINADO INDEX = "+i);
                        break;
                    }
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action" , AccionEnum.ADMIN_ANULAR_RESERVA.name());
                jsonObject.put("idReserva" , reservaDTO.getIdReserva());
                sendToServerWebSocket(jsonObject);
            } else if (buttonGrabar.equals(button)) {
                String telefono = detalleReservaPane.textFieldTelefono.getText();
                ClienteDTO clienteDTO = detalleReservaPane.reservaDTO.getCliente();
                clienteDTO.setTelefono(telefono);
                //clienteEJB.actualizarTelefonoCliente(clienteDTO);
                detalleReservaPane.reservaDTO.setFechaEntrega(detalleReservaPane.datePickerFechaEntrega.getValue());
                detalleReservaPane.reservaDTO.setEstadoReservaEnum(comboBoxEstadoReserva.getSelectionModel().getSelectedItem());
                detalleReservaPane.reservaDTO.setDatosDeEntrega(textAreaDatosEntrega.getText());
                detalleReservaPane.reservaDTO.setHoraEntregaEnum(comboBoxHoraEntrega.getSelectionModel().getSelectedItem());
                LOGGER.info(detalleReservaPane.reservaDTO);
                //reservaEJB.actualizarReservaEntrega(detalleReservaPane.reservaDTO);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("action", AccionEnum.ADMIN_ACTUALIZAR_RESERVA.name());
                jsonObject.put("idCliente" , clienteDTO.getId() );
                jsonObject.put("telefono" , telefono);
                jsonObject.put("idReserva", detalleReservaPane.reservaDTO.getIdReserva());
                jsonObject.put("fechaEntrega" , Utils.getFecha(detalleReservaPane.datePickerFechaEntrega.getValue()));
                jsonObject.put("estadoReserva" , comboBoxEstadoReserva.getSelectionModel().getSelectedItem().name());
                jsonObject.put("datosEntrega", textAreaDatosEntrega.getText());
                jsonObject.put("horaEntrega", comboBoxHoraEntrega.getSelectionModel().getSelectedItem().name());
                sendToServerWebSocket(jsonObject);
                //tableViewReserva.setItems(getReservaPendientes(null, EstadoReservaEnum.PENDIENTE, EstadoReservaEnum.POR_PAGAR, EstadoReservaEnum.PAGADO));
            }
            anchorPane.getChildren().remove(detalleReservaPane);
            anchorPane.getChildren().remove(productosReservaPane);
            productosReservaPane = null;
            detalleReservaPane = null;
        }
    }

    private void obtenerListaReservas()  {
        textFieldCodigoReserva.setText("");
        tableViewReserva.getItems().clear();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action",AccionEnum.ADMIN_OBTENER_LISTA_RESERVAS.name());
        sendToServerWebSocket(jsonObject);
    }
    @Override
    public void sendToServerWebSocket(final JSONObject JSONmessage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("sendToServerWebSocket "+(String)JSONmessage.get("action"));
                try {
                    screensController.getSession().getBasicRemote().sendText(JSONmessage.toString());
                    System.out.println("sendToServerWebSocket " + JSONmessage);
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
                try {
                    AccionEnum accionEnum = AccionEnum.valueOf((String) jsonMessage.get("action"));
                    switch (accionEnum) {
                        case ADMIN_ACTUALIZAR_RESERVA:
                            obtenerListaReservas();
                            break;
                        case GENERAR_RESERVA_PEDIDOS:
                            JSONObject jsonObjectReserva2 = (JSONObject)jsonMessage.get("reserva");
                            ReservaDTO reservaDTO2 = new ReservaDTO(jsonObjectReserva2);
                            ReservaProperty reservaProperty2 = new ReservaProperty(reservaDTO2);
                            tableViewReserva.getItems().add(reservaProperty2);
                            break;
                        case ADMIN_OBTENER_LISTA_RESERVAS:
                            JSONParser jsonParser = new JSONParser();
                            JSONArray jsonArray = (JSONArray) jsonParser.parse((String) jsonMessage.get("reservas"));
                            for (int i = 0; i < jsonArray.size(); i++) {
                                JSONObject jsonObjectReserva = (JSONObject) jsonArray.get(i);
                                ReservaDTO reservaDTO = new ReservaDTO(jsonObjectReserva);
                                ReservaProperty reservaProperty = new ReservaProperty(reservaDTO);
                                tableViewReserva.getItems().add(reservaProperty);
                            }
                            break;
                        case ADMIN_OBTENER_PRODUCTOS_RESERVA:
                            List<DetalleReservaDTO> detalleReservaDTOList = new ArrayList<DetalleReservaDTO>();
                            JSONArray jsonArray2 = (JSONArray) new JSONParser().parse((String) jsonMessage.get("detalleReservas"));
                            for(int i = 0 ; i < jsonArray2.size() ; i++ ){
                                JSONObject jsonObject = (JSONObject)jsonArray2.get(i);
                                DetalleReservaDTO detalleReservaDTO = new DetalleReservaDTO(jsonObject);
                                detalleReservaDTOList.add(detalleReservaDTO);
                            }
                            if (!detalleReservaDTOList.isEmpty()) {
                                productosReservaPane = new ProductosReservaPane(detalleReservaDTOList);
                                productosReservaPane.setLayoutX(464);
                                productosReservaPane.setLayoutY(240);
                                anchorPane.getChildren().add(productosReservaPane);
                            }
                            break;
                        default:
                            LOGGER.info("updateData --> " + accionEnum.name());
                    }
                }catch(Exception e){
                    LOGGER.error("EXCEPTION",e);
                }
            }
        });
    }
}
