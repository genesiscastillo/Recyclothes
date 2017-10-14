package com.recyclothes.fx.controller;

import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.fx.property.UsuaroProperty;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ResourceBundle;

/**
 * Created by Cesar on 03-08-2016.
 */
public class IntroController implements Initializable ,ControlledScreen {

    private static final Logger LOGGER = Logger.getLogger(IntroController.class);

    ScreensController screensController;
    @FXML
    TableView tableView;
    @FXML
    ImageView imageView;
    @FXML
    Button buttonLoginOnOff;
    @FXML
    Label labelConectados;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("images/caprichitos.jpg");
        imageView.setImage( image);
        buttonLoginOnOff.setStyle("-fx-background-color: green");
        buttonLoginOnOff.setUserData(Boolean.TRUE);
        buttonLoginOnOff.setOnMouseClicked(new ClickHandlder());
        TableColumn<UsuaroProperty, String> idSessionCol = new TableColumn<>("idSession");
        idSessionCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        idSessionCol.setCellValueFactory(new PropertyValueFactory("idSession"));
        idSessionCol.setPrefWidth(tableView.getPrefWidth() / 4);

        TableColumn<UsuaroProperty, String> clientePropertyCol = new TableColumn<>("clienteProperty");
        clientePropertyCol.setStyle("-fx-alignment: CENTER-RIGHT;");
        clientePropertyCol.setCellValueFactory(new PropertyValueFactory("clienteProperty"));
        clientePropertyCol.setPrefWidth(tableView.getPrefWidth() / 4);

        TableColumn<UsuaroProperty, String> fechaAccesoInCol = new TableColumn<>("fechaAccesoIn");
        fechaAccesoInCol.setCellValueFactory(new PropertyValueFactory("fechaAccesoIn"));
        fechaAccesoInCol.setPrefWidth(tableView.getPrefWidth() / 4);

        TableColumn<UsuaroProperty, String> fechaAccesoOutCol = new TableColumn<>("fechaAccesoOut");
        fechaAccesoOutCol.setCellValueFactory(new PropertyValueFactory("fechaAccesoOut"));
        fechaAccesoOutCol.setPrefWidth(tableView.getPrefWidth() / 4);

        tableView.getColumns().setAll( idSessionCol , clientePropertyCol , fechaAccesoInCol , fechaAccesoOutCol );

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action" , AccionEnum.ADMIN_OBTNER_LISTA_USUARIOS_ONLINE.name());
        sendToServerWebSocket(jsonObject);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("action" , AccionEnum.ADMIN_LOGIN_ON_FOR_USUARIO.name());
        jsonObject2.put("status" , null );
        sendToServerWebSocket(jsonObject2);

    }

    @Override
    public void setScreenParent(ScreensController screensController) {
        this.screensController = screensController;
    }

    @Override
    public void sendToServerWebSocket(final JSONObject jsonObject) {
        LOGGER.info("sendToServerWebSocket "+(String)jsonObject.get("action"));
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
                AccionEnum accionEnum = AccionEnum.valueOf((String) jsonMessage.get("action"));
                LOGGER.info(jsonMessage.toString());
                try {
                    switch (accionEnum) {
                        case ADMIN_LOGIN_ON_FOR_USUARIO:
                            Boolean jsonObjectLogin = (Boolean )jsonMessage.get("status");
                            buttonLoginOnOff.setText( jsonObjectLogin  ?  "Login OnLine" : "Login Off"  );
                            buttonLoginOnOff.setStyle( jsonObjectLogin  ? "-fx-background-color: green"  : "-fx-background-color: red"  );
                            buttonLoginOnOff.setUserData( jsonObjectLogin );
                            break;
                        case LOGEAR_USUARIO:
                            JSONObject jsonObjectUsuarioLog = (JSONObject )jsonMessage.get("usuario");
                            UsuaroProperty usuaroPropertyLog = new UsuaroProperty(jsonObjectUsuarioLog);
                            actualizarDatosUsuarios(usuaroPropertyLog);
                            break;
                        case CERRAR_SESSION:
                            JSONObject jsonObjectUsuarioOut = (JSONObject )jsonMessage.get("usuario");
                            UsuaroProperty usuaroPropertyOut = new UsuaroProperty(jsonObjectUsuarioOut);
                            actualizarDatosUsuarios(usuaroPropertyOut);
                            break;
                        case SESSIONAR_USUARIO:
                            JSONObject jsonObjectUsuarioIn = (JSONObject )jsonMessage.get("usuario");
                            UsuaroProperty usuaroPropertyIn = new UsuaroProperty(jsonObjectUsuarioIn);
                            tableView.getItems().add(0 , usuaroPropertyIn);
                            break;
                        case ADMIN_OBTNER_LISTA_USUARIOS_ONLINE:
                            LOGGER.info("\n\n\n");
                            JSONArray jsonArrayB = (JSONArray) new JSONParser().parse((String) jsonMessage.get("usuarios"));
                            for(int i = 0 ; i < jsonArrayB.size() ; i++){
                                JSONObject jsonObject = (JSONObject) jsonArrayB.get(i);
                                UsuaroProperty usuaroProperty = new UsuaroProperty(jsonObject);
                                tableView.getItems().add(usuaroProperty);
                            }
                            break;
                    }
                    int conectados = tableView.getItems().size();
                    labelConectados.setText("Conectados "+conectados);
                } catch (Exception e) {
                    LOGGER.error("EXCEPTION" , e);
                }
            }
        });
    }

    private void actualizarDatosUsuarios(UsuaroProperty usuaroProperty){
        for(int i = 0; i < tableView.getItems().size() ; i++)   {
            UsuaroProperty usuaroProperty1 = (UsuaroProperty) tableView.getItems().get(i);
            if(usuaroProperty.getIdSession().equals(usuaroProperty1.getIdSession())) {
                tableView.getItems().remove(i);
                tableView.getItems().add(i , usuaroProperty);
                break;
            }
        }
    }

    @Override
    public void receiverByteBuffer(ByteBuffer byteBuffer) {


    }

    public class ClickHandlder implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent mouseEvent) {
            Button button = (Button) mouseEvent.getSource();
            if( button.equals(buttonLoginOnOff))    {
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("action" , AccionEnum.ADMIN_LOGIN_ON_FOR_USUARIO.name());
                jsonObject2.put("status" ,!(Boolean)buttonLoginOnOff.getUserData( ));
                sendToServerWebSocket(jsonObject2);
            }
        }
    }
}
