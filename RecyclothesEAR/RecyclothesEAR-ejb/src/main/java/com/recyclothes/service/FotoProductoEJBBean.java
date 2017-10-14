package com.recyclothes.service;

import com.recyclothes.dao.FotoProductoDAO;
import com.recyclothes.ejbclient.FotoProductoEJB;

/**
 * Created by Cesar on 18-07-2016.
 */
@Stateless
@Remote(FotoProductoEJB.class)
public class FotoProductoEJBBean implements FotoProductoEJB {

    @EJB
    FotoProductoDAO fotoProductoDAO;

/*
    @Override
    public FotoProductoDTO obtenerFotoProducto(Long idFotoProducto) {
        return null;
    }

    @Override
    public FotoProductoDTO registrarFotoProducto(FotoProductoDTO fotoProducto) {
        return null;
    }

    @Override
    public void actualizarFotoProducto(FotoProductoDTO fotoProducto) {

    }

    @Override
    public List<FotoProductoDTO> obtenerListaFotosProductosNuevo(Long numeroPaginaActual) {
        List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<>();
        List<FotoProducto> fotoProductos = fotoProductoDAO.obtenerListaFotosProductosNuevo(numeroPaginaActual);
        for(FotoProducto fotoProducto : fotoProductos){
            FotoProductoDTO fotoProductoDTO = Translation.convertToDTO(fotoProducto );
            if(fotoProductoDTO != null){
                fotoProductoDTOs.add(fotoProductoDTO);
            }
        }
        return fotoProductoDTOs;
    }

    @Override
    public Long obtenerNumeroPaginacion() {
        return null;
    }

    @Override
    public Long obtenerSiguienteIdFotoProducto() {
        return null;
    }

    @Override
    public void eliminarFotoProducto(Long idFotoProducto) {

    }

    @Override
    public List<FotoProductoDTO> obtenerFotosAdjuntosDelProducto(Long idProducto) {
        return null;
    }
*/
}
