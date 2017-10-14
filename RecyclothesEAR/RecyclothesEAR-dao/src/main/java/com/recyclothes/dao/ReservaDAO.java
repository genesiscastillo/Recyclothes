package com.recyclothes.dao;

import com.recyclothes.common.enums.EstadoReservaEnum;
import com.recyclothes.dao.entity.Cliente;
import com.recyclothes.dao.entity.Reserva;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by Cesar on 12-03-2016.
 */
@Local
public interface ReservaDAO {

    Reserva generarReserva(Reserva reserva);
    List<Reserva> obtenerListaReserva(Long idReserva , EstadoReservaEnum... estadoReservaEnum);
    Reserva obtenerReserva(Long idReserva);
    Reserva obtenerReservaPorCodigoReserva(String codigoReserva);
    Reserva actualizarReserva(Reserva reserva);
    List<Reserva> obtenerListaReservaCliente(Cliente cliente , String codigoReserva , EstadoReservaEnum... estadoReservaEnums);
}
