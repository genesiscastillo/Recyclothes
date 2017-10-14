package com.recyclothes.common.dto;


import java.io.Serializable;

public class PedidoProductoDTO  implements Serializable {
    private ProductoDTO producto;
    private Integer montoTotal;
    private Integer totalProductos;

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public Integer getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Integer totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedidoProductoDTO that = (PedidoProductoDTO) o;

        if (producto != null ? !producto.equals(that.producto) : that.producto != null) return false;
        if (montoTotal != null ? !montoTotal.equals(that.montoTotal) : that.montoTotal != null) return false;
        return !(totalProductos != null ? !totalProductos.equals(that.totalProductos) : that.totalProductos != null);

    }

    @Override
    public int hashCode() {
        int result = producto != null ? producto.hashCode() : 0;
        result = 31 * result + (montoTotal != null ? montoTotal.hashCode() : 0);
        result = 31 * result + (totalProductos != null ? totalProductos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PedidoProductoDTO{" +
                "producto=" + producto +
                ", montoTotal=" + montoTotal +
                ", totalProductos=" + totalProductos +
                '}';
    }
}
