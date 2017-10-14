package com.recyclothes.common.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 24-07-2016.
 */
public class ResponseDTO implements Serializable {

    private Long totalPrendas;
    private List<DetalleProductoDTO> detalleProductoDTOs = new ArrayList<DetalleProductoDTO>();

    public Long getTotalPrendas() {
        return totalPrendas;
    }

    public void setTotalPrendas(Long totalPrendas) {
        this.totalPrendas = totalPrendas;
    }

    public List<DetalleProductoDTO> getDetalleProductoDTOs() {
        return detalleProductoDTOs;
    }

    public void setDetalleProductoDTOs(List<DetalleProductoDTO> detalleProductoDTOs) {
        this.detalleProductoDTOs = detalleProductoDTOs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseDTO that = (ResponseDTO) o;

        if (totalPrendas != null ? !totalPrendas.equals(that.totalPrendas) : that.totalPrendas != null) return false;
        return !(detalleProductoDTOs != null ? !detalleProductoDTOs.equals(that.detalleProductoDTOs) : that.detalleProductoDTOs != null);

    }

    @Override
    public int hashCode() {
        int result = totalPrendas != null ? totalPrendas.hashCode() : 0;
        result = 31 * result + (detalleProductoDTOs != null ? detalleProductoDTOs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "totalPrendas=" + totalPrendas +
                ", detalleProductoDTOs=" + detalleProductoDTOs +
                '}';
    }
}
