package com.recyclothes.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 25-04-2016.
 */
public class DatosCodigoReservaDTO implements Serializable  {

    private ReservaDTO reserva;
    private List<ProductoDTO> productoList = new ArrayList<ProductoDTO>();

    public ReservaDTO getReserva() {
        return reserva;
    }

    public void setReserva(ReservaDTO reserva) {
        this.reserva = reserva;
    }

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatosCodigoReservaDTO that = (DatosCodigoReservaDTO) o;

        if (reserva != null ? !reserva.equals(that.reserva) : that.reserva != null) return false;
        return !(productoList != null ? !productoList.equals(that.productoList) : that.productoList != null);

    }

    @Override
    public int hashCode() {
        int result = reserva != null ? reserva.hashCode() : 0;
        result = 31 * result + (productoList != null ? productoList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DatosCodigoReservaDTO{" +
                "reserva=" + reserva +
                ", productoList=" + productoList +
                '}';
    }
}
