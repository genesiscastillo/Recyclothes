package com.recyclothes.singleton;

import com.recyclothes.common.dto.*;
import com.recyclothes.common.enums.*;
import com.recyclothes.common.utils.Utils;
import com.recyclothes.dao.*;
import com.recyclothes.dao.entity.*;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cesar on 16-03-2016.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class AdministracionSingleton implements AdministracionService {

    static final Logger LOGGER = Logger.getLogger(AdministracionSingleton.class );
    String urlAppName = "http://wwww.recyclothes.cl";
    //String urlAppName = "http://localhost:8080";

    private Map<String,String> mapaTokenContacto = new HashMap<>();

    @Resource(name = "java:/myMailSession")
    private javax.mail.Session mailSession;

    @EJB
    ClienteDAO clienteDAO;

    @EJB
    FotoProductoDAO fotoProductoDAO;

    @EJB
    ProductoDAO productoDAO;

    @EJB
    ReservaDAO reservaDAO;

    @EJB
    DetalleReservaDAO detalleReservaDAO;


    @Override
    public boolean confirmacionToken(String idToken) {
        return clienteDAO.validarConfirmacionToken(idToken);
    }
    @Override
    public Long obtenerTotalPrendas(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum) {
        return productoDAO.obtenerTotalPrendas(catalogoEnum, tallaEnum, estadoProductoEnum);
    }

    @Override
    public Producto generarProductoNuevo(Producto producto) {
        return productoDAO.registrarProducto(producto);
    }

    @Override
    public FotoProducto adjuntarFotoProducto(FotoProducto fotoProducto) {
       return fotoProductoDAO.actualizarFotoProducto(fotoProducto);
    }

    @Override
    @Asynchronous
    public void eliminarFotoProducto(Long idFotoProducto) {
        fotoProductoDAO.eliminarFotoProducto(idFotoProducto);
    }

    @Override
    @Asynchronous
    public void actualizarProducto(ProductoDTO productoDTO) {
        Producto producto = Translation.convert(productoDTO);
        productoDAO.actualizarProducto(producto);
    }

    @Override
    @Asynchronous
    public void actualizarReserva(Cliente cliente, Reserva reserva) {
        Cliente cliente2 = clienteDAO.obtenerCliente(cliente.getId());
        cliente2.setTelefono(cliente.getTelefono());
        clienteDAO.actualizarCliente(cliente2);
        Reserva reserva1 = reservaDAO.obtenerReserva(reserva.getIdReserva());
        reserva1.setFechaEntrega(reserva.getFechaEntrega());
        reserva1.setEstadoReservaEnum(reserva.getEstadoReservaEnum());
        reserva1.setDatosDeEntrega(reserva.getDatosDeEntrega());
        reserva1.setHoraEntregaEnum(reserva.getHoraEntregaEnum());
        reservaDAO.actualizarReserva(reserva1);
    }

    @Override
    public List<ProductoDTO> anularReserva(Long idReserva) {
        LOGGER.info("anularReserva idReserva = "+idReserva);
        List<ProductoDTO> productoDTOs = new ArrayList<>();
        Reserva reserva = reservaDAO.obtenerReserva(idReserva );
        reserva.setEstadoReservaEnum(EstadoReservaEnum.ANULADO);
        reservaDAO.actualizarReserva(reserva);
        List<DetalleReserva> detalleReservaList = detalleReservaDAO.obtenerProductosReserva(idReserva);
        for(DetalleReserva detalleReserva : detalleReservaList) {
            Producto producto = detalleReserva.getProducto();
            producto.setEstadoProductoEnum(EstadoProductoEnum.DISPONIBLE);
            productoDAO.actualizarProducto(producto);
            productoDTOs.add(Translation.convertToDTO(producto));
        }
        return productoDTOs;
    }

    @Override
    public Long obtenerNumeroPaginacion() {
        //int total_foto_por_pagina = Integer.valueOf(parametro.getValor());
        int total_foto_por_pagina = 24;
        return fotoProductoDAO.obtenerNumeroPaginacion(total_foto_por_pagina);
    }

    @Override
    public Long obtenerNumeroPaginacionProductos(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum) {
        //int total_foto_por_pagina = Integer.valueOf(parametro.getValor());
        int total_foto_por_pagina = 24;
        return productoDAO.obtenerNumeroPaginacion(catalogoEnum, tallaEnum , estadoProductoEnum ,total_foto_por_pagina );
    }

    @Override
    public List<ReservaDTO> obtenerListaReservaPorCliente(Long idCliente) {
        Cliente cliente = clienteDAO.obtenerCliente(idCliente);
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        List<Reserva> reservaList = reservaDAO.obtenerListaReservaCliente(cliente, null , null);
        for(Reserva reserva  : reservaList)    {
            ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
            reservaDTOs.add(reservaDTO);
        }
        return reservaDTOs;
    }

    @Override
    public List<ClienteDTO> obtenerListaCliente(String nombres, String correo, String telefono) {
        List<ClienteDTO> clienteDTOs = new ArrayList<>();
        List<Cliente> clienteList = clienteDAO.obtenerListaCliente(nombres, correo, telefono);
        for(Cliente cliente : clienteList)  {
            ClienteDTO clienteDTO = Translation.convertToDTO(cliente);
            clienteDTOs.add(clienteDTO);
        }
        return clienteDTOs;
    }

    @Override
    public List<EstadisticaDTO> obtenerEstaditicaPorEstado(CatalogoEnum catalogoEnum){
        List<EstadisticaDTO> estadisticaDTOs = new ArrayList<>();
        for(EstadoProductoEnum estadoProductoEnum : EstadoProductoEnum.values()) {
            EstadisticaDTO estadisticaDTO = new EstadisticaDTO(catalogoEnum , estadoProductoEnum);
            for(TallaEnum tallaEnum : TallaEnum.values()) {
                Long totalProductos = productoDAO.obtenerTotalPrendas(catalogoEnum, tallaEnum, estadoProductoEnum);
                KeyValueDTO<TallaEnum, Long> keyValueDTO = new KeyValueDTO<>(tallaEnum ,totalProductos);
                estadisticaDTO.addKeyValueDTOs(keyValueDTO);
            }
            estadisticaDTOs.add(estadisticaDTO);
        }
        return estadisticaDTOs;
    }

    @Override
    public List<DetalleReservaDTO> obtenerProductosReserva(Long idReserva) {
        List<DetalleReservaDTO> detalleReservaDTOs = new ArrayList<>();
        List<DetalleReserva> detalleReservaList = detalleReservaDAO.obtenerProductosReserva(idReserva);
        for(DetalleReserva detalleReserva : detalleReservaList){
            DetalleReservaDTO detalleReservaDTO = Translation.convertToDTO(detalleReserva);
            detalleReservaDTOs.add(detalleReservaDTO);
        }
        return detalleReservaDTOs;
    }

    @Override
    public List<ReservaDTO> obtenerListaReserva(Long idReserva, EstadoReservaEnum... estadoReservaEnums) {
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        List<Reserva> reservaList = reservaDAO.obtenerListaReserva( idReserva , estadoReservaEnums);
        for(Reserva reserva : reservaList)  {
            ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
            List<DetalleReserva> detalleReservaList =detalleReservaDAO.obtenerProductosReserva(reserva.getIdReserva());
            for(DetalleReserva detalleReserva : detalleReservaList){
                DetalleReservaDTO detalleReservaDTO = Translation.convertToDTO(detalleReserva);
                Producto producto = productoDAO.obtenerProducto(detalleReserva.getProducto().getIdProducto());
                ProductoDTO productoDTO = Translation.convertToDTO(producto);

/*
                FotoProducto fotoProducto = fotoProductoDAO.obtenerFotoProducto(productoDTO.getIdFotoProducto());
                FotoProductoDTO fotoProductoDTO = Translation.convertToDTO(fotoProducto , sinFoto );
*/

                detalleReservaDTO.setProductoDTO(productoDTO);
                //detalleReservaDTO.setFotoProductoDTO(fotoProductoDTO);

                reservaDTO.addDetalleReservaDTO(detalleReservaDTO);
            }
            reservaDTOs.add(reservaDTO);
        }
        return reservaDTOs;
    }

    @Override
    public List<DetalleProductoDTO> obtenerProductosCatalogados(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum, Long numeroPagina) {
        List<DetalleProductoDTO> detalleProductoDTOs = new ArrayList<>();
        int total_foto_por_pagina = 24;

        List<Producto> productoList = productoDAO.obtenerProductos(catalogoEnum, tallaEnum, estadoProductoEnum, numeroPagina , total_foto_por_pagina);
        for(Producto producto : productoList)   {
            List<FotoProducto> fotoProductoList = fotoProductoDAO.obtenerFotosAdjuntosDelProducto(producto.getIdProducto());
            List<FotoProductoDTO> fotoProductoDTOs = Translation.converToDTO(fotoProductoList );
            ProductoDTO productoDTO = Translation.convertToDTO(producto);
            DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
            detalleProductoDTO.setProductoDTO(productoDTO);
            detalleProductoDTO.setFotoProductoDTOs(fotoProductoDTOs);
            detalleProductoDTOs.add(detalleProductoDTO);
        }
        return detalleProductoDTOs;

    }

    @Override
    public List<FotoProductoDTO> obtenerListaFotosProductos(Long numeroPagina) {
        List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<>();
        int total_foto_por_pagina = 24;

        List<FotoProducto> fotoProductoList = fotoProductoDAO.obtenerListaFotosProductosNuevo(numeroPagina , total_foto_por_pagina);
        for(FotoProducto fotoProducto : fotoProductoList){
            FotoProductoDTO fotoProductoDTO = Translation.convertToDTO(fotoProducto );
            fotoProductoDTOs.add(fotoProductoDTO);
        }
        return fotoProductoDTOs;
    }

    @Override
    public FotoProducto obtenerFotoProducto(Long idFotoProducto) {
        return fotoProductoDAO.obtenerFotoProducto(idFotoProducto);
    }

    @Override
    @Lock(LockType.WRITE)
    public Long grabarFotoProducto(FotoProductoDTO fotoProductoDTO) {
        FotoProducto fotoProducto = Translation.convert(fotoProductoDTO);
        fotoProducto = fotoProductoDAO.registrarFotoProducto(fotoProducto);
        return fotoProducto.getIdFotoProducto();
    }

    @Override
    @Asynchronous
    public void enviarCorreo( AccionEnum accionEnum , Object object) {
        //https://www.google.com/settings/security/lesssecureapps
        try {
            String emailDestino = null;
            // Create a default MimeMessage object.
            Message message = new MimeMessage(mailSession);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress("genesiscastillof@gmail.com" , "Recyclothes-CL"));
            // Set To: header field of the header.
            //String emailDestino = "genesiscastillo@hotmail.com";
            String asunto = "Testing Subject From JBoss WildFly Cesar Castillo";
            if(object instanceof RegistroClienteDTO){
                asunto = "Active Su Cuenta en www.recyclothes.cl";
                emailDestino = ((RegistroClienteDTO)object).getCliente().getCorreo();
            }else if(object instanceof DatosCodigoReservaDTO){
                DatosCodigoReservaDTO contact = (DatosCodigoReservaDTO)object;
                String codigoReserva = contact.getReserva().getIdReserva()+"-"+contact.getReserva().getCodigoReserva();
                asunto = "Notificacion de Reserva Productos # "+codigoReserva;
                emailDestino = ((DatosCodigoReservaDTO)object).getReserva().getCliente().getCorreo();
            }else if(object instanceof ContactoDTO){
                asunto = "Notificacion Contacto Usuario";
                emailDestino = "genesiscastillof@gmail.com";
            }else if(object instanceof ClienteDTO){
                ClienteDTO clienteDTO = (ClienteDTO)object;
                asunto = "Notificacion Contacto - Recuperar Clave";
                emailDestino = clienteDTO.getCorreo();
            }
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
            // Set Subject: header field
            message.setSubject(asunto);
            // Send the actual HTML message, as big as you like
            message.setContent(generarHtml(accionEnum , object),"text/html;charset=utf-8");
            // Send message
            Transport.send(message);
        }catch(Exception e){
            LOGGER.error("ERROR sendEmail",e);
        }
    }

    @Override
    public String generarTokenContacto(String idSession) {
        String tokenGenerado = Utils.generarCodigoReserva();
        mapaTokenContacto.put(idSession , tokenGenerado);
        return tokenGenerado;
    }

    @Override
    @Asynchronous
    public void eliminarTokenContacto(String idSession){
        if(mapaTokenContacto.containsKey(idSession)){
            mapaTokenContacto.remove(idSession);
        }
    }

    @Override
    public Boolean validarTokenContacto(String idSession, String tokenIngresado ){
        Boolean status = Boolean.FALSE;
        tokenIngresado = tokenIngresado.toUpperCase();
        if(mapaTokenContacto.containsKey(idSession)){
            status  = mapaTokenContacto.get(idSession).toUpperCase().equals(tokenIngresado);
        }
        return status;
    }
    private String generarHtml(AccionEnum accionEnum , Object object) {
        LOGGER.info("generarHtml " + accionEnum.name());
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        VelocityContext context = new VelocityContext();

        String templateHtml = "";
        if(AccionEnum.GENERAR_RESERVA_PEDIDOS.equals( accionEnum)){
            templateHtml = "reserva-email.vm";
            DatosCodigoReservaDTO datosCodigoReservaDTO= (DatosCodigoReservaDTO)object;

            List productList = new ArrayList();
            for(ProductoDTO productoDTO : datosCodigoReservaDTO.getProductoList() )     {
                Map<String,String> map = new HashMap();
                map.put("descripcion", productoDTO.getDescripcion());
                map.put("precioProducto", "$ "+productoDTO.getPrecioProducto());
                productList.add( map );
            }
            context.put("urlAppName" , urlAppName );
            String idReserva =  datosCodigoReservaDTO.getReserva().getIdReserva().toString();
            String codigoReserva = datosCodigoReservaDTO.getReserva().getCodigoReserva();
            context.put("idReserva-CodigoReserva", idReserva.concat("-").concat(codigoReserva));
            context.put("nombreCliente" ,   datosCodigoReservaDTO.getReserva().getCliente().getNombres());
            context.put("montoTotal", "$ " + datosCodigoReservaDTO.getReserva().getMontoTotal());
            context.put("productList", productList.toArray());
        }else if(AccionEnum.REGISTRAR_CLIENTE.equals( accionEnum)){
            templateHtml = "activacion-email.vm";
            RegistroClienteDTO registroClienteDTO = (RegistroClienteDTO)object;
            context.put("nombreClienteNuevo", registroClienteDTO.getCliente().getNombres());
            context.put("httpServletActivacion", "http://web-babycaprichitos.rhcloud.com/RecyclothesEAR-web/confirmacion?token="+registroClienteDTO.getCliente().getToken());
        }else if(AccionEnum.RECUPERAR_CLAVE_USUARIO.equals( accionEnum)){
            templateHtml = "recuperacion-clave.vm";
            ClienteDTO clienteDTO = (ClienteDTO)object;
            context.put("nombreCliente", clienteDTO.getNombres());
            context.put("password", clienteDTO.getPassword());
        }else if(AccionEnum.CONTACTO_MENSAJE_USUARIO.equals(accionEnum)){
            templateHtml = "contacto-usuario.vm";
            ContactoDTO contactoDTO = (ContactoDTO) object;
            context.put("nombreContacto" , contactoDTO.getNombre());
            context.put("correoContacto" , contactoDTO.getCorreo());
            context.put("asuntoContacto" , contactoDTO.getAsunto());
            context.put("mensajeContacto" , contactoDTO.getMensaje());
        }
        context.put("contactoRecyclothes" ,urlAppName+"?rv=contacto");
        Template template = ve.getTemplate( templateHtml );

        StringWriter writer = new StringWriter();

        template.merge(context, writer);
        return writer.toString();
    }
}
