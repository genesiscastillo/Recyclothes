package com.recyclothes.singleton;

import com.recyclothes.common.dto.*;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.EstadoReservaEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.dao.*;
import com.recyclothes.dao.entity.*;
import com.recyclothes.dto.UsuarioDTO;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.websocket.Session;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class CatalogoSingleton implements CatalogoService {

    static final Logger LOGGER = Logger.getLogger(CatalogoSingleton.class);

    @EJB
    ReservaDAO reservaDAO;

    @EJB
    DetalleReservaDAO detalleReservaDAO;

    @EJB
    ProductoDAO productoDAO;

    @EJB
    ClienteDAO clienteDAO;

    @EJB
    FotoProductoDAO fotoProductoDAO;

    @EJB
    AccesoDAO accesoDAO;


    Map<Long , DetalleProductoDTO> mapaDetalleProducto;
    Map<CatalogoEnum, Map<TallaEnum, List<ProductoDTO>>> mapaCatalogo;
    Map<String,List<ProductoDTO>> mapaSessionPedidos;
    Map<String,UsuarioDTO> mapaUsuario;

    @PostConstruct
    public void contextInitialized() {

        List<String> stringList = Arrays.asList("AA","BBBB","CCC");
        stringList.stream().forEach(System.out::println);
        LOGGER.info("\n\n\t\t**************************************************");
        LOGGER.info("\t\tCreating CatalogoSingleton and only one instance here!");

        if(mapaUsuario != null) {
            for(UsuarioDTO usuarioDTO : mapaUsuario.values()) {
                try {
                    usuarioDTO.getSession().close();
                }catch(IOException ex){
                    LOGGER.error("Tratando de matar al usuarioDTO ", ex);
                }
            }
        }
        mapaDetalleProducto = new LinkedHashMap<>();
        mapaUsuario = new ConcurrentHashMap<>();
        mapaCatalogo = new ConcurrentHashMap<>();
        mapaSessionPedidos = new ConcurrentHashMap<>();
        for(CatalogoEnum catalogoEnum : CatalogoEnum.values()) {
            LOGGER.info("******** Catalogo " + catalogoEnum.name());
            Map<TallaEnum , List<ProductoDTO>> mapaTallas = new HashMap<>();
            for (TallaEnum tallaEnum : TallaEnum.values()) {
                mapaTallas.put(tallaEnum , new ArrayList<ProductoDTO>());
            }
            mapaCatalogo.put(catalogoEnum, mapaTallas);
        }
        int total_foto_por_pagina = 24;
        List<Producto> productoList = productoDAO.obtenerProductosDisponibles(total_foto_por_pagina);
        for (Producto producto : productoList)  {
            actualizarProductoCatalogo(producto);
        }
        LOGGER.info("********************************************************");
        for(CatalogoEnum catalogoEnum : CatalogoEnum.values()) {
            LOGGER.info("******** Catalogo " + catalogoEnum.name());
            for (TallaEnum tallaEnum : TallaEnum.values()) {
                LOGGER.info("******** \tTalla "+tallaEnum.name()+" total = "+mapaCatalogo.get(catalogoEnum).get(tallaEnum).size() );
            }
        }
        LOGGER.info("********************************************************");
        Map<String, String> mapaEnv = System.getenv();
        for(String nameEnv : mapaEnv.keySet()){
            LOGGER.info("***** " + nameEnv + " : " + mapaEnv.get(nameEnv));
        }
        LOGGER.info("\n\n\t\t********************************************************");
    }

    @Override
    @Lock(LockType.WRITE)
    public ReservaDTO reservarProductos(String idSession , String comentarios) {
        LOGGER.info("reservarProductos idSession=" + idSession);
        List<ProductoDTO> productoDTOs= mapaSessionPedidos.get(idSession);
        List<Producto> productoList = new ArrayList<>();
        for(ProductoDTO productoDTO : productoDTOs){
            Producto producto = Translation.convert(productoDTO);
            productoList.add(producto);
        }
        ClienteDTO clienteDTO = mapaUsuario.get(idSession).getCliente();
        Cliente cliente = Translation.convert(clienteDTO);
        Reserva reserva = productoDAO.reservarProductos(cliente, productoList, comentarios);
        reserva = reservaDAO.generarReserva(reserva);
        ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
        for(Producto producto : productoList){
            DetalleReserva detalleReserva = new DetalleReserva();
            detalleReserva.setReserva(reserva);
            detalleReserva.setProducto(producto);
            detalleReservaDAO.agregarDetalleReserva(detalleReserva);
        }
        mapaSessionPedidos.get(idSession).clear();
        return reservaDTO;
    }

    @Override
    @Lock(LockType.READ)
    public UsuarioDTO validarUsuario(String idSession, String email ,Session session) {
        LOGGER.info("validarUsuario =" + email);
        UsuarioDTO usuarioDTO = null;
        Cliente cliente = clienteDAO.validarAcceso(email);
        if(cliente != null) {
            ClienteDTO clienteDTO = Translation.convertToDTO(cliente);
            usuarioDTO = mapaUsuario.remove(idSession);
            usuarioDTO.setCliente(clienteDTO);
            mapaUsuario.put(idSession , usuarioDTO);
            mapaSessionPedidos.put(idSession, new ArrayList<ProductoDTO>());
        }
        return usuarioDTO;
    }

    @Override
    @Lock(LockType.READ)
    public List<ReservaDTO> obtenerListaReservaCliente( ClienteDTO clienteDTO)  {
        LOGGER.info("obtenerListaReservaCliente ");
        Cliente cliente = Translation.convert(clienteDTO);
        List<Reserva> reservaList = reservaDAO.obtenerListaReservaCliente(cliente, null , EstadoReservaEnum.PENDIENTE , EstadoReservaEnum.POR_PAGAR);
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        for(Reserva reserva : reservaList){
            ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
            reservaDTOs.add(reservaDTO);
        }
        return reservaDTOs;
    }

    @Override
    @Lock(LockType.READ)
    public PedidoProductoDTO separarProducto(String idSession , Long idProducto){
        LOGGER.info("CatalogoSingleton separarProducto idProducto=" + idProducto);
        PedidoProductoDTO pedidoProductoDTO = new PedidoProductoDTO();
        Producto producto = productoDAO.obtenerProducto(idProducto);
        ProductoDTO productoDTO = Translation.convertToDTO(producto);
        mapaCatalogo.get(productoDTO.getCatalogoEnum()).get(productoDTO.getTallaEnum()).remove(productoDTO);
        mapaDetalleProducto.remove(productoDTO.getIdProducto());
        productoDTO.setEstadoProductoEnum(EstadoProductoEnum.RESERVADO);

        mapaSessionPedidos.get(idSession).add(productoDTO);
        Integer montoTotal = 0;
        Integer totalProductos = mapaSessionPedidos.get(idSession).size();
        for(ProductoDTO producto1 : mapaSessionPedidos.get(idSession)){
            montoTotal = montoTotal + producto1.getPrecioProducto().intValue();
        }
        pedidoProductoDTO.setProducto(productoDTO);
        pedidoProductoDTO.setMontoTotal(montoTotal);
        pedidoProductoDTO.setTotalProductos(totalProductos);
        return pedidoProductoDTO;
    }

    @Override
    public DetalleProductosReservadosDTO obtenerProductosSeparados(String idSession) {
        DetalleProductosReservadosDTO detalleProductosReservadosDTO = new DetalleProductosReservadosDTO();
        detalleProductosReservadosDTO.setProductoList(mapaSessionPedidos.get(idSession));
        return detalleProductosReservadosDTO;
    }

    @Override
    @Lock(LockType.WRITE)
    public UsuarioDTO cerrarSession(String idSession) {
        LOGGER.info("CatalogoSingleton cerrarSession idSession=" + idSession);
        UsuarioDTO usuarioDTO = null;
        if(mapaSessionPedidos.containsKey(idSession)){
            List<ProductoDTO> productos = mapaSessionPedidos.get(idSession);
            for (ProductoDTO productoDTO : productos) {
                actualizarProductoCatalogo(productoDTO.getIdProducto());
            }
            mapaSessionPedidos.remove(idSession);
        }
        if( mapaUsuario.containsKey(idSession)) {
            usuarioDTO =  mapaUsuario.get(idSession);
            usuarioDTO.setFechaAccesoOut(Calendar.getInstance().getTime());
            if(usuarioDTO.getCliente() != null  ) {
                Acceso acceso = new Acceso();
                acceso.setFechaAccesoOut(Calendar.getInstance().getTime());
                acceso.setFechaAccesoIn(usuarioDTO.getFechaAccesoIn());
                acceso.setIdCliente(usuarioDTO.getCliente().getId());
                accesoDAO.generarAccesoUsuario(acceso);
            }
            mapaUsuario.remove(idSession);
        }
        return usuarioDTO;
    }

    @Override
    @Lock(LockType.READ)
    public List<ProductoDTO> obtenerProductosAleatorio() {
        LOGGER.info("obtenerProductosAleatorio");
        int total_foto_por_pagina = 24;
        List<Producto> productoList = productoDAO.obtenerProductosAleatorio(total_foto_por_pagina);
        List<ProductoDTO> productoDTOs = new ArrayList<>();
        for(Producto producto : productoList){
            ProductoDTO productoDTO = Translation.convertToDTO(producto);
            productoDTOs.add(productoDTO);
        }
        return  productoDTOs;
    }

    @Override
    @Lock(LockType.READ)
    public Boolean isLogeado(String idSession){
        LOGGER.info("isLogeado");
        Boolean status = Boolean.FALSE;
        if(mapaUsuario.containsKey(idSession)){
            UsuarioDTO usuarioDTO = mapaUsuario.get(idSession);
            if(usuarioDTO.getCliente() != null ) {
                status = Boolean.TRUE;
            }
        }
        return status;
    }

    @Override
    public UsuarioDTO sessionarUsuario(UsuarioDTO usuarioDTO) {
        return mapaUsuario.put(usuarioDTO.getSession().getId() , usuarioDTO);
    }

    @Override
    @Lock(LockType.READ)
    public Boolean bloquearPedidosUsuario(String idSession){
        LOGGER.info("bloquearPedidosUsuario");
        Boolean status = Boolean.TRUE;
        if(mapaUsuario.containsKey(idSession)) {
            if (mapaUsuario.get(idSession).getCliente() != null) {
                ClienteDTO clienteDTO = (mapaUsuario.get(idSession)).getCliente();
                Cliente cliente = Translation.convert(clienteDTO);
                int totalReserva = reservaDAO.obtenerListaReservaCliente(cliente, null, EstadoReservaEnum.PENDIENTE).size();
                int total_maxima_reservas_pendientes_por_cliente = 3;
                if (totalReserva >= total_maxima_reservas_pendientes_por_cliente) {
                    status = Boolean.FALSE;
                }
            }
        }
        return status;
    }

    @Override
    public List<UsuarioDTO> obtenerListaUsuarioOnline() {
        LOGGER.info("obtenerListaUsuarioOnline "+mapaUsuario.values());
        return new ArrayList<>( mapaUsuario.values() );
    }

    @Override
    @Lock(LockType.READ)
    public DetalleProductoDTO obtenerDetalleProducto(Long idProducto) {
        LOGGER.info("CatalogoSingleton obtenerDetalleProducto idProducto=" + idProducto);
        DetalleProductoDTO detalleProductoDTO = mapaDetalleProducto.get(idProducto);
        int total = mapaCatalogo.get(detalleProductoDTO.getProductoDTO().getCatalogoEnum()).get(detalleProductoDTO.getProductoDTO().getTallaEnum()).size();
        int index = mapaCatalogo.get(detalleProductoDTO.getProductoDTO().getCatalogoEnum()).get(detalleProductoDTO.getProductoDTO().getTallaEnum()).indexOf(detalleProductoDTO.getProductoDTO());
        if(index == total - 1)  {
            detalleProductoDTO.setIsUltimo(Boolean.TRUE);
        }else   {
            Long idProductoSiguiente = mapaCatalogo.get(detalleProductoDTO.getProductoDTO().getCatalogoEnum()).get(detalleProductoDTO.getProductoDTO().getTallaEnum()).get(index + 1).getIdProducto();
            detalleProductoDTO.setIdProductoSiguiente(idProductoSiguiente);
        }
        if(index == 0)  {
            detalleProductoDTO.setIsPrimero(Boolean.TRUE);
        }else   {
            Long idProductoAnterior = mapaCatalogo.get(detalleProductoDTO.getProductoDTO().getCatalogoEnum()).get(detalleProductoDTO.getProductoDTO().getTallaEnum()).get(index - 1).getIdProducto();
            detalleProductoDTO.setIdProductoAnterior(idProductoAnterior);
        }
        return detalleProductoDTO;
    }

    @Override
    @Lock(LockType.WRITE)
    public RegistroClienteDTO registrarClienteNuevo(ClienteDTO clienteDTO2) {
        LOGGER.info("registrarClienteNuevo cliente=" + clienteDTO2);
        Cliente cliente2 = clienteDAO.validarAcceso(clienteDTO2.getCorreo());
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO();
        ClienteDTO clienteDTO = null;
        if(cliente2 == null) {
            Cliente clienteNuevo = Translation.convert(clienteDTO2);
            Cliente cliente3= clienteDAO.registrarCliente(clienteNuevo);
            clienteDTO = Translation.convertToDTO(cliente3);
            registroClienteDTO.setIsNuevo(Boolean.TRUE);
        }else   {
            clienteDTO = Translation.convertToDTO(cliente2);
        }
        registroClienteDTO.setCliente(clienteDTO);
        return registroClienteDTO;
    }

    @Override
    @Lock(LockType.READ)
    public List<ProductoDTO> obtenerProductosPorTalla(CatalogoEnum catalogoEnum, TallaEnum tallaEnum , Integer pagina ) {
        LOGGER.info("CatalogoSingleton obtenerProductosPorTalla pagina = "+pagina);
        List<ProductoDTO> productoDTOs = new ArrayList<>();
        Integer pageNumber = obtenerNumeroPaginas(catalogoEnum, tallaEnum);
        Integer totalProductosSeleccionado = mapaCatalogo.get(catalogoEnum).get(tallaEnum).size();
        Boolean status = Boolean.TRUE;
        int total_fotos_por_pagina = 24;
        if( totalProductosSeleccionado != 0 ) {
            do {
                if (pagina <= pageNumber) {
                    status = Boolean.FALSE;

                    for (int i = ((pagina - 1) * total_fotos_por_pagina), j = 0; j < total_fotos_por_pagina; j++, i++) {
                        if( i < totalProductosSeleccionado ) {
                            ProductoDTO producto = mapaCatalogo.get(catalogoEnum).get(tallaEnum).get(i);
                            productoDTOs.add(producto);
                        } else {
                            break;
                        }
                    }
                } else if(pagina == 1) {
                    break;
                }else{
                    pagina--;
                }
            } while (status);
        }
        return productoDTOs;
    }

    @Override
    @Lock(LockType.READ)
    public Integer obtenerNumeroPaginas(CatalogoEnum catalogoEnum, TallaEnum tallaEnum) {
        LOGGER.info("obtenerNumeroPaginas catalogoEnum=" + catalogoEnum.name()+" - tallaEnum="+tallaEnum.name());
        Integer countResult = mapaCatalogo.get(catalogoEnum).get(tallaEnum).size();
        int total_fotos_por_pagina = 24;
        Integer pageSize = total_fotos_por_pagina;
        Integer pageNumber;
        if(countResult % pageSize == 0) {
            pageNumber = ((countResult / pageSize));
        }else{
            pageNumber = ((countResult / pageSize) + 1);
        }
        return pageNumber;
    }

    @Override
    @Lock(LockType.WRITE)
    public PedidoProductoDTO habilitarProducto(String idSession,Long idProducto) {
        LOGGER.info("habilitarProducto idProducto="+idProducto);
        PedidoProductoDTO pedidoProductoDTO = new PedidoProductoDTO();
        actualizarProductoCatalogo(idProducto);
        ProductoDTO productoDTO = mapaDetalleProducto.get(idProducto).getProductoDTO();
        mapaSessionPedidos.get(idSession).remove(productoDTO);
        Integer montoTotal = 0;
        for(ProductoDTO producto1 : mapaSessionPedidos.get(idSession)){
            montoTotal = montoTotal + producto1.getPrecioProducto().intValue();
        }
        pedidoProductoDTO.setProducto(productoDTO);
        pedidoProductoDTO.setMontoTotal(montoTotal);
        return pedidoProductoDTO;
    }

    @Override
    @Lock(LockType.READ)
    public DatosCodigoReservaDTO obtenerProductosCodigoReserva(String codigoReserva){
        LOGGER.info("obtenerProductosCodigoReserva codigoReserva "+codigoReserva);
        DatosCodigoReservaDTO datosCodigoReservaDTO = new DatosCodigoReservaDTO();
        Reserva reserva  = reservaDAO.obtenerReservaPorCodigoReserva(codigoReserva);
        ReservaDTO reservaDTO = Translation.convertToDTO( reserva);
        List<DetalleReserva> detalleReservaList = detalleReservaDAO.obtenerProductosReserva(reservaDTO.getIdReserva());
        List<ProductoDTO> productoDTOs = new ArrayList<>();
        for(DetalleReserva detalleReserva : detalleReservaList){
            Producto producto = productoDAO.obtenerProducto(detalleReserva.getProducto().getIdProducto());
            ProductoDTO productoDTO = Translation.convertToDTO(producto);
            productoDTOs.add(productoDTO);
        }
        datosCodigoReservaDTO.setReserva(reservaDTO);
        datosCodigoReservaDTO.setProductoList(productoDTOs);
        return datosCodigoReservaDTO;
    }

    @Override
    @Lock(LockType.READ)
    public ClienteDTO recuperarClaveUsuario(String email){
        LOGGER.info("recuperarClaveUsuario");
        Cliente cliente =clienteDAO.recuperarClaveUsuario(email);
        ClienteDTO clienteDTO = Translation.convertToDTO(cliente);
        return  clienteDTO;
    }

    @Override
    @Asynchronous
    @Lock(LockType.WRITE)
    public void actualizarProductoCatalogo(Long idProducto) {
        LOGGER.info("actualizarProductoCatalogo idProducto="+idProducto);
        Producto producto = productoDAO.obtenerProducto(idProducto);
        actualizarProductoCatalogo( producto );
    }
    private void actualizarProductoCatalogo(Producto producto) {
        ProductoDTO productoDTO = Translation.convertToDTO(producto);
        List<FotoProducto> fotoProductoList = fotoProductoDAO.obtenerFotosAdjuntosDelProducto(productoDTO.getIdProducto());
        List<FotoProductoDTO> fotoProductoDTOs = Translation.converToDTO(fotoProductoList);
        DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
        detalleProductoDTO.setFotoProductoDTOs(fotoProductoDTOs);
        detalleProductoDTO.setProductoDTO(productoDTO);
        mapaCatalogo.get(productoDTO.getCatalogoEnum()).get(productoDTO.getTallaEnum()).add(productoDTO);
        mapaDetalleProducto.put(detalleProductoDTO.getProductoDTO().getIdProducto(), detalleProductoDTO);
    }

    @Override
    @Asynchronous
    @Lock(LockType.WRITE)
    public void disponibilizarProductosReservaAnulada(Long idReserva) {
        LOGGER.info("disponibilizarProductosReservaAnulada idReserva="+idReserva);
        List<DetalleReserva> detalleReservaList = detalleReservaDAO.obtenerProductosReserva(idReserva);
        for(DetalleReserva detalleReserva : detalleReservaList){
            Producto producto = productoDAO.obtenerProducto(detalleReserva.getProducto().getIdProducto());
            producto.setEstadoProductoEnum(EstadoProductoEnum.DISPONIBLE);
            producto = productoDAO.actualizarProducto(producto);
            actualizarProductoCatalogo(producto);
        }
    }

    @Override
    @Lock(LockType.WRITE)
    public ProductoDTO cambiarEstadoProducto(Long idProducto , EstadoProductoEnum estadoProductoEnum) {
        LOGGER.info("cambiarEstadoProducto idProducto="+idProducto+" "+estadoProductoEnum.name());
        Producto producto = productoDAO.obtenerProducto(idProducto);
        ProductoDTO productoDTO = Translation.convertToDTO(producto);
        if( EstadoProductoEnum.PENDIENTE.equals(estadoProductoEnum) && mapaCatalogo.get(producto.getCatalogoEnum()).get(producto.getTalla()).contains(productoDTO))    {
            mapaCatalogo.get(producto.getCatalogoEnum()).get(producto.getTalla()).remove(productoDTO);
            producto.setEstadoProductoEnum(estadoProductoEnum);
            productoDAO.actualizarProducto(producto);
        }else{
            producto.setEstadoProductoEnum(EstadoProductoEnum.DISPONIBLE);
            productoDAO.actualizarProducto(producto);
            actualizarProductoCatalogo(idProducto);
        }
        productoDTO.setEstadoProductoEnum(estadoProductoEnum);
        return productoDTO;
    }
}
