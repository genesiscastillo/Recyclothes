package com.recyclothes.dao;

import com.recyclothes.dao.entity.DetalleReserva;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Cesar on 12-03-2016.
 */
@Local
public interface DetalleReservaDAO {

    DetalleReserva agregarDetalleReserva(DetalleReserva detalleReserva);
    List<DetalleReserva> obtenerProductosReserva(Long idReserva);
}
