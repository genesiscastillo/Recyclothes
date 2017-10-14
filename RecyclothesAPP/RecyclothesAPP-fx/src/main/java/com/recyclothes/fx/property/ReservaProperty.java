package com.recyclothes.fx.property;

import com.recyclothes.common.dto.ReservaDTO;
import com.recyclothes.common.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Cesar on 25-07-2016.
 */
public class ReservaProperty {

    private StringProperty correo;
    private StringProperty cliente;
    private StringProperty fechaReserva;
    private StringProperty estadoReservaEnum;
    private StringProperty fechaEntrega;
    private StringProperty codigoReserva;
    private StringProperty montoTotal;
    private StringProperty montoPagar;
    private StringProperty horaEntregaEnum;
    private StringProperty datosDeEntrega;

    private ReservaDTO reservaDTO;

    public String getDatosDeEntrega() {
        return datosDeEntregaProperty().get();
    }

    public StringProperty datosDeEntregaProperty() {
        if(datosDeEntrega == null){
            datosDeEntrega = new SimpleStringProperty();
        }
        return datosDeEntrega;
    }

    public void setDatosDeEntrega(String datosDeEntrega) {
        this.datosDeEntregaProperty().set(datosDeEntrega);
    }

    public ReservaProperty(ReservaDTO reservaDTO)   {
        this.reservaDTO = reservaDTO;
        this.setCorreo(reservaDTO.getCliente().getCorreo());
        this.setCliente(reservaDTO.getCliente().getNombres());
        this.setFechaReserva(Utils.getDiaFecha(reservaDTO.getFechaReserva()));
        this.setEstadoReservaEnum(reservaDTO.getEstadoReservaEnum().name());
        this.setFechaEntrega(Utils.getDiaFecha(reservaDTO.getFechaEntrega()));
        this.setCodigoReserva(StringUtils.leftPad(reservaDTO.getIdReserva() + "-" + reservaDTO.getCodigoReserva(), 10));
        this.setMontoTotal(String.valueOf(reservaDTO.getMontoTotal()));
        this.setMontoPagar(String.valueOf(reservaDTO.getMontoPagar()));
        this.setHoraEntregaEnum(reservaDTO.getHoraEntregaEnum() == null ? null : reservaDTO.getHoraEntregaEnum().toString());
        this.setDatosDeEntrega(reservaDTO.getDatosDeEntrega());
    }

    public ReservaDTO getReservaDTO() {
        return reservaDTO;
    }

    public String getHoraEntregaEnum() {
        return horaEntregaEnumProperty().get();
    }

    public StringProperty horaEntregaEnumProperty() {
        if(horaEntregaEnum == null) {
            horaEntregaEnum = new SimpleStringProperty();
        }
        return horaEntregaEnum;
    }

    public void setHoraEntregaEnum(String horaEntregaEnum) {
        this.horaEntregaEnumProperty().set(horaEntregaEnum);
    }

    public String getCorreo() {
        return correoProperty().get();
    }

    public StringProperty correoProperty() {
        if(correo == null){
            correo = new SimpleStringProperty();
        }
        return correo;
    }

    public void setCorreo(String correo) {
        this.correoProperty().set(correo);
    }

    public String getCliente() {
        return clienteProperty().get();
    }

    public StringProperty clienteProperty() {
        if(cliente == null ){
            cliente = new SimpleStringProperty();
        }
        return cliente;
    }

    public void setCliente(String cliente) {
        clienteProperty().set(cliente);
    }

    public String getFechaReserva() {
        return fechaReservaProperty().get();
    }

    public StringProperty fechaReservaProperty() {
        if(fechaReserva == null){
            fechaReserva = new SimpleStringProperty();
        }
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReservaProperty().set(fechaReserva);
    }

    public String getEstadoReservaEnum() {
        return estadoReservaEnumProperty().get();
    }

    public StringProperty estadoReservaEnumProperty() {
        if(estadoReservaEnum == null){
            estadoReservaEnum = new SimpleStringProperty();
        }
        return estadoReservaEnum;
    }

    public void setEstadoReservaEnum(String estadoReservaEnum) {
        this.estadoReservaEnumProperty().set(estadoReservaEnum);
    }

    public String getFechaEntrega() {
        return fechaEntregaProperty().get();
    }

    public StringProperty fechaEntregaProperty() {
        if(fechaEntrega == null){
            fechaEntrega = new SimpleStringProperty();
        }
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntregaProperty().set(fechaEntrega);
    }

    public String getCodigoReserva() {
        return codigoReservaProperty().get();
    }

    public StringProperty codigoReservaProperty() {
        if(codigoReserva == null){
            codigoReserva = new SimpleStringProperty();
        }
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReservaProperty().set(codigoReserva);
    }

    public String getMontoTotal() {
        return montoTotalProperty().get();
    }

    public StringProperty montoTotalProperty() {
        if(montoTotal == null){
            montoTotal = new SimpleStringProperty();
        }
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotalProperty().set(montoTotal);
    }

    public String getMontoPagar() {
        return montoPagarProperty().get();
    }

    public StringProperty montoPagarProperty() {
        if(montoPagar == null){
            montoPagar = new SimpleStringProperty();
        }
        return montoPagar;
    }

    public void setMontoPagar(String montoPagar) {
        this.montoPagarProperty().set(montoPagar);
    }

    @Override
    public String toString() {
        return "ReservaProperty{" +
                "codigoReserva=" + getCodigoReserva() +
                ", cliente=" + getCliente() +
                ", correo=" + getCorreo() +
                ", fechaReserva=" + getFechaReserva()+
                ", estadoReservaEnum=" + getEstadoReservaEnum()+
                ", fechaEntrega=" + getFechaEntrega() +
                ", montoTotal=" + getMontoTotal() +
                ", montoPagar=" + getMontoPagar() +
                ", horaEntregaEnum=" + getHoraEntregaEnum() +
                '}';
    }
}
