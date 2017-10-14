package com.recyclothes.fx.property;

import com.recyclothes.common.dto.ClienteDTO;
import com.recyclothes.common.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Cesar on 10-08-2016.
 */
public class ClienteProperty {

    StringProperty id;
    StringProperty nombres;
    StringProperty apellidos;
    StringProperty correo;
    StringProperty telefono;
    StringProperty fechaRegistroCreacion;
    StringProperty fechaUltimaVisita;

    ClienteDTO clienteDTO;

    public ClienteProperty(ClienteDTO clienteDTO){
        this.clienteDTO = clienteDTO;
        this.setApellidos(clienteDTO.getApellidos());
        this.setCorreo(clienteDTO.getCorreo());
        this.setFechaRegistroCreacion(Utils.getFecha( clienteDTO.getFechaRegistroCreacion()));
        this.setFechaUltimaVisita( Utils.getFecha( clienteDTO.getFechaUltimaVisita()));
        this.setId(clienteDTO.getId().toString());
        this.setNombres(clienteDTO.getNombres());
        this.setTelefono(clienteDTO.getTelefono());
    }

    public String getId() {
        return idProperty().get();
    }

    public StringProperty idProperty() {
        if(id == null){
            id= new SimpleStringProperty();
        }
        return id;
    }

    public void setId(String id) {
        this.idProperty().set(id);
    }

    public String getNombres() {
        return nombresProperty().get();
    }

    public StringProperty nombresProperty() {
        if(nombres == null){
            nombres= new SimpleStringProperty();
        }
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombresProperty().set(nombres);
    }

    public String getApellidos() {
        return apellidosProperty().get();
    }

    public StringProperty apellidosProperty() {
        if(apellidos == null){
            apellidos= new SimpleStringProperty();
        }
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidosProperty().set(apellidos);
    }

    public String getCorreo() {
        return correoProperty().get();
    }

    public StringProperty correoProperty() {
        if(correo == null){
            correo= new SimpleStringProperty();
        }
        return correo;
    }

    public void setCorreo(String correo) {
        this.correoProperty().set(correo);
    }

    public String getTelefono() {
        return telefonoProperty().get();
    }

    public StringProperty telefonoProperty() {
        if(telefono == null){
            telefono= new SimpleStringProperty();
        }
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefonoProperty().set(telefono);
    }

    public String getFechaRegistroCreacion() {
        return fechaRegistroCreacionProperty().get();
    }

    public StringProperty fechaRegistroCreacionProperty() {
        if(fechaRegistroCreacion == null){
            fechaRegistroCreacion= new SimpleStringProperty();
        }
        return fechaRegistroCreacion;
    }

    public void setFechaRegistroCreacion(String fechaRegistroCreacion) {
        this.fechaRegistroCreacionProperty().set(fechaRegistroCreacion);
    }

    public String getFechaUltimaVisita() {
        return fechaUltimaVisitaProperty().get();
    }

    public StringProperty fechaUltimaVisitaProperty() {
        if(fechaUltimaVisita == null){
            fechaUltimaVisita= new SimpleStringProperty();
        }
        return fechaUltimaVisita;
    }

    public void setFechaUltimaVisita(String fechaUltimaVisita) {
        this.fechaUltimaVisitaProperty().set(fechaUltimaVisita);
    }

    public ClienteDTO getClienteDTO() {
        return clienteDTO;
    }

}
