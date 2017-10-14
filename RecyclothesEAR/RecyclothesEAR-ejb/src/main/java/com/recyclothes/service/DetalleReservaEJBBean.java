package com.recyclothes.service;

import com.recyclothes.dao.DetalleReservaDAO;
import com.recyclothes.dao.FotoProductoDAO;
import com.recyclothes.dao.ProductoDAO;
import com.recyclothes.ejbclient.DetalleReservaEJB;

/**
 * Created by Cesar on 18-07-2016.
 */
@Stateless
@Remote(DetalleReservaEJB.class)
public class DetalleReservaEJBBean implements DetalleReservaEJB {

    @EJB
    DetalleReservaDAO detalleReservaDAO;

    @EJB
    ProductoDAO productoDAO;

    @EJB
    FotoProductoDAO fotoProductoDAO;

/*
    @Override
    public DetalleReservaDTO agregarDetalleReserva(DetalleReservaDTO detalleReserva) {
        return null;
    }

    @Override
    public List<DetalleReservaDTO> obtenerProductosReserva(Long idReserva) {
        List<DetalleReservaDTO> detalleReservaDTOList = new ArrayList<>();
        List<DetalleReserva> detalleReservaList = detalleReservaDAO.obtenerProductosReserva(idReserva);
        for(DetalleReserva detalleReserva : detalleReservaList){
            DetalleReservaDTO detalleReservaDTO = Translation.convertToDTO(detalleReserva);

            Producto producto = productoDAO.obtenerProducto(detalleReserva.getProducto().getIdProducto());
            ProductoDTO productoDTO = Translation.convertToDTO(producto);

            FotoProducto fotoProducto = fotoProductoDAO.obtenerFotoProducto(productoDTO.getIdFotoProducto());
            Boolean conFoto = Boolean.TRUE;
            FotoProductoDTO fotoProductoDTO = Translation.convertToDTO(fotoProducto );

            detalleReservaDTO.setProductoDTO(productoDTO);
            //detalleReservaDTO.setFotoProductoDTO(fotoProductoDTO);

            detalleReservaDTOList.add(detalleReservaDTO);
        }
        return detalleReservaDTOList;
    }
*/
}
