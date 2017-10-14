package com.recyclothes.singleton;

import com.recyclothes.common.dto.*;
import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.dto.UsuarioDTO;

import javax.websocket.Session;
import java.util.List;

/**
 * Created by Cesar on 11-03-2016.
 */
public interface CatalogoService {
    UsuarioDTO sessionarUsuario(UsuarioDTO usuarioDTO);
    Boolean bloquearPedidosUsuario(String idSession);
    List<ProductoDTO> obtenerProductosAleatorio();
    UsuarioDTO cerrarSession(String idSession);
    PedidoProductoDTO separarProducto(String idSession , Long idProducto);
    DetalleProductosReservadosDTO obtenerProductosSeparados(String idSession);
    ReservaDTO reservarProductos(String idSession, String comentarios);
    UsuarioDTO validarUsuario(String idSession,String email, Session session);
    Boolean isLogeado(String idSession);
    DetalleProductoDTO obtenerDetalleProducto(Long idProducto);
    List<UsuarioDTO> obtenerListaUsuarioOnline();
    RegistroClienteDTO registrarClienteNuevo(ClienteDTO cliente);
    List<ProductoDTO> obtenerProductosPorTalla( CatalogoEnum catalogoEnum, TallaEnum tallaEnum, Integer pagina);
    Integer obtenerNumeroPaginas(CatalogoEnum catalogoEnum , TallaEnum tallaEnum);
    PedidoProductoDTO habilitarProducto(String idSession,Long idProducto);
    List<ReservaDTO> obtenerListaReservaCliente( ClienteDTO cliente);
    DatosCodigoReservaDTO obtenerProductosCodigoReserva(String codigoReserva);
    ClienteDTO recuperarClaveUsuario(String email);
    void actualizarProductoCatalogo(Long idProducto);
    void disponibilizarProductosReservaAnulada(Long idReserva);
    ProductoDTO cambiarEstadoProducto(Long idProducto , EstadoProductoEnum estadoProductoEnum);
}
