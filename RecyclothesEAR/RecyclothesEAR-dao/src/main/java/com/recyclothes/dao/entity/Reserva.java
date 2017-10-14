package com.recyclothes.dao.entity;

import com.recyclothes.common.enums.EstadoReservaEnum;
import com.recyclothes.common.enums.HoraEntregaEnum;
import com.recyclothes.common.utils.Utils;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tb_reserva")
public class Reserva {

    @Id
    @Column(name = "id_reserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_reserva")
    private Date fechaReserva;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "estado_reserva")
    @Enumerated(EnumType.ORDINAL)
    private EstadoReservaEnum estadoReservaEnum;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_entrega")
    private Date fechaEntrega;

    @Column(name ="cod_reserva")
    private String codigoReserva;

    @Column(name = "costo_reserva")
    private Integer montoTotal;

    @Column(name = "total_a_pagar")
    private Integer montoPagar;

    @Column(name = "datosDeEntrega")
    private String datosDeEntrega;

    @Column(name="hora_entrega")
    @Enumerated(EnumType.ORDINAL)
    private HoraEntregaEnum horaEntregaEnum;

    @PrePersist
    void onCreate() {
        Calendar  calendar = Calendar.getInstance();
        this.setFechaReserva(calendar.getTime());
        this.setEstadoReservaEnum(EstadoReservaEnum.PENDIENTE);
        this.setMontoPagar(this.getMontoTotal());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        this.setFechaEntrega(calendar.getTime());
        this.setMontoPagar(this.getMontoTotal());
        setCodigoReserva(Utils.generarCodigoReserva());
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getDatosDeEntrega() {
        return datosDeEntrega;
    }

    public void setDatosDeEntrega(String datosDeEntrega) {
        this.datosDeEntrega = datosDeEntrega;
    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public EstadoReservaEnum getEstadoReservaEnum() {
        return estadoReservaEnum;
    }

    public void setEstadoReservaEnum(EstadoReservaEnum estadoReservaEnum) {
        this.estadoReservaEnum = estadoReservaEnum;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Integer montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Integer montoPagar) {
        this.montoPagar = montoPagar;
    }

    public HoraEntregaEnum getHoraEntregaEnum() {
        return horaEntregaEnum;
    }

    public void setHoraEntregaEnum(HoraEntregaEnum horaEntregaEnum) {
        this.horaEntregaEnum = horaEntregaEnum;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", cliente=" + cliente +
                ", fechaReserva=" + fechaReserva +
                ", comentarios='" + comentarios + '\'' +
                ", estadoReservaEnum=" + estadoReservaEnum +
                ", fechaEntrega=" + fechaEntrega +
                ", codigoReserva='" + codigoReserva + '\'' +
                ", montoTotal=" + montoTotal +
                ", montoPagar=" + montoPagar +
                ", datosDeEntrega='" + datosDeEntrega + '\'' +
                ", horaEntrega=" + horaEntregaEnum +
                '}';
    }
}
