package com.recyclothes.singleton;

import com.recyclothes.common.dto.*;
import com.recyclothes.common.enums.*;
import com.recyclothes.dao.entity.*;

import java.util.List;

/**
 * Created by Cesar on 16-03-2016.
 */
public interface AdministracionService {

    Long obtenerNumeroPaginacion();

    Long obtenerNumeroPaginacionProductos(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum);

    List<ReservaDTO> obtenerListaReservaPorCliente(Long idCliente);

    List<ClienteDTO> obtenerListaCliente(String nombres, String correo, String telefono);

    List<EstadisticaDTO> obtenerEstaditicaPorEstado(CatalogoEnum catalogoEnum);

    Long obtenerTotalPrendas(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum);

    boolean confirmacionToken(String idToken);

    void enviarCorreo(AccionEnum accionEnum, Object object);

    String generarTokenContacto(String idSession);

    Boolean validarTokenContacto(String idSession, String tokenIngresado);

    void eliminarTokenContacto(String idSession);

    Long grabarFotoProducto(FotoProductoDTO fotoProductoDTO);

    FotoProducto obtenerFotoProducto(Long idFotoProducto);

    List<FotoProductoDTO> obtenerListaFotosProductos(Long numeroPagina);

    Producto generarProductoNuevo(Producto producto);

    FotoProducto adjuntarFotoProducto(FotoProducto fotoProducto);

    void eliminarFotoProducto(Long idFotoProducto);

    List<DetalleProductoDTO> obtenerProductosCatalogados(CatalogoEnum catalogoEnum , TallaEnum tallaEnum , EstadoProductoEnum estadoProductoEnum, Long numeroPagina);

    void actualizarProducto(ProductoDTO productoDTO);

    List<ReservaDTO> obtenerListaReserva(Long idReserva , EstadoReservaEnum... estadoReservaEnum);

    List<DetalleReservaDTO> obtenerProductosReserva(Long idReserva);

    List<ProductoDTO> anularReserva(Long idReserva);
    void actualizarReserva(Cliente cliente , Reserva reserva);
}