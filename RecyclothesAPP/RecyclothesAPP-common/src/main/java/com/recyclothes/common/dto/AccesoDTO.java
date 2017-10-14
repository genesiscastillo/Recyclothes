package com.recyclothes.common.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cesar on 17-08-2016.
 */
public class AccesoDTO implements Serializable{

    private Long idAcceso;

    private Long idCliente;

    private Date fechaAccesoIn;

    private Date fechaAccesoOut;

    private Long status;

    public Long getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(Long idAcceso) {
        this.idAcceso = idAcceso;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaAccesoIn() {
        return fechaAccesoIn;
    }

    public void setFechaAccesoIn(Date fechaAccesoIn) {
        this.fechaAccesoIn = fechaAccesoIn;
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
}
