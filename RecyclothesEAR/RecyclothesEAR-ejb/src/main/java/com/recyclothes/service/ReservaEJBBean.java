package com.recyclothes.service;

import com.recyclothes.dao.ClienteDAO;
import com.recyclothes.dao.ReservaDAO;
import com.recyclothes.ejbclient.ReservaEJB;
import org.apache.log4j.Logger;

/**
 * Created by Cesar on 18-07-2016.
 */
@Stateless
@Remote(ReservaEJB.class)
public class ReservaEJBBean implements ReservaEJB {

    static final Logger LOGGER = Logger.getLogger(ReservaEJBBean.class);
    @EJB
    ReservaDAO reservaDAO;

    @EJB
    ClienteDAO clienteDAO;


/*
    @Override
    public List<ReservaDTO> obtenerListaReservaPendientes(Long idReserva , EstadoReservaEnum... estadoReservaEnums) {
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        List<Reserva> reservaList = reservaDAO.obtenerListaReserva( idReserva , estadoReservaEnums);
        for(Reserva reserva : reservaList){
            ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
            reservaDTOs.add(reservaDTO);
        }
        return reservaDTOs;
    }

    @Override
    public Boolean anularReserva(ReservaDTO reservaDTO) {
        LOGGER.info("anularReserva idReserva = "+reservaDTO.getIdReserva());
        Reserva reserva = reservaDAO.obtenerReserva(reservaDTO.getIdReserva() , null);
        reserva.setEstadoReservaEnum(EstadoReservaEnum.ANULADO);
        reservaDAO.actualizarReserva(reserva);
        return Boolean.TRUE;
    }

    @Override
    public void actualizarReservaEntrega(ReservaDTO reservaDTO) {
        LOGGER.info("actualizarReservaEntrega idReserva = "+reservaDTO.getIdReserva() + " "+ Utils.getDiaFechaMes(reservaDTO.getFechaEntrega()));
        Reserva reserva = reservaDAO.obtenerReserva(reservaDTO.getIdReserva() , null);
        reserva.setFechaEntrega(reservaDTO.getFechaEntrega());
        reserva.setEstadoReservaEnum(reservaDTO.getEstadoReservaEnum());
        reserva.setDatosDeEntrega(reservaDTO.getDatosDeEntrega());
        reserva.setHoraEntregaEnum(reservaDTO.getHoraEntregaEnum());
        reservaDAO.actualizarReserva(reserva);
    }

    @Override
    public List<ReservaDTO> obtenerListaEntregasPendientes() {
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        List<Reserva> reservaList = reservaDAO.obtenerListaReserva(null, EstadoReservaEnum.PAGADO, EstadoReservaEnum.POR_PAGAR);
        for(Reserva reserva : reservaList )     {
            ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
            if(reservaDTO != null)  {
                reservaDTOs.add(reservaDTO);
            }
        }
        return reservaDTOs;
    }

    @Override
    public List<ReservaDTO> obtenerListaReservaPorCliente(Long idCliente) {
        List<ReservaDTO> reservaDTOs = new ArrayList<>();
        Cliente cliente = clienteDAO.obtenerCliente(idCliente);
        List<Reserva> reservaList = reservaDAO.obtenerListaReservaCliente(cliente, null, null);
        for(Reserva reserva : reservaList){
            ReservaDTO reservaDTO = Translation.convertToDTO(reserva);
            reservaDTOs.add(reservaDTO);
        }
        return reservaDTOs;
    }
*/

}
