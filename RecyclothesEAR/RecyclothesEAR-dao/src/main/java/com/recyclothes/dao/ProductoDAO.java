package com.recyclothes.dao;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;
import com.recyclothes.dao.entity.Cliente;
import com.recyclothes.dao.entity.Producto;
import com.recyclothes.dao.entity.Reserva;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Cesar on 11-03-2016.
 */
@Local
public interface ProductoDAO {
    List<Producto> obtenerProductosAleatorio(int TOTAL_FOTOS_POR_PAGINA);
    Reserva reservarProductos(Cliente cliente, List<Producto> productoList, String comentarios);
    Producto obtenerProducto(Long idProducto);
    Producto registrarProducto(Producto producto);
    List<Producto> obtenerProductosDisponibles(int TOTAL_FOTOS_POR_PAGINA);
    Producto actualizarProducto(Producto producto);
    Long obtenerNumeroPaginacion(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum, int TOTAL_FOTOS_POR_PAGINA);
    Long obtenerTotalPrendas(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum);
    Boolean publicarProductosPendientes(CatalogoEnum catalogoEnum, TallaEnum tallaEnum);
    List<Producto> obtenerProductos(CatalogoEnum catalogoEnum, TallaEnum tallaEnum, EstadoProductoEnum estadoProductoEnum , Long numeroPaginaActual , int TOTAL_FOTOS_POR_PAGINA);
    Long totalProductoPorEstado(CatalogoEnum catalogoEnum , TallaEnum tallaEnum , EstadoProductoEnum estadoProductoEnum);

    //**------- new java 8

    List<Producto> obtenerProductos();
    List<Producto> obtenerProductos(EstadoProductoEnum estadoProductoEnum );
}
