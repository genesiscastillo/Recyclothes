package com.recyclothes.dao.entity;

import javax.persistence.*;
import java.util.Date;

/**
 CREATE TABLE `web`.`tb_acceso` (
 `id_acceso` INT NOT NULL AUTO_INCREMENT,
 `id_cliente` INT NULL,
 `fecha_acceso_in` DATETIME NOT NULL,
 `fecha_acceso_out` DATETIME NULL,
 PRIMARY KEY (`id_acceso`));


 ALTER TABLE `web`.`tb_acceso`
 ADD COLUMN `status` INT NULL AFTER `fecha_acceso_out`;
 */
@Entity
@Table(name = "tb_acceso")
public class Acceso {


    @Id
    @Column(name="id_acceso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAcceso;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_acceso_in")
    private Date fechaAccesoIn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_acceso_out")
    private Date fechaAccesoOut;

    @Column(name = "status")
    private Long status;

    public Long getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Long idAcceso) {
        this.idAcceso = idAcceso;
    }

    public Date getFechaAccesoIn() {
        return fechaAccesoIn;
    }

    public void setFechaAccesoIn(Date fechaAccesoIn) {
        this.fechaAccesoIn = fechaAccesoIn;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaAccesoOut() {
        return fechaAccesoOut;
    }

    public void setFechaAccesoOut(Date fechaAccesoOut) {
        this.fechaAccesoOut = fechaAccesoOut;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Acceso{" +
                "idAcceso=" + idAcceso +
                ", idCliente=" + idCliente +
                ", fechaAccesoIn=" + fechaAccesoIn +
                ", fechaAccesoOut=" + fechaAccesoOut +
                ", status=" + status +
                '}';
    }
}
