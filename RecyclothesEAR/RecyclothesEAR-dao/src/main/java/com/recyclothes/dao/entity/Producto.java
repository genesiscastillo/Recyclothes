package com.recyclothes.dao.entity;

import com.recyclothes.common.enums.CatalogoEnum;
import com.recyclothes.common.enums.EstadoProductoEnum;
import com.recyclothes.common.enums.TallaEnum;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tb_producto")
public class Producto {

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado_producto")
    @Enumerated(EnumType.ORDINAL)
    private EstadoProductoEnum estadoProductoEnum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_ingreso")
    private Date fecIngreso;

    @Column(name = "catalogo_producto")
    @Enumerated(EnumType.ORDINAL)
    private CatalogoEnum catalogoEnum;

    @Column(name = "talla_producto")
    @Enumerated(EnumType.ORDINAL)
    private TallaEnum tallaEnum;

    @Column(name = "precio_producto")
    private Long precioProducto;

    @Column(name = "id_foto_producto")
    private Long idFotoProducto;

    @PrePersist
    public void init(){
        setEstadoProductoEnum(EstadoProductoEnum.PENDIENTE);
        setFecIngreso(Calendar.getInstance().getTime());
    }

    public TallaEnum getTalla() {
        return tallaEnum;
    }

    public void setTalla(TallaEnum talla) {
        this.tallaEnum = talla;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Long precioProducto) {
        this.precioProducto = precioProducto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoProductoEnum getEstadoProductoEnum() {
        return estadoProductoEnum;
    }

    public void setEstadoProductoEnum(EstadoProductoEnum estadoProducto) {
        this.estadoProductoEnum = estadoProducto;
    }

    public Date getFecIngreso() {
        return fecIngreso;
    }

    public void setFecIngreso(Date fecIngreso) {
        this.fecIngreso = fecIngreso;
    }

    public CatalogoEnum getCatalogoEnum() {
        return catalogoEnum;
    }

    public void setCatalogoEnum(CatalogoEnum catalogoEnum) {
        this.catalogoEnum = catalogoEnum;
    }

    public Long getIdFotoProducto() {
        return idFotoProducto;
    }

    public void setIdFotoProducto(Long idFotoProducto) {
        this.idFotoProducto = idFotoProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precioProducto +
                ", estado=" + estadoProductoEnum +
                ", idFotoProducto=" + idFotoProducto +
                ", catalogoEnum=" + catalogoEnum +
                ", talla=" + tallaEnum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return idProducto != null ? idProducto.equals(producto.idProducto) : producto.idProducto == null;

    }

    @Override
    public int hashCode() {
        return idProducto != null ? idProducto.hashCode() : 0;
    }
}
