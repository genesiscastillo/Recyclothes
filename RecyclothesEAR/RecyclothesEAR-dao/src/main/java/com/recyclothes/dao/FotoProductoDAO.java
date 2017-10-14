package com.recyclothes.dao;

import com.recyclothes.dao.entity.FotoProducto;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Cesar on 21-03-2016.
 */
@Local
public interface FotoProductoDAO {

    FotoProducto obtenerFotoProducto(Long idFotoProducto);
    FotoProducto registrarFotoProducto(FotoProducto fotoProducto);
    FotoProducto actualizarFotoProducto(FotoProducto fotoProducto);
    List<FotoProducto> obtenerListaFotosProductosNuevo(Long numeroPaginaActual , int TOTAL_FOTOS_POR_PAGINA);
    Long obtenerNumeroPaginacion( int TOTAL_FOTOS_POR_PAGINA);
    Long obtenerSiguienteIdFotoProducto();
    void eliminarFotoProducto(Long idFotoProducto);
    List<FotoProducto> obtenerFotosAdjuntosDelProducto(Long idProducto);
}
