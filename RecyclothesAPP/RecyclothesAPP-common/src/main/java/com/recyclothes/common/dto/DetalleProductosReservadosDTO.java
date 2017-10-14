package com.recyclothes.common.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cesar on 13-03-2016.
 */
public class DetalleProductosReservadosDTO implements Serializable  {

    private List<ProductoDTO> productoList = new ArrayList<ProductoDTO>();
    private BigInteger montoTotal = BigInteger.ZERO;

    public List<ProductoDTO> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<ProductoDTO> productoList) {
        this.productoList = productoList;
    }

    public BigInteger getMontoTotal() {
        for (ProductoDTO productoDTO : productoList){
            montoTotal = montoTotal.add(new BigInteger( productoDTO.getPrecioProducto().toString() ));
        }
        return montoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetalleProductosReservadosDTO that = (DetalleProductosReservadosDTO) o;

        if (productoList != null ? !productoList.equals(that.productoList) : that.productoList != null) return false;
        return !(montoTotal != null ? !montoTotal.equals(that.montoTotal) : that.montoTotal != null);

    }

    @Override
    public int hashCode() {
        int result = productoList != null ? productoList.hashCode() : 0;
        result = 31 * result + (montoTotal != null ? montoTotal.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DetalleProductosReservadosDTO{" +
                "productoList=" + productoList +
                ", montoTotal=" + montoTotal +
                '}';
    }
}
