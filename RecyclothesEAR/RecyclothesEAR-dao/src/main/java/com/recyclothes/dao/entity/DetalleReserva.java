package com.recyclothes.dao.entity;

import javax.persistence.*;

/**
 * Created by Cesar on 31-03-2016.
 */
@Entity
@Table(name ="tb_detalle_reserva")
public class DetalleReserva {

    @Id
    @Column(name = "id_detalle_reserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleReserva;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_reserva")
    private Reserva reserva;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_producto")
    private Producto producto;

    public Long getIdDetalleReserva() {
        return idDetalleReserva;
    }

    public void setIdDetalleReserva(Long idDetalleReserva) {
        this.idDetalleReserva = idDetalleReserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleReserva{" +
                "idDetalleReserva=" + idDetalleReserva +
                ", reserva=" + reserva +
                ", producto=" + producto +
                '}';
    }
}
