package com.recyclothes.web;

import com.recyclothes.common.dto.FotoProductoDTO;
import com.recyclothes.common.dto.ProductoDTO;
import com.recyclothes.common.enums.AccionEnum;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.helper.ManagerService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 10-05-2016.
 */
@ApplicationScoped
@ServerEndpoint("/adminWebSocket")
public class AdminWebSocket {

    @Inject
    ManagerService managerService;

    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(AdminWebSocket.class);

    @OnOpen
    public void onOpen(Session session) {
        LOGGER.info("ENTRO A AdminWebSocket onOpen " + session.getId());
        managerService.service(session , AccionEnum.LOGEAR_USUARIO_ADMIN);
    }

    @OnClose
    public void onClose(Session session) {
        LOGGER.info("ENTRO A AdminWebSocket onClose " + session.getId());
        managerService.service(session, AccionEnum.CERRAR_SESSION_ADMIN);
        LOGGER.info("******************************************************************************");
    }
    @OnError
    public void onError(Throwable throwable){
        LOGGER.error("ERROR AdminWebSocket Throwable ", throwable);
    }

    @OnMessage
    public void handlerMessage(Session session , String message ) throws Exception {
        LOGGER.info("******************** handlerMessage");
        String action = "";
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonMessage = (JSONObject) jsonParser.parse(message);
        action = (String)jsonMessage.get("action");
        AccionEnum accionEnum = AccionEnum.valueOf(action);
        switch( accionEnum ) {
            case ADMIN_LOGIN_ON_FOR_USUARIO:
                Boolean status = (Boolean)jsonMessage.get("status");
                managerService.service(session , accionEnum , status);
                break;
            case ADMIN_OBTNER_LISTA_USUARIOS_ONLINE:
                managerService.service(session , accionEnum );
                break;
            case ADMIN_OBTENER_LISTA_RESERVAS_POR_CLIENTE:
                Long idClienteReserva = (Long)jsonMessage.get("idCliente");
                managerService.service(session , accionEnum , idClienteReserva );
                break;
            case ADMIN_CARGAR_DATOS_CLIENTE:
                String nombre = (String)jsonMessage.get("nombre");
                String correo =  (String)jsonMessage.get("correo");
                String telefono = (String)jsonMessage.get("telefono");
                managerService.service(session , accionEnum , nombre, correo , telefono);
                break;
            case ADMIN_ACTUALIZAR_RESERVA:
                managerService.service(session , accionEnum , jsonMessage);
                break;
            case ADMIN_DISPONIBILIZAR_PRODUCTO:
                Long idProductoPendiente = (Long)jsonMessage.get("idProducto");
                managerService.service(session , accionEnum , idProductoPendiente);
                break;
            case ADMIN_OBTENER_ESTADISTICA_POR_ESTADO:
                managerService.service(session , accionEnum);
            case ADMIN_ANULAR_RESERVA:
                Long idReservaAnular = (Long)jsonMessage.get("idReserva");
                managerService.service(session , accionEnum , idReservaAnular);
                break;
            case ADMIN_OBTENER_PRODUCTOS_RESERVA:
                Long idReservaProd = (Long)jsonMessage.get("idReserva");
                managerService.service(session , accionEnum , idReservaProd);
                break;
            case ADMIN_OBTENER_LISTA_RESERVAS:
                managerService.service(session , accionEnum );
                break;
            case ADMIN_ACTUALIZAR_PRODUCTO:
                JSONObject jsonObjectTemp = (JSONObject) jsonMessage.get("producto");
                ProductoDTO productoDTOTemp = new ProductoDTO(jsonObjectTemp);
                managerService.service(session , accionEnum , productoDTOTemp);
            case ADMIN_AGREGAR_PRODUCTOS:
                CatalogoEnum catalogoEnum1  = CatalogoEnum.valueOf((String)jsonMessage.get("catalogo"));
                TallaEnum tallaEnum1 = TallaEnum.valueOf((String) jsonMessage.get("talla") );
                EstadoProductoEnum estadoProductoEnum1 = EstadoProductoEnum.valueOf((String) jsonMessage.get("estadoProducto"));
                Long numeroPagina = (Long)jsonMessage.get("numeroPagina");
                managerService.service(session , accionEnum , catalogoEnum1 , tallaEnum1 , estadoProductoEnum1, numeroPagina);
                break;
            case ADMIN_ELIMINAR_FOTO_PRODUCTO:
                Long idFotoProducto2 = (Long)jsonMessage.get("idFotoProducto");
                managerService.service(session, accionEnum , idFotoProducto2 );
                break;
            case GENERAR_PRODUCTO_NUEVO:
                JSONObject jsonObject = (JSONObject)jsonMessage.get("producto");
                JSONArray jsonArray = (JSONArray) jsonParser.parse((String)jsonMessage.get("fotoProductos"));
                ProductoDTO productoDTO = new ProductoDTO();
                List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<>();
                CatalogoEnum catalogoEnum = CatalogoEnum.valueOf((String)jsonObject.get("catalogo"));
                productoDTO.setCatalogoEnum(catalogoEnum);
                TallaEnum tallaEnum = TallaEnum.valueOf((String)jsonObject.get("talla"));
                productoDTO.setTallaEnum(tallaEnum);
                productoDTO.setDescripcion((String) jsonObject.get("descripcion"));
                productoDTO.setPrecioProducto((Long) jsonObject.get("precioProducto"));
                productoDTO.setIdFotoProducto((Long) jsonObject.get("idFotoProducto"));
                for(int i = 0 ; i < jsonArray.size() ; i++){
                    JSONObject jsonObject1 = (JSONObject)jsonArray.get(i);
                    FotoProductoDTO fotoProductoDTO = new FotoProductoDTO();
                    fotoProductoDTO.setIdFotoProducto((Long)jsonObject1.get("idFotoProducto"));
                    fotoProductoDTOs.add(fotoProductoDTO);
                }
                managerService.service(session , accionEnum , productoDTO , fotoProductoDTOs );
                break;
            case ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS:
            case ADMIN_CARGAR_FOTOS_REGISTRO_PRODUCTO:
                Long paginaSeleccionada = (Long)jsonMessage.get("paginaSeleccionada");
                managerService.service(session , accionEnum , paginaSeleccionada );
                break;
/*
            case ADMIN_CAMBIAR_ESTADO_PRODUCTO_CATALOGO:
                Long idProducto = (Long)jsonMessage.get("idProducto");
                EstadoProductoEnum estadoProductoEnum = EstadoProductoEnum.valueOf((String) jsonMessage.get("estadoProducto"));
                managerService.service(session, accionEnum, idProducto , estadoProductoEnum);
                break;
*/
            case LIBERAR_PRODUCTOS_RESERVA_ANULADA:
                Long idReserva = (Long)jsonMessage.get("idReserva");
                managerService.service(session, accionEnum, idReserva);
                break;
            case ADMIN_CARGAR_FOTO_PRODUCTO:
                Long idFotoProducto = (Long) jsonMessage.get("idFotoProducto");
                managerService.service(session , accionEnum , idFotoProducto);
        }
    }
    @OnMessage
    public void onMessageImage( Session session , ByteBuffer byteBuffer)   {
        LOGGER.info("Recibiendo imagenes para AdminWebSocket");
        FotoProductoDTO fotoProductoDTO =  new FotoProductoDTO();
        fotoProductoDTO.setFoto(byteBuffer.array());
        fotoProductoDTO.setIdProducto(0l);
        managerService.service(session , AccionEnum.ADMIN_GRABAR_FOTO_PRODUCTO, fotoProductoDTO);
    }
}
