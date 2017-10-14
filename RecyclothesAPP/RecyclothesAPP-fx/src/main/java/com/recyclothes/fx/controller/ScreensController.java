package com.recyclothes.fx.controller;

import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.fx.enums.ScreenEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.websocket.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import static com.recyclothes.fx.enums.ScreenEnum.REGISTRAR_PRODUCTO;

/**
 * Created by Cesar on 19-07-2016.
 */
@ClientEndpoint
public class ScreensController extends StackPane {

    ScreenEnum screenEnum;
    static final Logger LOGGER = Logger.getLogger(ScreensController.class);
    private Session session;

    public ScreensController(String urlEndpointWebSocket){

        connectToWebSocket(urlEndpointWebSocket);
    }
    private Map<ScreenEnum , Node>  screens = new HashMap<>();
    private Map<ScreenEnum , ControlledScreen> controlledScreens = new HashMap<>();

    public void addController(ScreenEnum screenEnum , ControlledScreen controlledScreen){
        controlledScreens.put(screenEnum , controlledScreen);
    }
    public void addScreen(ScreenEnum screenEnum , Node screen){
        screens.put(screenEnum , screen);
    }

    public ControlledScreen getControlledScreen(ScreenEnum screenEnum){
        return controlledScreens.get(screenEnum);
    }
    public Node getScreen(ScreenEnum screenEnum){
        return screens.get(screenEnum);
    }

    public boolean loadScreen(ScreenEnum screenEnum)    {
        LOGGER.info(screenEnum.name());
        try{
            switch (screenEnum){
                case ESTADISTICAS:
                    EstadisticaProductosController estadisticaProductosController = new EstadisticaProductosController(this);
                    addScreen(screenEnum,  estadisticaProductosController);
                    addController(screenEnum , estadisticaProductosController);
                    break;
                default:
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(screenEnum.getResource()));
                    Parent loadScreen = (Parent) fxmlLoader.load();
                    ControlledScreen controlledScreen = (ControlledScreen) fxmlLoader.getController();
                    controlledScreen.setScreenParent(this);
                    addController(screenEnum , controlledScreen);
                    addScreen(screenEnum, loadScreen);
            }
            return true;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean setScreen(ScreenEnum screenEnum ) {
        this.screenEnum = screenEnum;
        if (screens.get( screenEnum ) != null) {
            if (!getChildren().isEmpty()) {
                getChildren().remove(0);
                getChildren().add(0, screens.get(screenEnum));
            } else {
                getChildren().add(screens.get(screenEnum));
            }
            return true;
        }else{
            System.out.println("Screen hasnt loaded..!! ScreenEnum="+screenEnum.name());
            return false;
        }
    }
    public boolean unloadScreen(ScreenEnum screenEnum){
        if(screens.remove(screenEnum) == null){
            System.out.println("Screen didnt exist!");
            return false;
        }else{
            return true;
        }
    }

    private void connectToWebSocket(String urlEndpointWebSocket) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create(urlEndpointWebSocket);
            container.connectToServer(this , uri);
        } catch (DeploymentException | IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    @OnOpen
    public void open(Session session)   {
        this.session = session;
        LOGGER.info("OPEN QueryString....."+session.getQueryString());
        LOGGER.info("OPEN RequestURI......"+session.getRequestURI().getHost());
        LOGGER.info("OPEN MaxIdleTimeout.."+session.getMaxIdleTimeout());
        LOGGER.info("OPEN QueryString......"+session.getQueryString());
        LOGGER.info("OPEN ProtocolVersion.."+session.getProtocolVersion());
    }

    @OnClose
    public void close(){
        System.out.println("CLOSE.... SESSION SERVER.!!!!");
        System.exit(-1);
    }

    @OnError
    public void error(Throwable throwable){
        System.out.println("ERROR....");
        throwable.printStackTrace();
    }

    @OnMessage
    public void handlerMessage(String message ) {
        try {
            JSONObject jsonMessage = (JSONObject) new JSONParser().parse(new StringReader(message));

            String action = (String) jsonMessage.get("action");
            AccionEnum accionEnum = AccionEnum.valueOf(action);
            LOGGER.info("WebSocket ACTION RECIBIDO --- > "+accionEnum.name());
            LOGGER.info("ScreenEnum ACTUAL         --- > "+screenEnum.name());
            switch (accionEnum) {
                case LOGEAR_USUARIO:
                case CERRAR_SESSION:
                case SESSIONAR_USUARIO:
                case ADMIN_OBTNER_LISTA_USUARIOS_ONLINE:
                case ADMIN_LOGIN_ON_FOR_USUARIO:
                    getControlledScreen(ScreenEnum.INTRO).updateData(jsonMessage);
                    break;
                case ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS:
                case ADMIN_ELIMINAR_FOTO_PRODUCTO:
                case ADMIN_GRABAR_FOTO_PRODUCTO:
                    getControlledScreen(ScreenEnum.CARGAR_FOTOS).updateData(jsonMessage);
                    break;
                case ADMIN_CARGAR_FOTOS_REGISTRO_PRODUCTO:
                    getControlledScreen(REGISTRAR_PRODUCTO).updateData(jsonMessage);
                    break;
                case ADMIN_DISPONIBILIZAR_PRODUCTO:
                case ADMIN_ACTUALIZAR_PRODUCTO:
                case ADMIN_AGREGAR_PRODUCTOS:
                    getControlledScreen(ScreenEnum.MODIFICAR_PRODUCTO).updateData(jsonMessage);
                    break;
                case ADMIN_OBTENER_LISTA_RESERVAS:
                case ADMIN_OBTENER_PRODUCTOS_RESERVA:
                case ADMIN_ACTUALIZAR_RESERVA:
                case ADMIN_ANULAR_RESERVA:
                    getControlledScreen(ScreenEnum.AGENDAR_RESERVA).updateData(jsonMessage);
                    break;
                case ADMIN_OBTENER_ESTADISTICA_POR_ESTADO:
                    getControlledScreen(ScreenEnum.ESTADISTICAS).updateData(jsonMessage);
                    break;
                case ADMIN_CARGAR_DATOS_CLIENTE:
                case ADMIN_OBTENER_LISTA_RESERVAS_POR_CLIENTE:
                    getControlledScreen(ScreenEnum.REGISTRO_CLIENTE).updateData(jsonMessage);
                    break;
                default:
                    LOGGER.info("ACTION NO DFINIDO --- > "+accionEnum.name());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessageByteBuffer (ByteBuffer byteBuffer){
        System.out.println("RECEIVER onMessageByteBuffer -->> "+screenEnum.name() );
        getControlledScreen( screenEnum ).receiverByteBuffer(byteBuffer);
    }
    public Session getSession() {
        return session;
    }

}
