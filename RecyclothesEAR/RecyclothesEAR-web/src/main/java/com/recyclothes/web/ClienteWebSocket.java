package com.recyclothes.web;

import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.helper.ManagerService;
import org.apache.log4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Cesar on 11-03-2016.
 */
@ApplicationScoped
@ServerEndpoint("/clienteWebSocket")
public class ClienteWebSocket {
    static final Logger LOGGER = Logger.getLogger(ClienteWebSocket.class);

    @Inject
    ManagerService managerService;

    @OnOpen
    public void onOpen(Session session){
        try {
            LOGGER.info("------------------------------------------------\n");
            LOGGER.info("Nueva Session " + session.getId());
            LOGGER.info("Datetime      " + Utils.getDiaFecha(Calendar.getInstance().getTime()));
            LOGGER.info("------------------------------------------------");
            managerService.service(session, AccionEnum.SESSIONAR_USUARIO);
        }catch(Exception e){
            LOGGER.error("ClienteWebSocket onOpen",e);
        }
    }
    @OnClose
    public void onClose(Session session){
        LOGGER.error("close session " + session.getId());
        managerService.service(session, AccionEnum.CERRAR_SESSION);
    }
    @OnError
    public void onError(Throwable throwable){
        LOGGER.error("ERROR ", throwable);
    }

    @OnMessage
    public void handlerMessage(String message, Session session) {
        LOGGER.info("handlerMessage "+message);
        String action = "";
        JsonReader jsonReader = Json.createReader(new StringReader(message));
        JsonObject jsonMessage = jsonReader.readObject();
        jsonReader.close();
        action = jsonMessage.getString("action");
        try{
            String filename = "CLIENTE_"+action+".txt";
            Path file = Paths.get("D:\\app\\", filename);
            if(!Files.exists( file )) {
                byte[] buf = message.getBytes();
                Files.write(file, buf, StandardOpenOption.CREATE);
            }
        }catch(Exception e){
            LOGGER.error("Error al generar fichero...ClienteWebSocket \n" , e);
        }
        AccionEnum accionEnum = AccionEnum.valueOf(action);
        switch(accionEnum ) {
            case ADMIN_LOGIN_ON_FOR_USUARIO:
                    Boolean status = null;
                    managerService.service(session , accionEnum , status);
                break;
            case GENERAR_TOKEN_CONTACTO :
                    managerService.service(session , accionEnum );
                break;
            case CONTACTO_MENSAJE_USUARIO :
                    String nombre = jsonMessage.getString("name");
                    String correo = jsonMessage.getString("email");
                    String asunto = jsonMessage.getString("subject");
                    String mensaje = jsonMessage.getString("message");
                    String token = jsonMessage.getString("token");
                    managerService.service(session , accionEnum , nombre , correo, asunto , mensaje , token);
                break;
            case RECUPERAR_CLAVE_USUARIO:
                    String email = jsonMessage.getString("email");
                    managerService.service(session , accionEnum , email);
                break;
            case CERRAR_SESSION:
                    managerService.service(session , accionEnum);
                break;
            case REGISTRAR_CLIENTE:
                    nombre = jsonMessage.getString("regnombre");
                    email = jsonMessage.getString("regemail");
                    String password = jsonMessage.getString("regpassword");
                    String telefono = jsonMessage.getString("regtelefono");
                    managerService.service(session, accionEnum, nombre, email, password , telefono);
                break;
            case LOGEAR_USUARIO:
                    email = jsonMessage.getString("email");
                    password = jsonMessage.getString("password");
                    managerService.service(session, accionEnum, email, password );
                break;
            case AGREGAR_PRODUCTOS:
                    Integer ordinalCatalogo = Integer.valueOf(jsonMessage.getString("catalogo"));
                    Integer ordinalTalla    = Integer.valueOf(jsonMessage.getString("talla"));
                    Integer pagina          = Integer.valueOf(jsonMessage.getString("pagina"));
                    if( ordinalTalla == 14 ) {
                        managerService.service(session, AccionEnum.AGREGAR_PRODUCTOS_ALEATORIA);
                    }else {
                        TallaEnum tallaEnum = TallaEnum.getEnum(ordinalTalla);
                        CatalogoEnum catalogoEnum = CatalogoEnum.getEnum(ordinalCatalogo);
                        managerService.service(session, accionEnum, tallaEnum, catalogoEnum, pagina);
                    }
                break;
            case VER_DETALLE_PEDIDOS:
                    managerService.service(session, accionEnum);
                break;
            case VER_DETALLE_PRODUCTO:
                JsonNumber jsonNumber = jsonMessage.getJsonNumber("idProducto");
                Long idProducto =  jsonNumber.longValue();
                    managerService.service(session, accionEnum, idProducto);
                break;
            case RESERVAR_PRODUCTO :
            case RESERVAR_PRODUCTO_DETALLE:
                jsonNumber = jsonMessage.getJsonNumber("idProducto");
                idProducto =  jsonNumber.longValue();
                    managerService.service(session, accionEnum, idProducto);
                break;
            case GENERAR_RESERVA_PEDIDOS:
                String comentarios = jsonMessage.getString("comentarios");
                    managerService.service(session, accionEnum, comentarios );
                break;
            case HABILITAR_PRODUCTO:
                idProducto =  jsonMessage.getJsonNumber("idProducto").longValue();
                    managerService.service(session, accionEnum, idProducto);
                break;
            case VER_RESERVA_PEDIDOS:
                String codigoReserva = jsonMessage.getString("codigoReserva");
                    managerService.service(session, accionEnum, codigoReserva);
                break;
            default:
                LOGGER.info("ClienteWebSocket NEGOCIO NO DEFINIDO aun "+accionEnum.name());
                break;
        }
    }

}
