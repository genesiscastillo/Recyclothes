package com.recyclothes.dao;

import com.recyclothes.common.dto.*;
import com.recyclothes.dao.entity.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 18-07-2016.
 */
public class Translation {

    public static AccesoDTO convertToDTO(Acceso acceso){
        AccesoDTO accesoDTO = null;
        if(acceso != null){
            accesoDTO = new AccesoDTO();
            accesoDTO.setIdAcceso(acceso.getIdAcceso());
            accesoDTO.setIdCliente(acceso.getIdCliente());
            accesoDTO.setFechaAccesoIn(acceso.getFechaAccesoIn());
            accesoDTO.setFechaAccesoOut(acceso.getFechaAccesoOut());
        }
        return accesoDTO;
    }

    public static Cliente convert(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setFechaRegistroCreacion(clienteDTO.getFechaRegistroCreacion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setPassword(clienteDTO.getPassword());
        cliente.setFechaUltimaVisita(clienteDTO.getFechaUltimaVisita());
        cliente.setToken(clienteDTO.getToken());
        cliente.setNombres(clienteDTO.getNombres());
        return cliente;
    }

    public static Producto convert(ProductoDTO productoDTO){
        Producto producto = new Producto();
        producto.setIdProducto(productoDTO.getIdProducto());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecioProducto(productoDTO.getPrecioProducto());
        producto.setIdFotoProducto(productoDTO.getIdFotoProducto());
        producto.setCatalogoEnum(productoDTO.getCatalogoEnum());
        producto.setTalla(productoDTO.getTallaEnum());
        producto.setEstadoProductoEnum(productoDTO.getEstadoProductoEnum());
        producto.setFecIngreso(productoDTO.getFecIngreso());
        return producto;
    }

    public static FotoProducto convert(FotoProductoDTO fotoProductoDTO){
        FotoProducto fotoProducto = new FotoProducto();
        fotoProducto.setIdProducto(fotoProductoDTO.getIdProducto());
        fotoProducto.setIdFotoProducto(fotoProductoDTO.getIdFotoProducto());
        fotoProducto.setFoto(fotoProductoDTO.getFoto());
        return fotoProducto;
    }

    public static ReservaDTO convertToDTO(Reserva reserva){
        ReservaDTO reservaDTO = null;
        if(reserva != null){
            reservaDTO = new ReservaDTO();
            reservaDTO.setIdReserva(reserva.getIdReserva());
            reservaDTO.setCliente( convertToDTO(reserva.getCliente()));
            reservaDTO.setCodigoReserva(reserva.getCodigoReserva());
            reservaDTO.setComentarios(reserva.getComentarios());
            reservaDTO.setDatosDeEntrega(reserva.getDatosDeEntrega());
            reservaDTO.setEstadoReservaEnum(reserva.getEstadoReservaEnum());
            reservaDTO.setFechaEntrega(reserva.getFechaEntrega());
            reservaDTO.setMontoPagar(reserva.getMontoPagar());
            reservaDTO.setMontoTotal(reserva.getMontoTotal());
            reservaDTO.setFechaReserva(reserva.getFechaReserva());
            reservaDTO.setHoraEntregaEnum(reserva.getHoraEntregaEnum());
        }
        return reservaDTO;
    }

    public static FotoProductoDTO convertToDTO(FotoProducto fotoProducto ){
        FotoProductoDTO fotoProductoDTO = null;
        if(fotoProducto != null){
            fotoProductoDTO = new FotoProductoDTO();
            fotoProductoDTO.setIdFotoProducto(fotoProducto.getIdFotoProducto());
            fotoProductoDTO.setIdProducto(fotoProducto.getIdProducto());
        }
        return fotoProductoDTO;
    }

    public static ClienteDTO convertToDTO(Cliente cliente){
        ClienteDTO clienteDTO = null;
        if(cliente != null){
            clienteDTO = new ClienteDTO();
            clienteDTO.setToken(cliente.getToken());
            clienteDTO.setCorreo(cliente.getCorreo());
            clienteDTO.setId(cliente.getId());
            clienteDTO.setApellidos(cliente.getApellidos());
            clienteDTO.setNombres(cliente.getNombres());
            clienteDTO.setFechaRegistroCreacion(cliente.getFechaRegistroCreacion());
            clienteDTO.setFechaUltimaVisita(cliente.getFechaUltimaVisita());
            clienteDTO.setTelefono(cliente.getTelefono());
            clienteDTO.setPassword(cliente.getPassword());
        }
        return clienteDTO;
    }

    public static DetalleReservaDTO convertToDTO(DetalleReserva detalleReserva){
        DetalleReservaDTO detalleReservaDTO = null;
        if(detalleReserva != null)  {
            detalleReservaDTO = new DetalleReservaDTO();
            detalleReservaDTO.setProductoDTO( convertToDTO(detalleReserva.getProducto()));
            detalleReservaDTO.setIdDetalleReserva(detalleReserva.getIdDetalleReserva());
            detalleReservaDTO.setReservaDTO( convertToDTO( detalleReserva.getReserva()));
        }
        return detalleReservaDTO;
    }

    public static ProductoDTO convertToDTO(Producto producto){
        ProductoDTO productoDTO = null;
        if(producto != null)    {
            productoDTO = new ProductoDTO();
            productoDTO.setIdProducto(producto.getIdProducto());
            productoDTO.setDescripcion(producto.getDescripcion());
            productoDTO.setIdFotoProducto(producto.getIdFotoProducto());
            productoDTO.setFecIngreso(producto.getFecIngreso());
            productoDTO.setPrecioProducto(producto.getPrecioProducto());
            productoDTO.setCatalogoEnum(producto.getCatalogoEnum());
            productoDTO.setEstadoProductoEnum(producto.getEstadoProductoEnum());
            productoDTO.setTallaEnum(producto.getTalla());
        }
        return productoDTO;
    }

    public static List<FotoProductoDTO> converToDTO(List<FotoProducto> fotoProductoList ){
        List<FotoProductoDTO> fotoProductoDTOs = new ArrayList<>();
        for(FotoProducto fotoProducto : fotoProductoList ){
            FotoProductoDTO fotoProductoDTO = convertToDTO(fotoProducto);
            fotoProductoDTOs.add(fotoProductoDTO);
        }
        return fotoProductoDTOs;
    }

}
