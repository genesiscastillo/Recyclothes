package com.recyclothes.fx.controller;

import com.recyclothes.common.dto.ClienteDTO;
import com.recyclothes.common.dto.ReservaDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.fx.property.ClienteProperty;
import com.recyclothes.fx.property.ReservaProperty;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Cesar on 10-08-2016.
 */
public class RegistroClienteController implements Initializable , ControlledScreen  {

    static final Logger LOGGER = Logger.getLogger(RegistroClienteController.class);

    @FXML TextField textFieldNombres;
    @FXML TextField textFieldCorreo;
    @FXML TextField textFieldTelefono;
    @FXML Button buttonBuscar;
    @FXML Button buttonLimpiar;

    @FXML TableView tableViewClientes;
    @FXML TableView tableViewReservas;

    ScreensController screensController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableViewClientes.getColumns().clear();
        tableViewReservas.getColumns().clear();

        TableColumn<ClienteProperty,String> colId = new TableColumn<>("id");
        colId.setStyle("-fx-alignment: CENTER-RIGHT;");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(tableViewClientes.getPrefWidth() / 7 );

        TableColumn<ClienteProperty,String> colNombres = new TableColumn<>("nombres");
        colNombres.setStyle("-fx-alignment: CENTER-RIGHT;");
        colNombres.setCellValueFactory(new PropertyValueFactory("nombres"));
        colNombres.setPrefWidth(tableViewClientes.getPrefWidth() / 7 );

        TableColumn<ClienteProperty,String> colApellidos = new TableColumn<>("apellidos");
        colApellidos.setStyle("-fx-alignment: CENTER-RIGHT;");
        colApellidos.setCellValueFactory(new PropertyValueFactory("apellidos"));
        colApellidos.setPrefWidth(tableViewClientes.getPrefWidth() / 7 );

        TableColumn<ClienteProperty,String> colCorreo = new TableColumn<>("correo");
        colCorreo.setStyle("-fx-alignment: CENTER-RIGHT;");
        colCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        colCorreo.setPrefWidth(tableViewClientes.getPrefWidth() / 7 );

        TableColumn<ClienteProperty,String> colTelefono = new TableColumn<>("telefono");
        colTelefono.setStyle("-fx-alignment: CENTER-RIGHT;");
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        colTelefono.setPrefWidth(tableViewClientes.getPrefWidth() / 7);

        TableColumn<ClienteProperty,String> colFechaRegistroCreacion = new TableColumn<>("fechaRegistroCreacion");
        colFechaRegistroCreacion.setStyle("-fx-alignment: CENTER-RIGHT;");
        colFechaRegistroCreacion.setCellValueFactory(new PropertyValueFactory("fechaRegistroCreacion"));
        colFechaRegistroCreacion.setPrefWidth(tableViewClientes.getPrefWidth() / 7 );

        TableColumn<ClienteProperty,String> colFechaUltimaVisita = new TableColumn<>("fechaUltimaVisita");
        colFechaUltimaVisita.setStyle("-fx-alignment: CENTER-RIGHT;");
        colFechaUltimaVisita.setCellValueFactory(new PropertyValueFactory("fechaUltimaVisita"));
        colFechaUltimaVisita.setPrefWidth(tableViewClientes.getPrefWidth() / 7);

        tableViewClientes.getColumns().addAll(colId, colNombres, colApellidos, colCorreo, colTelefono, colFechaRegistroCreacion, colFechaUltimaVisita);

