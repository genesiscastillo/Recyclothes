package com.recyclothes.manager;

import com.recyclothes.common.dto.*;
import com.recyclothes.common.enums.*;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.dao.Translation;
import com.recyclothes.dao.entity.*;
import com.recyclothes.dto.UsuarioDTO;
import com.recyclothes.helper.ManagerService;
import com.recyclothes.singleton.AdministracionService;
import com.recyclothes.singleton.CatalogoService;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class ManagerSessionService implements Serializable, ManagerService {

    static final Logger LOGGER = Logger.getLogger(ManagerSessionService.class);

    private Session sessionAdmin = null;
    private List<Session> listSessionClients = new ArrayList<>();

    @EJB
    CatalogoService catalogoService;

    @EJB
    AdministracionService administracionService;

/*
    mail.smtp.auth=true
    mail.smtp.starttls.enable=true
    mail.smtp.host=smtp.gmail.com
    mail.smtp.port=587
*/

    @Override
    public void service(Session session, AccionEnum accionEnum, Object... obj) {
        LOGGER.info("service -->>"+accionEnum.name());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("action", accionEnum.name());
        JSONObject jsonObjectAdmin = new JSONObject();
        jsonObjectAdmin.put("action", accionEnum.name());
        switch(accionEnum) {
            case ADMIN_LOGIN_ON_FOR_USUARIO:
                Boolean status = (Boolean)obj[0];
                LOGGER.info("status -->>"+status);
                jsonObject.put("status" , Boolean.TRUE  );
                sendToSession(session, jsonObject);
                listSessionClients.add(session);
                if(status != null) {
                    //sendToSessionAdmin(jsonObject);
                    LOGGER.info("ENVIANDO "+jsonObject.toString());
                    sendToAllConnectedSessions(session , jsonObject);
                }
                break;
            case ADMIN_OBTNER_LISTA_USUARIOS_ONLINE:
                List<UsuarioDTO> usuarioDTOs = catalogoService.obtenerListaUsuarioOnline();
                JSONArray jsonArrayOnLine = new JSONArray();
                for(UsuarioDTO usuarioDTO : usuarioDTOs){
                    jsonArrayOnLine.add(usuarioDTO.toJSONObject());
                }
                jsonObjectAdmin.put("usuarios" , jsonArrayOnLine.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_OBTENER_LISTA_RESERVAS_POR_CLIENTE:
                Long idClienteReserva = (Long)obj[0];
                JSONArray jsonArrayReserva = new JSONArray();
                List<ReservaDTO> reservaDTOs3 = administracionService.obtenerListaReservaPorCliente(idClienteReserva );
                for(ReservaDTO reservaDTO : reservaDTOs3)   {
                    jsonArrayReserva.add(reservaDTO.toJSONObject());
                }
                jsonObjectAdmin.put("reservas" , jsonArrayReserva.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_CARGAR_DATOS_CLIENTE:
                String _nombres = (String) obj[0];
                String _correo = (String) obj[1];
                String _telefono= (String) obj[2];
                JSONArray jsonArray11 = new JSONArray();
                List<ClienteDTO> clienteDTOs11 = administracionService.obtenerListaCliente(_nombres, _correo, _telefono);
                for(ClienteDTO clienteDTO : clienteDTOs11){
                    jsonArray11.add(clienteDTO.toJSONObject());
                }
                jsonObjectAdmin.put("clientes",jsonArray11.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_ACTUALIZAR_RESERVA:
                JSONObject jsonObject11 = (JSONObject)obj[0];
                Cliente cliente = new Cliente();
                cliente.setId((Long)jsonObject11.get("idCliente"));
                cliente.setTelefono((String)jsonObject11.get("telefono"));
                Reserva reserva2 = new Reserva();
                reserva2.setIdReserva((Long)    jsonObject11.get("idReserva"));
                reserva2.setFechaEntrega(Utils.getDate((String)jsonObject11.get("fechaEntrega")));
                reserva2.setEstadoReservaEnum(EstadoReservaEnum.valueOf((String)   jsonObject11.get("estadoReserva")));
                reserva2.setDatosDeEntrega((String)jsonObject11.get("datosEntrega"));
                reserva2.setHoraEntregaEnum(HoraEntregaEnum.valueOf((String)jsonObject11.get("horaEntrega")));
                administracionService.actualizarReserva(cliente , reserva2);
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_DISPONIBILIZAR_PRODUCTO:
                Long idProductoPendiente = (Long)obj[0];
                catalogoService.cambiarEstadoProducto(idProductoPendiente , EstadoProductoEnum.DISPONIBLE);
                break;
            case ADMIN_OBTENER_ESTADISTICA_POR_ESTADO:
                for(CatalogoEnum catalogoEnum : CatalogoEnum.values()) {
                    jsonObjectAdmin.put("catalogoEnum" , catalogoEnum.name());
                    List<EstadisticaDTO> estadisticaDTOs = administracionService.obtenerEstaditicaPorEstado(catalogoEnum);
                    JSONArray jsonArray = new JSONArray();
                    for(EstadisticaDTO estadisticaDTO : estadisticaDTOs){
                        jsonArray.add(estadisticaDTO.toJSONObject());
                    }
                    jsonObjectAdmin.put("estadisticas" , jsonArray.toJSONString());
                    sendToSessionAdmin(jsonObjectAdmin);
                }
                break;
            case ADMIN_ANULAR_RESERVA:
                Long idReservaAnular = (Long)obj[0];
                List<ProductoDTO> productoDTOs2 = administracionService.anularReserva(idReservaAnular);
                JSONArray jsonArray8 = new JSONArray();
                for(ProductoDTO productoDTO : productoDTOs2) {
                    catalogoService.actualizarProductoCatalogo(productoDTO.getIdProducto());
                    jsonArray8.add(productoDTO.toJSONObject());
                }
                jsonObjectAdmin.put("productos" , jsonArray8.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_OBTENER_PRODUCTOS_RESERVA:
                Long idReserva = (Long)obj[0];
                List<DetalleReservaDTO> detalleReservaDTOs = administracionService.obtenerProductosReserva(idReserva);
                JSONArray jsonArray6 = new JSONArray();
                for(DetalleReservaDTO detalleReservaDTO : detalleReservaDTOs){
                    JSONObject jsonObject1 = detalleReservaDTO.toJSONObject();
                    jsonArray6.add(jsonObject1);
                }
                jsonObjectAdmin.put("detalleReservas", jsonArray6.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_OBTENER_LISTA_RESERVAS:
                List<ReservaDTO> reservaDTOs2 = administracionService.obtenerListaReserva(null , EstadoReservaEnum.PENDIENTE , EstadoReservaEnum.POR_PAGAR , EstadoReservaEnum.PAGADO);
                JSONArray jsonArray5 = new JSONArray();
                for(ReservaDTO reservaDTO : reservaDTOs2)   {
                    JSONObject jsonObjectReserva = reservaDTO.toJSONObject();
                    jsonArray5.add(jsonObjectReserva);
                }
                jsonObjectAdmin.put("reservas" , jsonArray5.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_ACTUALIZAR_PRODUCTO:
                ProductoDTO productoDTOTemp = (ProductoDTO) obj[0];
                administracionService.actualizarProducto(productoDTOTemp);
                break;
            case ADMIN_AGREGAR_PRODUCTOS:
                CatalogoEnum catalogoEnum2 = (CatalogoEnum)obj[0];
                TallaEnum tallaEnum2 = (TallaEnum)obj[1];
                EstadoProductoEnum estadoProductoEnum2 = (EstadoProductoEnum) obj[2];
                Long numeroPagina = (Long) obj[3];
                List<DetalleProductoDTO> detalleProductoDTOs = administracionService.obtenerProductosCatalogados(catalogoEnum2 , tallaEnum2 , estadoProductoEnum2 , numeroPagina);
                Long totalPrendas = administracionService.obtenerTotalPrendas(catalogoEnum2 , tallaEnum2 , estadoProductoEnum2);
                Long totalPaginacion = administracionService.obtenerNumeroPaginacionProductos(catalogoEnum2 , tallaEnum2 , estadoProductoEnum2);
                JSONArray jsonArray4 = new JSONArray();
                for(DetalleProductoDTO detalleProductoDTO : detalleProductoDTOs)    {
                    JSONObject jsonObjectProd = new JSONObject();
                    JSONArray jsonArray3 = new JSONArray();
                    ProductoDTO productoDTO = detalleProductoDTO.getProductoDTO();
                    jsonObjectProd.put("producto" , productoDTO.toJSONObject());
                    for( FotoProductoDTO fotoProductoDTO :   detalleProductoDTO.getFotoProductoDTOs()){
                        JSONObject jsonObjectFotoProd = new JSONObject();
                        jsonObjectFotoProd.put("fotoProducto" , fotoProductoDTO.toJSONObject());
                        jsonArray3.add(jsonObjectFotoProd);
                    }
                    jsonObjectProd.put("fotoProductos" , jsonArray3.toJSONString());
                    jsonArray4.add(jsonObjectProd);
                }
                jsonObjectAdmin.put("totalPrendas" , totalPrendas);
                jsonObjectAdmin.put("totalPaginacion" , totalPaginacion);
                jsonObjectAdmin.put("productos" , jsonArray4.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_ELIMINAR_FOTO_PRODUCTO:
                Long idFotoProducto3 = (Long)obj[0];
                administracionService.eliminarFotoProducto(idFotoProducto3);
                break;
            case GENERAR_PRODUCTO_NUEVO:
                ProductoDTO productoDTO2 = (ProductoDTO) obj[0];
                List<FotoProductoDTO> fotoProductoDTOs2 = (List<FotoProductoDTO>)obj[1];
                Producto producto2 = Translation.convert(productoDTO2);
                producto2 = administracionService.generarProductoNuevo(producto2);
                for(FotoProductoDTO fotoProductoDTO : fotoProductoDTOs2){
                    FotoProducto fotoProducto = administracionService.obtenerFotoProducto(fotoProductoDTO.getIdFotoProducto());
                    fotoProducto.setIdProducto(producto2.getIdProducto());
                    administracionService.adjuntarFotoProducto(fotoProducto);
                }
                break;
            case ADMIN_INICIAR_CARGA_FOTOS_PRODUCTOS:
            case ADMIN_CARGAR_FOTOS_REGISTRO_PRODUCTO:
                Long paginaSeleccionada = (Long) obj[0];
                Long totalPaginas  = administracionService.obtenerNumeroPaginacion();
                List<FotoProductoDTO> fotoProductoDTOs = administracionService.obtenerListaFotosProductos(  paginaSeleccionada );
                JSONArray jsonArray3 = new JSONArray();
                for(FotoProductoDTO fotoProductoDTO : fotoProductoDTOs){
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put("idFotoProducto" , fotoProductoDTO.getIdFotoProducto());
                    jsonArray3.add(jsonObject1);
                }
                jsonObjectAdmin.put("lisIdFotoProducto" , jsonArray3.toJSONString());
                jsonObjectAdmin.put("totalPaginas" , totalPaginas);
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case ADMIN_CARGAR_FOTO_PRODUCTO:
                Long idFotoProducto2 = (Long) obj[0];
                FotoProducto fotoProducto = administracionService.obtenerFotoProducto(idFotoProducto2);
                ByteBuffer byteBuffer = ByteBuffer.wrap(fotoProducto.getFoto());
                sendImageToSession(session ,  byteBuffer);
                break;
            case ADMIN_GRABAR_FOTO_PRODUCTO:
                FotoProductoDTO fotoProductoDTO = (FotoProductoDTO)obj[0];
                long fileSizeInBytes = fotoProductoDTO.getFoto().length;
                // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                long fileSizeInKB = fileSizeInBytes / 1024;
                // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                //long fileSizeInMB = fileSizeInKB / 1024;
                Long idFotoProducto = administracionService.grabarFotoProducto(fotoProductoDTO);
                jsonObjectAdmin.put("size", fileSizeInKB);
                jsonObjectAdmin.put("idFotoProducto", idFotoProducto );
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case SESSIONAR_USUARIO:
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setSession(session);
                usuarioDTO.setFechaAccesoIn(Calendar.getInstance().getTime());
                catalogoService.sessionarUsuario(usuarioDTO);
                jsonObjectAdmin.put("usuario" , usuarioDTO.toJSONObject() );
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case LOGEAR_USUARIO_ADMIN :
                sessionAdmin = session;
                break;
            case CERRAR_SESSION_ADMIN:
                sessionAdmin = null;
                break;
/*
            case ADMIN_CAMBIAR_ESTADO_PRODUCTO_CATALOGO:
                Long idProducto1 = (Long) obj[0];
                EstadoProductoEnum estadoProductoEnum = (EstadoProductoEnum)obj[1];
                ProductoDTO productoDTO4 = catalogoService.cambiarEstadoProducto(idProducto1 , estadoProductoEnum) ;
                if( EstadoProductoEnum.PENDIENTE.equals(estadoProductoEnum) ) {
                    jsonObject.put("action", AccionEnum.PRODUCTO_RESERVADO.name());
                    jsonObject.put("productoDTO", productoDTO4.toJSONObject());
                    sendToAllConnectedSessions(session, jsonObject);
                }
                break;
*/
            case LIBERAR_PRODUCTOS_RESERVA_ANULADA:
                Long idReserva1 = (Long)obj[0];
                catalogoService.disponibilizarProductosReservaAnulada(idReserva1);
                break;
            case GENERAR_TOKEN_CONTACTO:
                String token = administracionService.generarTokenContacto(session.getId());
                jsonObject.put("token", token);
                sendToSession(session, jsonObject);
                break;
            case CONTACTO_MENSAJE_USUARIO:
                String nombre   = (String)obj[0];
                String correo   = (String)obj[1];
                String asunto   = (String)obj[2];
                String mensaje  = (String)obj[3];
                token           =  (String)obj[4];
                if( administracionService.validarTokenContacto(session.getId(), token ) ) {
                    ContactoDTO contactoDTO = new ContactoDTO(nombre, correo, asunto, mensaje);
                    administracionService.enviarCorreo(AccionEnum.CONTACTO_MENSAJE_USUARIO,  contactoDTO);
                    administracionService.eliminarTokenContacto(session.getId());
                    //LOGGER.info("TOKEN VALIDO y SE ENVIA EL CONTACTO A USUARIO genesiscastillo@hotmail.com");
                }
                break;
            case RECUPERAR_CLAVE_USUARIO:
                String email1 = (String)obj[0];
                ClienteDTO clienteDTOClave = catalogoService.recuperarClaveUsuario(email1);
                if(clienteDTOClave == null) {
                    jsonObject.put("existe", Boolean.FALSE);
                }else{
                    administracionService.enviarCorreo(accionEnum ,  clienteDTOClave);
                    jsonObject.put("existe", Boolean.TRUE);
                    jsonObject.put("cliente", clienteDTOClave.toJSONObject());
                }
                sendToSession(session , jsonObject);
                break;
            case RESERVAR_PRODUCTO:
            case RESERVAR_PRODUCTO_DETALLE :
                Long idProducto = (Long) obj[0];
                PedidoProductoDTO pedidoProductoDTO = catalogoService.separarProducto(session.getId(), idProducto);
                jsonObject.put("isLoggeado", catalogoService.isLogeado(session.getId()));
                jsonObject.put("productoDTO", pedidoProductoDTO.getProducto().toJSONObject());
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("(").append(pedidoProductoDTO.getTotalProductos()).append(") ")
                            .append("Pedidos ").append("$ ").append(pedidoProductoDTO.getMontoTotal());
                jsonObject.put("pedidos", stringBuilder.toString());
                sendToSession(session, jsonObject);
                jsonObject.put("action", AccionEnum.PRODUCTO_RESERVADO.name());
                sendToAllConnectedSessions(session, jsonObject);
                break;
            case CERRAR_SESSION:
                UsuarioDTO usuarioDTO12=  catalogoService.cerrarSession(session.getId());
                if(usuarioDTO12 != null) {
                    jsonObjectAdmin.put("usuario", usuarioDTO12.toJSONObject());
                    sendToSessionAdmin(jsonObjectAdmin);
                }
                listSessionClients.remove(session);
                break;
            case VER_DETALLE_PRODUCTO:
                idProducto = (Long) obj[0];
                DetalleProductoDTO detalleProductoDTO = catalogoService.obtenerDetalleProducto(idProducto);
                jsonObject.put("isLoggeado", catalogoService.isLogeado(session.getId()));
                jsonObject.put("detalleProductoDTO", detalleProductoDTO.toJSONObject());
                sendToSession(session, jsonObject);
                break;
            case LOGEAR_USUARIO:
                String email = (String) obj[0];
                String password = (String) obj[1];
                UsuarioDTO usuarioDTO1 = catalogoService.validarUsuario(session.getId(), email , session);
                if( usuarioDTO1 == null )   {
                    jsonObject.put("mensaje" , "<strong>Correo no registrado!</strong>");
                    jsonObject.put("isLoggeado" , Boolean.FALSE);
                    jsonObject.put("cliente", org.codehaus.jettison.json.JSONObject.NULL );
                }else if( ! usuarioDTO1.getCliente().getToken().isEmpty()) {
                    jsonObject.put("mensaje" , "Debe activar su cuenta.<br/>Revise su correo en "+usuarioDTO1.getCliente().getCorreo());
                    jsonObject.put("isLoggeado", Boolean.FALSE);
                    jsonObject.put("cliente", org.codehaus.jettison.json.JSONObject.NULL );
                }else if( ! password.equals(usuarioDTO1.getCliente().getPassword())   ) {
                    jsonObject.put("mensaje" , "Password incorrecto !<br/>"+usuarioDTO1.getCliente().getCorreo());
                    jsonObject.put("isLoggeado", Boolean.FALSE);
                    jsonObject.put("cliente", org.codehaus.jettison.json.JSONObject.NULL );
                }else{
                    JSONArray jsonArray2 = new JSONArray();
                    jsonObject.put("isLoggeado", catalogoService.isLogeado(session.getId()));
                    jsonObject.put("cliente", usuarioDTO1.getCliente().toJSONObject());
                    List<ReservaDTO> reservaDTOs = catalogoService.obtenerListaReservaCliente(usuarioDTO1.getCliente());
                    for (ReservaDTO reservaDTO : reservaDTOs) {
                        jsonArray2.add(reservaDTO.toJSONObject());
                    }
                    jsonObject.put("reservas", jsonArray2.toJSONString());
                }
                sendToSession(session , jsonObject);
                if( usuarioDTO1 != null)    {
                    jsonObjectAdmin.put("usuario", usuarioDTO1.toJSONObject());
                    sendToSessionAdmin(jsonObjectAdmin);
                }
                break;
            case AGREGAR_PRODUCTOS_ALEATORIA:
                JSONArray jsonArray2 = new JSONArray();
                List<ProductoDTO> productoDTO2s = catalogoService.obtenerProductosAleatorio();
                for (ProductoDTO productoTmp : productoDTO2s) {
                    jsonArray2.add(productoTmp.toJSONObject());
                }
                Boolean isLoggeado = catalogoService.isLogeado(session.getId());
                if( isLoggeado ) {
                    isLoggeado  = catalogoService.bloquearPedidosUsuario(session.getId());
                }
                jsonObject.put("action" , AccionEnum.AGREGAR_PRODUCTOS.name());
                jsonObject.put("isLoggeado", isLoggeado);
                jsonObject.put("productos", jsonArray2.toJSONString());
                sendToSession(session, jsonObject);
                break;
            case AGREGAR_PRODUCTOS:
                JSONArray jsonArray = new JSONArray();
                List<ProductoDTO> productoDTOs;
                TallaEnum tallaEnum = (TallaEnum) obj[0];
                CatalogoEnum catalogoEnum = (CatalogoEnum) obj[1];
                Integer pagina = (Integer)obj[2];
                productoDTOs = catalogoService.obtenerProductosPorTalla( catalogoEnum , tallaEnum , pagina);
                Integer numeroPaginas = catalogoService.obtenerNumeroPaginas(catalogoEnum, tallaEnum);
                jsonObject.put("numeroPaginas", numeroPaginas );
                for (ProductoDTO productoTmp : productoDTOs) {
                    jsonArray.add(productoTmp.toJSONObject());
                }
                isLoggeado = catalogoService.isLogeado(session.getId());
                if( isLoggeado ) {
                    isLoggeado  = catalogoService.bloquearPedidosUsuario(session.getId());
                }
                jsonObject.put("isLoggeado", isLoggeado);
                jsonObject.put("productos", jsonArray.toJSONString());
                sendToSession(session, jsonObject);
                break;
            case VER_DETALLE_PEDIDOS:
                jsonArray = new JSONArray();
                DetalleProductosReservadosDTO detalleProductosReservadosDTO = catalogoService.obtenerProductosSeparados(session.getId());
                productoDTOs = detalleProductosReservadosDTO.getProductoList();
                for (ProductoDTO productoTmp : productoDTOs) {
                    LOGGER.info("ManagerSessionService->" + productoTmp.toString());
                    jsonArray.add(productoTmp.toJSONObject());
                }
                jsonObject.put("isLoggeado", catalogoService.isLogeado(session.getId()));
                jsonObject.put("productos", jsonArray.toJSONString());
                jsonObject.put("montoTotal", detalleProductosReservadosDTO.getMontoTotal());
                sendToSession(session , jsonObject);
                break;
            case GENERAR_RESERVA_PEDIDOS:
                String comentarios = (String)obj[0];
                ReservaDTO reservaDTO = catalogoService.reservarProductos(session.getId(), comentarios);
                DatosCodigoReservaDTO datosCodigoReservaDTO  = catalogoService.obtenerProductosCodigoReserva(reservaDTO.getCodigoReserva());
                administracionService.enviarCorreo(accionEnum, datosCodigoReservaDTO);
                ClienteDTO clienteDTO = datosCodigoReservaDTO.getReserva().getCliente();
                jsonObject.put("cliente", clienteDTO.toJSONObject());
                List<ReservaDTO> reservaDTOs = catalogoService.obtenerListaReservaCliente(clienteDTO);
                jsonArray = new JSONArray();
                for (ReservaDTO reserva : reservaDTOs) {
                    jsonArray.add(reserva.toJSONObject());
                }
                jsonObject.put("reservas", jsonArray.toJSONString());
                sendToSession(session, jsonObject);
                jsonArray = new JSONArray();
                for (ProductoDTO productoDTO : datosCodigoReservaDTO.getProductoList()) {
                    jsonArray.add(productoDTO.toJSONObject());
                }
                jsonObjectAdmin.put("reserva", reservaDTO.toJSONObject());
                jsonObjectAdmin.put("productos", jsonArray.toJSONString());
                sendToSessionAdmin(jsonObjectAdmin);
                break;
            case REGISTRAR_CLIENTE:
                String regnombre = (String)obj[0];
                String regemail = (String)obj[1];
                String regpassword = (String)obj[2];
                String regtelefono = (String)obj[3];
                ClienteDTO clienteNuevoDTO = new ClienteDTO();
                clienteNuevoDTO.setNombres(regnombre);
                clienteNuevoDTO.setCorreo(regemail);
                clienteNuevoDTO.setPassword(regpassword);
                clienteNuevoDTO.setTelefono(regtelefono);
                RegistroClienteDTO registroClienteDTO = catalogoService.registrarClienteNuevo( clienteNuevoDTO );
                if( registroClienteDTO.isNuevo() ) {
                    administracionService.enviarCorreo(accionEnum, registroClienteDTO);
                }
                jsonObject.put("cliente", registroClienteDTO.getCliente().toJSONObject() );
                jsonObject.put("isNuevo", registroClienteDTO.isNuevo());
                sendToSession(session , jsonObject);
                break;
            case HABILITAR_PRODUCTO:
                idProducto = (Long)obj[0];
                pedidoProductoDTO = catalogoService.habilitarProducto(session.getId() , idProducto);
                jsonObject.put("idProducto", pedidoProductoDTO.getProducto().getIdProducto());
                jsonObject.put("montoTotal", pedidoProductoDTO.getMontoTotal());
                sendToSession(session, jsonObject);
                break;
            case VER_RESERVA_PEDIDOS:
                String codigoReserva = (String)obj[0];
                datosCodigoReservaDTO = catalogoService.obtenerProductosCodigoReserva(codigoReserva);
                jsonObject.put("reserva", datosCodigoReservaDTO.getReserva().toJSONObject());
                jsonArray = new JSONArray();
                for(ProductoDTO producto : datosCodigoReservaDTO.getProductoList()){
                    jsonArray.add(producto.toJSONObject());
                }
                jsonObject.put("productos", jsonArray.toJSONString());
                sendToSession(session, jsonObject);
                break;
            default:
                LOGGER.info("ManagerSessionService NO EXISTE negocio service ->" + accionEnum.name());
        }
    }

    private void sendToSession(Session session, JSONObject message) {
        String action = (String)message.get("action");
        try{
            String filename = "ADMIN_"+action+".txt";
            Path file = Paths.get("D:\\app\\", filename);
            if(!Files.exists( file )) {
                byte[] buf = message.toString().getBytes();
                Files.write(file, buf, StandardOpenOption.CREATE);
            }
        }catch(Exception e){
            LOGGER.error("Error al generar fichero...ManagerSessionService \n" , e);
        }

        LOGGER.info("sendToSession ->>" + (String)message.get("action"));
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            LOGGER.error("ERROR Manager ClienteWebSocket sendToSession",ex);
        }
    }

    private void sendToAllConnectedSessions(Session session,JSONObject message) {
        for(Session session1 : listSessionClients){
            //if(!session1.equals(session)) {
                sendToSession(session1, message);
            //}
        }
    }
    private void sendToSessionAdmin(JSONObject message){
        LOGGER.info("sendToSessionAdmin ->>" + (String)message.get("action"));
        if(sessionAdmin != null)    {
            try{
                sessionAdmin.getBasicRemote().sendText(message.toString());

            } catch (IOException ex) {
                LOGGER.error("ERROR Manager AdminWebSocket sendToSession",ex);
            }
        }
    }
    private void sendImageToSession(Session session , ByteBuffer byteBuffer){
        LOGGER.info("sendImageToSession ->>" + session.getId());
        try {
            session.getBasicRemote().sendBinary(byteBuffer);
        }catch(Exception e){
            LOGGER.error("service " , e);
        }
    }

}