        tableViewClientes.setRowFactory(new Callback<TableView<ClienteProperty>, TableRow<ClienteProperty>>() {
            @Override
            public TableRow<ClienteProperty> call(TableView<ClienteProperty> tableView2) {
                final TableRow<ClienteProperty> row = new TableRow<>();
                tableViewReservas.getItems().clear();
                row.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        final int index = row.getIndex();
                        if (row.getTableView().getSelectionModel().getSelectedIndex() == index) {
                            ClienteProperty clienteProperty = row.getTableView().getSelectionModel().getSelectedItem();
                            LOGGER.info(clienteProperty.getClienteDTO());
                            cargarDatosReservaPorCliente(clienteProperty.getClienteDTO().getId());
                        }
                    }
                });
                return row;
            }
        });

        TableColumn<ReservaProperty, String> codigoReservaCol = new TableColumn<>("codigoReserva");
        codigoReservaCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        codigoReservaCol.setCellValueFactory(new PropertyValueFactory("codigoReserva"));
        codigoReservaCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> clienteCol = new TableColumn<>("cliente");
        clienteCol.setCellValueFactory(new PropertyValueFactory("cliente"));
        clienteCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> idReservaCol = new TableColumn<>("correo");
        idReservaCol.setCellValueFactory(new PropertyValueFactory("correo"));
        idReservaCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> fechaReservaCol = new TableColumn<>("fechaReserva");
        fechaReservaCol.setEditable(true);
        fechaReservaCol.setCellValueFactory(new PropertyValueFactory("fechaReserva"));
        fechaReservaCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> fechaEntregaCol = new TableColumn<>("fechaEntrega");
        fechaEntregaCol.setCellValueFactory(new PropertyValueFactory("fechaEntrega"));
        fechaEntregaCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> estadoReservaEnumCol = new TableColumn<>("estadoReservaEnum");
        estadoReservaEnumCol.setCellValueFactory(new PropertyValueFactory("estadoReservaEnum"));
        estadoReservaEnumCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> montoTotalCol = new TableColumn<>("montoTotal");
        montoTotalCol.setCellValueFactory(new PropertyValueFactory("montoTotal"));
        montoTotalCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        TableColumn<ReservaProperty, String> comentariosCol = new TableColumn<>("comentarios");
        comentariosCol.setCellValueFactory(new PropertyValueFactory("comentarios"));
        comentariosCol.setPrefWidth(tableViewReservas.getPrefWidth() / 8);

        tableViewReservas.getColumns().setAll(codigoReservaCol, idReservaCol, clienteCol, fechaReservaCol, fechaEntregaCol, estadoReservaEnumCol, montoTotalCol, comentariosCol);

        buttonBuscar.setOnMouseClicked(new ClickHandlder());
        buttonLimpiar.setOnMouseClicked(new ClickHandlder());

        cargarDatosClientes(null, null ,null);


    }

    private void cargarDatosReservaPorCliente(Long idCliente){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", AccionEnum.ADMIN_OBTENER_LISTA_RESERVAS_POR_CLIENTE.name());
        jsonObject.put("idCliente" , idCliente);
        sendToServerWebSocket(jsonObject);

        ObservableList<ReservaProperty> reservaPropertyObservableList = FXCollections.observableArrayList();
        try{
            List<ReservaDTO> reservaDTOs = new ArrayList<>();
            for(ReservaDTO reservaDTO : reservaDTOs){
                LOGGER.info(reservaDTO);
                ReservaProperty reservaProperty = new ReservaProperty(reservaDTO);
                reservaPropertyObservableList.add(reservaProperty);
            }
        }catch(Exception e){
            LOGGER.error("cargarDatosReservaPorCliente", e);
        }
        tableViewReservas.setItems( reservaPropertyObservableList );
    }

    private void cargarDatosClientes(String nombre, String correo,String telefono)  {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", AccionEnum.ADMIN_CARGAR_DATOS_CLIENTE.name());
        jsonObject.put("nombre" , nombre);
        jsonObject.put("correo" , correo);
        jsonObject.put("telefono" , telefono);
        sendToServerWebSocket(jsonObject);
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
                LOGGER.info("updateData "+jsonMessage.toString());
                try {
                    String accion = (String)jsonMessage.get("action");
                    AccionEnum accionEnum = AccionEnum.valueOf(accion);
                    switch (accionEnum) {
                        case ADMIN_CARGAR_DATOS_CLIENTE:
                            JSONArray jsonArrayB = (JSONArray) new JSONParser().parse((String) jsonMessage.get("clientes"));
                            for (int i = 0; i < jsonArrayB.size(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArrayB.get(i);
                                ClienteDTO clienteDTO = new ClienteDTO(jsonObject);
                                ClienteProperty clienteProperty = new ClienteProperty(clienteDTO);
                                tableViewClientes.getItems().add(clienteProperty);
                            }
                            break;
                        case ADMIN_OBTENER_LISTA_RESERVAS_POR_CLIENTE:
//{"reservas":"[{\"codigoReserva\":\"1c2f9\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":null,\"horaEntregaEnum\":null,\"montoPagar\":333,\"fechaEntrega\":\"30\\\/08\\\/2016\",\"estadoReservaEnum\":\"PENDIENTE\",\"comentarios\":\"aaa\",\"idReserva\":63,\"montoTotal\":333,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"29\\\/08\\\/2016\"},{\"codigoReserva\":\"afbd3\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":\"plaza de armas 200 500\",\"horaEntregaEnum\":\"05:00pm\",\"montoPagar\":7500,\"fechaEntrega\":\"27\\\/08\\\/2016\",\"estadoReservaEnum\":\"ANULADO\",\"comentarios\":\"Llamameeee\",\"idReserva\":62,\"montoTotal\":7500,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"24\\\/08\\\/2016\"},{\"codigoReserva\":\"59283\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":null,\"horaEntregaEnum\":null,\"montoPagar\":6000,\"fechaEntrega\":\"24\\\/08\\\/2016\",\"estadoReservaEnum\":\"ANULADO\",\"comentarios\":\"wwwee\",\"idReserva\":61,\"montoTotal\":6000,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"23\\\/08\\\/2016\"},{\"codigoReserva\":\"7cb45\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":null,\"horaEntregaEnum\":null,\"montoPagar\":6000,\"fechaEntrega\":\"24\\\/08\\\/2016\",\"estadoReservaEnum\":\"ANULADO\",\"comentarios\":\"wwwwww\",\"idReserva\":60,\"montoTotal\":6000,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"23\\\/08\\\/2016\"},{\"codigoReserva\":\"5a0e3\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":null,\"horaEntregaEnum\":null,\"montoPagar\":3000,\"fechaEntrega\":\"24\\\/08\\\/2016\",\"estadoReservaEnum\":\"ANULADO\",\"comentarios\":\"ssss\",\"idReserva\":59,\"montoTotal\":3000,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"23\\\/08\\\/2016\"},{\"codigoReserva\":\"cdda4\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":null,\"horaEntregaEnum\":null,\"montoPagar\":2500,\"fechaEntrega\":\"24\\\/08\\\/2016\",\"estadoReservaEnum\":\"ANULADO\",\"comentarios\":\"www\",\"idReserva\":58,\"montoTotal\":2500,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"23\\\/08\\\/2016\"},{\"codigoReserva\":\"53901\",\"cliente\":{\"apellidos\":null,\"fechaRegistroCreacion\":\"23\\\/08\\\/2016\",\"correo\":\"genesiscastillo@hotmail.com\",\"id\":24,\"telefono\":\"+5695181762\",\"fechaUltimaVisita\":null,\"nombres\":\"Cesar\",\"token\":\"\"},\"datosDeEntrega\":null,\"horaEntregaEnum\":null,\"montoPagar\":7000,\"fechaEntrega\":\"24\\\/08\\\/2016\",\"estadoReservaEnum\":\"ANULADO\",\"comentarios\":\"ssss\",\"idReserva\":57,\"montoTotal\":7000,\"nombres\":\"#24-Cesar null\",\"fechaReserva\":\"23\\\/08\\\/2016\"}]","action":"ADMIN_OBTENER_LISTA_RESERVAS_POR_CLIENTE"}
                            JSONArray jsonArrayA = (JSONArray) new JSONParser().parse((String) jsonMessage.get("reservas"));
                            for (int i = 0; i < jsonArrayA.size(); i++) {
                                JSONObject jsonObject = (JSONObject) jsonArrayA.get(i);
                                ReservaDTO reservaDTO = new ReservaDTO(jsonObject);
                                ReservaProperty reservaProperty = new ReservaProperty(reservaDTO);
                                tableViewReservas.getItems().add(reservaProperty);
                            }
                            break;
                        default:
                            LOGGER.info("ACCION NO DEFINIDO "+accion);
                    }
                }catch(Exception e){
                    LOGGER.error("Exception",e);
                }
            }
        });
    }

    @Override
    public void receiverByteBuffer(ByteBuffer byteBuffer) {

    }

    public class ClickHandlder implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button button = (Button) mouseEvent.getSource();
            if( button.equals(buttonLimpiar))    {
                tableViewClientes.getItems().clear();
                tableViewReservas.getItems().clear();
                textFieldNombres.setText(null);
                textFieldCorreo.setText(null);
                textFieldTelefono.setText(null);
            }
            else if( button.equals(buttonBuscar))    {
                tableViewClientes.getItems().clear();
                tableViewReservas.getItems().clear();
                String nombre = textFieldNombres.getText();
                String correo = textFieldCorreo.getText();
                String telefono = textFieldTelefono.getText();
                cargarDatosClientes(nombre, correo,telefono);
            }
        }
    }

}
